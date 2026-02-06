package br.com.agi.adm.domain.repository;

import br.com.agi.adm.domain.entity.Assignee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssigneeRepository extends JpaRepository<Assignee, Long> {

    Optional<Assignee> findByName(String name);
}
