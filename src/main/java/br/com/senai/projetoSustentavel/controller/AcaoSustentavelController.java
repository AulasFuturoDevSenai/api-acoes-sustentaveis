package br.com.senai.projetoSustentavel.controller;

import br.com.senai.projetoSustentavel.model.dtos.AcaoSustentavelRequest;
import br.com.senai.projetoSustentavel.model.dtos.AcaoSustentavelResponse;
import br.com.senai.projetoSustentavel.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class AcaoSustentavelController {

    private final AcaoSustentavelService acaoSustentavelService;

    // Acesso liberado para qualquer usuário autenticado
    @GetMapping
    public List<AcaoSustentavelResponse> listarTodas() {
        return acaoSustentavelService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> buscarPorId(@PathVariable Long id) {
        AcaoSustentavelResponse response = acaoSustentavelService.buscarPorId(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(response);
    }

    //  Somente ADMIN pode cadastrar
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AcaoSustentavelResponse> cadastrar(@Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavelResponse response = acaoSustentavelService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //  Somente ADMIN pode atualizar
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavelResponse response = acaoSustentavelService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    // Somente ADMIN pode deletar
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        acaoSustentavelService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Acesso liberado para qualquer usuário autenticado
    @GetMapping("/categoria")
    public ResponseEntity<List<AcaoSustentavelResponse>> listarPorCategoria(@RequestParam String tipo) {
        List<AcaoSustentavelResponse> resultados = acaoSustentavelService.listarPorCategoria(tipo);
        return ResponseEntity.ok(resultados);
    }
}