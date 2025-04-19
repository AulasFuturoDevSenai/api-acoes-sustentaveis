package br.com.senai.projetoSustentavel.controller;

import br.com.senai.projetoSustentavel.model.dtos.AcaoSustentavelRequest;
import br.com.senai.projetoSustentavel.model.dtos.AcaoSustentavelResponse;
import br.com.senai.projetoSustentavel.service.AcaoSustentavelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
@RequiredArgsConstructor
public class AcaoSustentavelController {

    private final AcaoSustentavelService acaoSustentavelService;

    @GetMapping
    public List<AcaoSustentavelResponse> listarTodas() {
        return acaoSustentavelService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(acaoSustentavelService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AcaoSustentavelResponse> cadastrar(@Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavelResponse response = acaoSustentavelService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AcaoSustentavelResponse> atualizar(@PathVariable Long id, @Valid @RequestBody AcaoSustentavelRequest request) {
        AcaoSustentavelResponse response = acaoSustentavelService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        acaoSustentavelService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
