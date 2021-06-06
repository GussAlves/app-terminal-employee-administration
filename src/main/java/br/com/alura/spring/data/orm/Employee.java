package br.com.alura.spring.data.orm;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal salary;
    @Column(name="hiring_date")
    private LocalDateTime hiringDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name="role_id", nullable = false)
    private Role role;

    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="employees_unitys", joinColumns = {
            @JoinColumn(name="fk_employee") }, inverseJoinColumns = { @JoinColumn(name="fk_unity" ) })
    private List<UnityControl> unityControlList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDateTime getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDateTime hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Funcionario | id - " + id + " | nome - " + name + " | salario - R$" + salary + " | cargo - " + role
                + " | dataContratação - " + hiringDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
    }

    public List<UnityControl> getUnityControlList() {
        return unityControlList;
    }

    public void setUnityControlList(List<UnityControl> unityControlList) {
        this.unityControlList = unityControlList;
    }
}
