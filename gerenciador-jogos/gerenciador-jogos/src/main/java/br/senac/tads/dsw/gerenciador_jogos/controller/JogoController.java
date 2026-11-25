package br.senac.tads.dsw.gerenciador_jogos.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import br.senac.tads.dsw.gerenciador_jogos.model.Jogo;
import br.senac.tads.dsw.gerenciador_jogos.service.JogoService;

@RestController
@RequestMapping("/api/jogos")
public class JogoController {

    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Jogo> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Jogo buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Jogo cadastrar(@Valid @RequestBody Jogo jogo) {
        return service.salvar(jogo);
    }

    @PutMapping("/{id}")
    public Jogo atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Jogo jogo) {

        Jogo atual = service.buscarPorId(id);

        atual.setTitulo(jogo.getTitulo());
        atual.setAno(jogo.getAno());
        atual.setPlataforma(jogo.getPlataforma());
        atual.setDisponivel(jogo.getDisponivel());
        atual.setObservacoes(jogo.getObservacoes());

        return service.salvar(atual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
