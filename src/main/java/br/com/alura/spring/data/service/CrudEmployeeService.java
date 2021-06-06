package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudEmployeeService {

    private Boolean system = true;
    private EmployeeRepository employeeRepository;

    public CrudEmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    public void init(Scanner scanner) {
        System.out.println(System.lineSeparator());
        system = true;
        while (system) {
            System.out.println("Qual ação deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Visualizar");
            System.out.println("4 - Remover");

            int action = scanner.nextInt();
            switch (action) {
                case 1:
                    create(scanner);
                    break;
                case 2:
                    update(scanner);
                    break;
                case 3:
                    listAll();
                    break;
                case 4:
                    remove(scanner);
                    break;
                default:
                    system = false;
                    break;
            }
        }
    }

    private void create(Scanner scanner) {
        System.out.println(" º Insira o nome do novo funcionário: ");
        String name = scanner.next();
        System.out.println(" º Insira salario do funcionário: ");
        BigDecimal salary = scanner.nextBigDecimal();

        Employee newEmployee = new Employee();
        newEmployee.setName(name.toUpperCase(Locale.ROOT));
        newEmployee.setSalary(salary);

        employeeRepository.save(newEmployee);
        System.out.println(" **Novo funcionário cadastrado com sucesso! ");
    }

    private void update(Scanner scanner) {
        System.out.println(" Insira o id do funcionário: ");
        int id = scanner.nextInt();
        Optional<Employee> employee = employeeRepository.findById(id);

        System.out.println(" Insira o novo nome: ");
        String newName = scanner.next();
        System.out.println(" Insira o novo salario: ");
        BigDecimal newSalary = scanner.nextBigDecimal();

        Employee newEmployee = new Employee();
        newEmployee.setHiringDate(employee.get().getHiringDate());
        newEmployee.setSalary(newSalary);
        newEmployee.setName(newName.toUpperCase(Locale.ROOT));
        newEmployee.setId(employee.get().getId());

        employeeRepository.save(newEmployee);
    }

    private void listAll() {
        System.out.println("* ********* *");
        List<Employee> employeeList = employeeRepository.findAll();
        employeeList.forEach(System.out::println);
        System.out.println("* ********* *");
    }

    private void remove(Scanner scanner) {
        System.out.println(" º Insira o id do funcionáro: ");
        int id = scanner.nextInt();
        employeeRepository.deleteById(id);
        System.err.println("Funcionário removido com sucesso!");
    }

}
