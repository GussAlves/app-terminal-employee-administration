package br.com.alura.spring.data.specification;

import br.com.alura.spring.data.orm.Employee;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationEmployee {

    public static Specification<Employee> name (String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

}
