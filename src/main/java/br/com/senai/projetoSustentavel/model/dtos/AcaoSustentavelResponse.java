package br.com.senai.projetoSustentavel.model.dtos;

import br.com.senai.projetoSustentavel.model.enums.CategoriaAcao;
import java.time.LocalDate;

public class AcaoSustentavelResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private CategoriaAcao categoria;
    private LocalDate dataRealizacao;
    private String nomeResponsavel;

    public AcaoSustentavelResponse(Long id, String titulo, String descricao,
                                   CategoriaAcao categoria, LocalDate dataRealizacao,
                                   String nomeResponsavel) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
        this.dataRealizacao = dataRealizacao;
        this.nomeResponsavel = nomeResponsavel;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaAcao getCategoria() {
        return categoria;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
}
