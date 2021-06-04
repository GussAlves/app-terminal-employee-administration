package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Role;
import br.com.alura.spring.data.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

@Service
public class CrudRoleService {

    private Boolean system = true;
    private final RoleRepository repository;

    public CrudRoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public void init(Scanner scanner){
        System.out.println(System.lineSeparator());
        system = true;
        while (system) {
            System.out.println("Qual ação deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");

            int action = scanner.nextInt();

            if ( action == 1 )
                save(scanner);
            if ( action == 2 )
                update(scanner);
            else
                system = false;
        }
    }

    public void save(Scanner scanner){
        System.out.println(" º Qual é o novo cargo a ser adicionado: ");
        String newRole = scanner.next();
        Role role = new Role();
        role.setDescription(newRole.toUpperCase(Locale.ROOT));
        repository.save(role);
        System.out.println("Novo cargo cadastrado com sucesso" + System.lineSeparator());
    }

    public void update(Scanner scanner) {
        System.out.println(" º Qual o id do cargo a ser alterado: ");
        int id = scanner.nextInt();
        System.out.println(" º Insira novo nome: ");
        String newDesc = scanner.next();

        Role role = new Role();
        role.setId(id);
        role.setDescription(newDesc.toUpperCase(Locale.ROOT));
        repository.save(role);
        System.out.println("Cargo Atualizado");
    }

}
