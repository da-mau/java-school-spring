package com.mau.spring.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.beans.Transient;

public interface GenderEmployee {
    @JsonIgnore
    Long getCount();
    @Transient
    Long getTotal();

    String getGenderName();

    @Transient
    default Double getPercentageDouble(){
        return getCount().doubleValue()/getTotal().doubleValue()*100;
    }

    default String getPercentage(){
        double percentage = getPercentageDouble();
        String result = String.format("%2.2f%%", percentage);
        return result;
    }
}
