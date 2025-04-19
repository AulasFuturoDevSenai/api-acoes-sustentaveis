package br.com.senai.projetoSustentavel.service;

import br.com.senai.projetoSustentavel.model.exceptions.RecursoNaoEncontradoException;
import org.springframework.stereotype.Service;
import br.com.senai.projetoSustentavel.model.dtos.AcaoSustentavelRequest;
import br.com.senai.projetoSustentavel.model.dtos.AcaoSustentavelResponse;
import br.com.senai.projetoSustentavel.model.entity.AcaoSustentavel;
import br.com.senai.projetoSustentavel.model.entity.Responsavel;
import br.com.senai.projetoSustentavel.repository.AcaoSustentavelRepository;
import br.com.senai.projetoSustentavel.repository.ResponsavelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcaoSustentavelService {
    @Autowired
    private AcaoSustentavelRepository acaoRepository;

    @Autowired
    private ResponsavelRepository responsavelRepository;

    public AcaoSustentavelResponse criar(AcaoSustentavelRequest request) {
        Responsavel responsavel = responsavelRepository.findById(request.getIdResponsavel())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado"));

        AcaoSustentavel acao = new AcaoSustentavel();
        acao.setTitulo(request.getTitulo());
        acao.setDescricao(request.getDescricao());
        acao.setCategoria(request.getCategoria());
        acao.setDataRealizacao(request.getDataRealizacao());
        acao.setResponsavel(responsavel);

        AcaoSustentavel salvo = acaoRepository.save(acao);

        return toResponse(salvo);
    }

    public List<AcaoSustentavelResponse> listarTodas() {
        return acaoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AcaoSustentavelResponse buscarPorId(Long id) {
        AcaoSustentavel acao = acaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação não encontrada com ID: " + id));

        return toResponse(acao);
    }

    public AcaoSustentavelResponse atualizar(Long id, AcaoSustentavelRequest request) {
        AcaoSustentavel acao = acaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação não encontrada com ID: " + id));

        Responsavel responsavel = responsavelRepository.findById(request.getIdResponsavel())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável não encontrado"));

        acao.setTitulo(request.getTitulo());
        acao.setDescricao(request.getDescricao());
        acao.setCategoria(request.getCategoria());
        acao.setDataRealizacao(request.getDataRealizacao());
        acao.setResponsavel(responsavel);

        AcaoSustentavel atualizado = acaoRepository.save(acao);

        return toResponse(atualizado);
    }

    public void deletar(Long id) {
        AcaoSustentavel acao = acaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Ação não encontrada com ID: " + id));

        acaoRepository.delete(acao);
    }

    private AcaoSustentavelResponse toResponse(AcaoSustentavel acao) {
        return new AcaoSustentavelResponse(
                acao.getId(),
                acao.getTitulo(),
                acao.getDescricao(),
                acao.getCategoria(),
                acao.getDataRealizacao(),
                acao.getResponsavel().getNome()
        );
    }
}
