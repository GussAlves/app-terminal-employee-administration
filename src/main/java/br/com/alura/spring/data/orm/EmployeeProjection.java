package br.com.alura.spring.data.orm;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface EmployeeProjection {

    Integer getId();
    String getName();
    BigDecimal getSalary();
    LocalDate getHiringDate();

}
