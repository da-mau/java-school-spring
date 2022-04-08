package com.mau.spring.repository;

import com.mau.spring.entity.Employee;
import com.mau.spring.projection.EmployeeByLocation;
import com.mau.spring.projection.GenderEmployee;
import com.mau.spring.projection.PositionEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(value = "select count(e.employee_id) total, ep.position_name positionName\n" +
            "from employee e, employee_position ep \n" +
            "where e.status = 'Active'\n" +
            "and ep.status = 'Active'\n" +
            "and e.employee_id = ep.employee_id \n" +
            "group by (ep.position_name) union\n" +
            "select count(e.employee_id) as total, 'N/A' as positionName  \n" +
            "from employee e where e.employee_id not in\n" +
            "(select distinct(ep.employee_id) from employee_position ep)\n" +
            "and e.status ='Active'", nativeQuery = true)
    List<PositionEmployee> countEmployeesByPosition();

    @Query(value = "Select count(e.gender) as count,  (select count(e2) from Employee as e2) as total, e.gender as genderName  \n" +
            "from Employee as e \n" +
            "group by e.gender")
    List<GenderEmployee> getPercentagesByGender();

    @Query(value = "Select e \n" +
            "from Employee as e join Position as ep  \n" +
            "on e.employeeId = ep.employee.employeeId \n" +
            "where lower(ep.positionName) like lower(concat('%', :position, '%'))\n" +
            "and e.status = ep.status \n" +
            "and e.status IN (:status) \n" +
            "and lower(e.firstName) like lower(concat('%', :firstName, '%'))\n" +
            "and lower(e.lastName)  like lower(concat('%', :lastName, '%'))")
    Page<Employee> getEmployeeByPartialNamesAndPosition(@Param("firstName") String firstName, @Param("lastName") String lastName,
                                                        @Param("position") String position, @Param("status") List<String> status,
                                                        Pageable pageable) ;

    @Query(value = "select  ci.country as country, ci.state as state , count(e.employeeId) as count\n" +
            "from Employee as e join ContactInformation as ci \n" +
            "on e.employeeId = ci.employee.employeeId \n" +
            "and e.status = 'Active' \n" +
            "group by ci.country ,ci.state\n" +
            "order by ci.country")
    List<EmployeeByLocation> getEmployeesByCountryAndState();

}
