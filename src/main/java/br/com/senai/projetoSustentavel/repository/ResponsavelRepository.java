package br.com.senai.projetoSustentavel.repository;

import br.com.senai.projetoSustentavel.model.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
