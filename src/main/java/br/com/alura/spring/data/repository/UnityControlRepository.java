package br.com.alura.spring.data.repository;

import br.com.alura.spring.data.orm.UnityControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnityControlRepository extends JpaRepository<UnityControl, Integer> {
}
