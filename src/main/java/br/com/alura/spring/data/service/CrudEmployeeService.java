package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.orm.Role;
import br.com.alura.spring.data.orm.UnityControl;
import br.com.alura.spring.data.repository.EmployeeRepository;
import br.com.alura.spring.data.repository.RoleRepository;
import br.com.alura.spring.data.repository.UnityControlRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CrudEmployeeService {

    private EmployeeRepository employeeRepository;
    private UnityControlRepository unityControlRepository;
    private RoleRepository roleRepository;

    private Boolean system = true;

    public CrudEmployeeService(EmployeeRepository employeeRepository, UnityControlRepository unityControlRepository,
                               RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.unityControlRepository = unityControlRepository;
        this.roleRepository = roleRepository;
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
                    listAll(scanner);
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

        Role role = role(scanner);

        Employee newEmployee = new Employee();
        newEmployee.setName(name.toUpperCase(Locale.ROOT));
        newEmployee.setSalary(salary);
        newEmployee.setRole(role);

        List<UnityControl> unitys = unitys(scanner);
        newEmployee.setUnityControlList(unitys);

        employeeRepository.save(newEmployee);
        System.out.println(" **Novo funcionário cadastrado com sucesso! ");
    }

    private Role role(Scanner scanner) {
        System.out.println(" º Insira o cargoId do funcionário: ");
        int roleId = scanner.nextInt();
        Optional<Role> roleRepositoryById = roleRepository.findById(roleId);
        return roleRepositoryById.get();
    }

    public List<UnityControl> unitys (Scanner scanner) {
        List<UnityControl> unityControlList = new ArrayList<>();
        int unityId;
        do {
            System.out.println("Insira o unidadeId para adicionar uma entidade (0 encerra o registro)");
            unityId = scanner.nextInt();
            if ( unityId != 0 ) {
                Optional<UnityControl> unity = unityControlRepository.findById(unityId);
                unityControlList.add(unity.get());
            }
        }while (unityId != 0);

        return unityControlList;
    }

    private void update(Scanner scanner) {
        System.out.println(" Insira o id do funcionário: ");
        int id = scanner.nextInt();
        Optional<Employee> employee = employeeRepository.findById(id);

        System.out.println(" Insira o novo nome: ");
        String newName = scanner.next();
        System.out.println(" Insira o novo salario: ");
        BigDecimal newSalary = scanner.nextBigDecimal();
        Role role = role(scanner);

        Employee newEmployee = new Employee();
        newEmployee.setHiringDate(employee.get().getHiringDate());
        newEmployee.setSalary(newSalary);
        newEmployee.setName(newName.toUpperCase(Locale.ROOT));
        newEmployee.setId(employee.get().getId());
        newEmployee.setRole(role);

        employeeRepository.save(newEmployee);
    }

    private void listAll(Scanner scanner) {
        System.out.println("* ********* *");
        System.out.println("Qual pagina vc deseja visualizar");
        int page = scanner.nextInt();

        Pageable pageable = PageRequest.of(page, 5, Sort.unsorted());
        Page<Employee> employeeList = employeeRepository.findAll(pageable);

        System.out.println(employeeList);
        System.out.println("Pagina atual " + employeeList.getNumber());
        System.out.println("Total elementos " + employeeList.getTotalElements());

        employeeList.forEach(System.out::println);
        System.out.println("* ********* *");
    }

    private void remove(Scanner scanner) {
        System.out.println(" º Insira o id do funcionáro: ");
        int id = scanner.nextInt();
        Optional<Employee> employee = employeeRepository.findById(id);
        employeeRepository.delete(employee.get());
        System.err.println("Funcionário removido com sucesso!");
    }

}
