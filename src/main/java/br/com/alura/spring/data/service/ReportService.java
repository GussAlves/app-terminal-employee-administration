package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ReportService {

    private Boolean system = true;
    private EmployeeRepository employeeRepository;

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

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    findByName(scanner);
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
        List<Employee> employeeList = employeeRepository.findByName(byName);
        employeeList.forEach(System.out::println);
    }
}
