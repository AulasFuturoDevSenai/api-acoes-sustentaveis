package br.com.senai.projetoSustentavel.repository;

import br.com.senai.projetoSustentavel.model.entity.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Responsavel, Long> {
    Optional<Responsavel> findByUsername(String username);
}
