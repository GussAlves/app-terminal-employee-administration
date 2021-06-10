package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findByNameLike(String name);
    List<Employee> findByNameLikeOrderByNameAsc(String name);

    @Query("SELECT e FROM Employee e WHERE e.name = :name AND e.salary >= :salary")
    List<Employee> findNameSalaryGreaterHiringDate(String name, BigDecimal salary);

    @Query(value = "SELECT * FROM employees e WHERE e.hiring_date >= :date", nativeQuery = true)
    List<Employee> findHiringDateGreater(LocalDate date);

}
