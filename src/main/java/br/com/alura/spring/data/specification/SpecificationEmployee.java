package br.com.alura.spring.data.specification;

import br.com.alura.spring.data.orm.Employee;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SpecificationEmployee {

    public static Specification<Employee> name (String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Employee> salary (BigDecimal salary) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salary"), salary));
    }

    public static Specification<Employee> hiringDate (LocalDate localDate) {
        return ((root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("hiringDate"), localDate));
    }
}
