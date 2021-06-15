package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.Employee;
import br.com.alura.spring.data.orm.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>,
        JpaSpecificationExecutor<Employee> {

    List<Employee> findByNameLike(String name);
    List<Employee> findByNameLikeOrderByNameAsc(String name);
    List<Employee> findByRoleId(int integer);

    @Query(value = "SELECT * FROM employees e WHERE e.hiring_date >= :date", nativeQuery = true)
    List<Employee> findHiringDateGreater(LocalDate date);

    @Query(value = "SELECT e.id, e.name, e.salary, e.hiring_date FROM employees e", nativeQuery = true)
    List<EmployeeProjection> findNameSalary();

}
