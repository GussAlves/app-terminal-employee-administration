package br.com.alura.spring.data.orm;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @OneToOne
    private Role role;

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

    @Override
    public String toString() {
        return "Funcionario | id - " + id + " | nome - " + name + " | salario - " + salary + " | dataContratação - " +
                hiringDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss"));
    }
}
