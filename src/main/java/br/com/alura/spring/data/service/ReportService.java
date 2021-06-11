package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.orm.EmployeeProjection;
import br.com.alura.spring.data.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private Boolean system = true;
    private EmployeeRepository employeeRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void init(Scanner scanner) {
        System.out.println(System.lineSeparator());
        system = true;
        while (system) {
            System.out.println("Qual ação deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Buscar funcionário por nome");
            System.out.println("2 - Buscar funcionário data contratação");
            System.out.println("3 - Exibir funcionário formatado");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    findByNameOrderBy(scanner);
                    break;
                case 2:
                    findByHiringDate(scanner);
                    break;
                case 3:
                    findEmployeeSalary();
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void findByName(Scanner scanner) {
        System.out.println("Insira o nome do funcionário");
        String byName = scanner.next();

        // to make the like work correctly you need to be between %
        String byNameLike = "%" + byName + "%";
        List<Employee> employeeList = employeeRepository.findByNameLike(byNameLike);
        employeeList.forEach(System.out::println);
    }

    private void findByNameOrderBy(Scanner scanner) {
        System.out.println("Insira o nome do funcionário");
        String byName = scanner.next();
        String byNameLike = "%" + byName + "%";
        List<Employee> employeeList = employeeRepository.findByNameLikeOrderByNameAsc(byNameLike);
        employeeList.forEach(System.out::println);
    }
    
    private void findByHiringDate(Scanner scanner) {
        System.out.println("Insira data de contratação");
        String date = scanner.next();
        LocalDate localDate = LocalDate.parse(date, formatter);

        List<Employee> hiringDateGreater = employeeRepository.findHiringDateGreater(localDate);
        hiringDateGreater.forEach(System.out::println);
    }

    private void findEmployeeSalary() {
        List<EmployeeProjection> list = employeeRepository.findNameSalary();
        System.out.println("* ********** *");
        list.forEach(e -> System.out.println("Funcionários: id: " + e.getId() + " | Nome: " + e.getName() + " | Salario: R$"
                + e.getSalary()));
        System.out.println("* ********** *");
    }
}
