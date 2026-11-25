package br.senac.tads.dsw.gerenciador_jogos.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.senac.tads.dsw.gerenciador_jogos.model.Jogo;
import br.senac.tads.dsw.gerenciador_jogos.repository.JogoRepository;

@Service
public class JogoService {

    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> listar() {
        return repository.findAll();
    }

    public Jogo buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Jogo não encontrado."));
    }

    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Jogo não encontrado.");
        }

        repository.deleteById(id);
    }
    
}
