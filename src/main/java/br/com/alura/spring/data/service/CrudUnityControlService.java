package br.com.alura.spring.data.service;

import br.com.alura.spring.data.orm.UnityControl;
import br.com.alura.spring.data.repository.UnityControlRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

@Service
public class CrudUnityControlService {

    private Boolean system = true;
    private UnityControlRepository controlRepository;

    public CrudUnityControlService(UnityControlRepository repository) {
        this.controlRepository = repository;
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

    private void remove(Scanner scanner) {
        System.out.println(" º Insira o id da unidade que deseja remover: ");
        int id = scanner.nextInt();
        controlRepository.deleteById(id);
        System.err.println("Unidade removida com sucesso!");
    }

    private void update(Scanner scanner) {
        System.out.println(" º Insira o id da unidade de controle que você deseja alterar: ");
        int id = scanner.nextInt();
        System.out.println(" º Insira o novo nome: ");
        String name = scanner.next();
        System.out.println(" º Insira o novo endereço: ");
        String address = scanner.next();

        UnityControl unityControl = new UnityControl();
        unityControl.setId(id);
        unityControl.setDescription(name.toUpperCase(Locale.ROOT));
        unityControl.setAddress(address.toUpperCase(Locale.ROOT));

        controlRepository.save(unityControl);
        System.err.println("Unidade atuailizada com sucesso!");
    }

    private void listAll() {
        System.out.println("* ********* *");
        List<UnityControl> listUnityControl = controlRepository.findAll();
        listUnityControl.forEach(System.out::println);
        System.out.println("* ********* *");
    }

    private void create(Scanner scanner) {
        System.out.print(" º Insira o nome da nova unidade de controle: ");
        String name = scanner.next();
        System.out.println(" º Insira o endereço da unidade: ");
        String address = scanner.next();

        UnityControl unityControl = new UnityControl();
        unityControl.setAddress(address.toUpperCase(Locale.ROOT));
        unityControl.setDescription(name.toUpperCase(Locale.ROOT));

        controlRepository.save(unityControl);
        System.err.println("Nova unidade de controle cadastrada com sucesso!");
    }

}
