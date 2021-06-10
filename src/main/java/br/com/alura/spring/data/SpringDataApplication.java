package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.orm.Role;
import br.com.alura.spring.data.repository.RoleRepository;
import br.com.alura.spring.data.service.CrudEmployeeService;
import br.com.alura.spring.data.service.CrudRoleService;
import br.com.alura.spring.data.service.CrudUnityControlService;
import br.com.alura.spring.data.service.ReportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private CrudRoleService roleService;
	private CrudUnityControlService unityControlService;
	private CrudEmployeeService employeeService;
	private ReportService reportService;

	private Boolean system = true;

	public SpringDataApplication(CrudRoleService roleService, CrudUnityControlService unityControlService,
								 CrudEmployeeService employeeService, ReportService reportService) {
		this.unityControlService = unityControlService;
		this.roleService = roleService;
		this.employeeService = employeeService;
		this.reportService = reportService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.print("****** SISTEMA INICIADO ******* ");
		Scanner scanner = new Scanner(System.in);
		while(system) {
			System.out.println(System.lineSeparator() + "Qual você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade de controle");
			System.out.println("3 - Funcionario");
			System.out.println("4 - Ralatórios");

			int action = scanner.nextInt();
			switch ( action ) {
				case 1:
					roleService.init(scanner);
					break;
				case 2:
					unityControlService.init(scanner);
					break;
				case 3:
					employeeService.init(scanner);
					break;
				case 4:
					reportService.init(scanner);
					break;
				default:
				system = false;
				break;
			}
		}
		System.out.println("****** SISTEMA ENCERRADO ******* ");
		scanner.close();
	}
}