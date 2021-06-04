package br.com.alura.spring.data;

import br.com.alura.spring.data.orm.Role;
import br.com.alura.spring.data.repository.RoleRepository;
import br.com.alura.spring.data.service.CrudRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.CrudRepository;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private CrudRoleService roleService;

	private Boolean system = true;

	public SpringDataApplication(CrudRoleService service) {
		this.roleService = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		while(system) {
			System.out.println("Qual você quer executar");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");

			int action = scanner.nextInt();
			if ( action == 1 ) {
				roleService.init(scanner);
			}
			else
				system = false;
		}
		scanner.close();

	}
}
