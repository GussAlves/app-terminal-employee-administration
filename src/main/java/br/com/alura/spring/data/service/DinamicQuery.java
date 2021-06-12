package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.repository.EmployeeRepository;
import br.com.alura.spring.data.specification.SpecificationEmployee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class DinamicQuery {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private EmployeeRepository employeeRepository;

    public DinamicQuery(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        findNameLike(scanner);
    }

    private void findNameLike(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String name = scanner.next();

        if (name.equalsIgnoreCase("0"))
            name = null;

        System.out.println("Digite o salario: ");
        BigDecimal salary = scanner.nextBigDecimal();

        if (salary.equals(BigDecimal.ZERO))
            salary = null;

        System.out.println("Digite a data de contratação: ");
        String date = scanner.next();

        LocalDate hiringDate = LocalDate.now();
        if (date.equalsIgnoreCase("0"))
            hiringDate = LocalDate.parse("01/10/2015", formatter);
        else
            hiringDate = LocalDate.parse(date, formatter);

        List<Employee> queryResults = employeeRepository
                .findAll(Specification.where(
                        SpecificationEmployee.name(name))
                        .or(SpecificationEmployee.salary(salary))
                        .or(SpecificationEmployee.hiringDate(hiringDate))
                );
        queryResults.forEach(System.out::println);
    }
}
