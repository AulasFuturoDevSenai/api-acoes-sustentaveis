package br.com.senai.projetoSustentavel.repository;

import br.com.senai.projetoSustentavel.model.entity.AcaoSustentavel;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.projetoSustentavel.model.enums.CategoriaAcao;

import java.util.List;

public interface AcaoSustentavelRepository extends JpaRepository<AcaoSustentavel, Long> {
    List<AcaoSustentavel> findByCategoria(CategoriaAcao categoria);
}
