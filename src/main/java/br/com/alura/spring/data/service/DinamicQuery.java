package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.repository.EmployeeRepository;
import br.com.alura.spring.data.specification.SpecificationEmployee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class DinamicQuery {

    private EmployeeRepository employeeRepository;

    public DinamicQuery(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        findNameLike(scanner);
    }

    private void findNameLike(Scanner scanner) {
        System.out.println("Insira o nome do funcionário: ");
        String nameEmployee = scanner.next();

        List<Employee> queryResults = employeeRepository
                .findAll(Specification.where(SpecificationEmployee.name(nameEmployee)));
        queryResults.forEach(x -> System.out.println(x.getId() + " | " + x.getName() + " | R$" + x.getSalary() + " | " +
                x.getRole().getDescription()));
    }
}
