package br.com.senai.projetoSustentavel.model.dtos;

import br.com.senai.projetoSustentavel.model.enums.CategoriaAcao;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class AcaoSustentavelRequest {
    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
    private String descricao;

    @NotNull(message = "A categoria é obrigatória")
    private CategoriaAcao categoria;

    @NotNull(message = "A data de realização é obrigatória")
    @PastOrPresent(message = "A data não pode estar no futuro")
    private LocalDate dataRealizacao;

    @NotNull(message = "O ID do responsável é obrigatório")
    private Long idResponsavel;

    // Getters e setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public CategoriaAcao getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaAcao categoria) {
        this.categoria = categoria;
    }

    public LocalDate getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(LocalDate dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public Long getIdResponsavel() {
        return idResponsavel;
    }

    public void setIdResponsavel(Long idResponsavel) {
        this.idResponsavel = idResponsavel;
    }
}
