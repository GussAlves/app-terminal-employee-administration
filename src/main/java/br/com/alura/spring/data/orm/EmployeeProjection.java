package br.com.alura.spring.data.orm;

import java.math.BigDecimal;

public interface EmployeeProjection {

    Integer getId();
    String getName();
    BigDecimal getSalary();

}
