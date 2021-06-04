package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.Role;
import br.com.alura.spring.data.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Scanner;

@Service
public class CrudRoleService {

    private final RoleRepository repository;

    public CrudRoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public void init(Scanner scanner){
        save(scanner);
    }

    public void save(Scanner scanner){
        System.out.println(" º Qual é o novo cargo a ser adicionado: ");
        String newRole = scanner.next();
        Role role = new Role();
        role.setDescription(newRole.toUpperCase(Locale.ROOT));
        repository.save(role);
        System.out.println("Novo cargo cadastrado com sucesso" + System.lineSeparator());
    }

}
