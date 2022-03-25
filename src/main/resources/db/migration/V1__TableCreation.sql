-- public.employee definition

-- Drop table

-- DROP TABLE employee;

CREATE TABLE employee (
	employee_id int4 NOT NULL GENERATED ALWAYS AS IDENTITY,
	corp_email varchar(255) NOT NULL,
	first_name varchar(40) NOT NULL,
	last_name varchar(40) NOT NULL,
	gender bpchar(1) NULL,
	birthday date NULL,
	status varchar(10) NULL,
	CONSTRAINT employee_pk PRIMARY KEY (employee_id),
	CONSTRAINT employee_un UNIQUE (corp_email)
);

-- public.contact_information definition

-- Drop table

-- DROP TABLE contact_information;

CREATE TABLE contact_information (
	employee_id int4 NOT NULL,
	street_name varchar(255) NOT NULL,
	street_number numeric NOT NULL,
	zip_code numeric NOT NULL,
	state varchar(40) NOT NULL,
	country varchar(80) NOT NULL,
	personal_email varchar(255) NULL,
	phone_number varchar(40) NULL
);


-- public.contact_information foreign keys

ALTER TABLE contact_information ADD CONSTRAINT contact_information_fk FOREIGN KEY (employee_id) REFERENCES employee(employee_id);

-- public."position" definition

-- Drop table

-- DROP TABLE "position";

CREATE TABLE "position" (
	employee_id int4 NOT NULL,
	position_name varchar(80) NOT NULL,
	salary numeric NOT NULL,
	start_date date NULL,
	end_date date NULL
);


-- public."position" foreign keys

ALTER TABLE "position" ADD CONSTRAINT position_fk FOREIGN KEY (employee_id) REFERENCES employee(employee_id);
