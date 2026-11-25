package br.senac.tads.dsw.gerenciador_jogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.senac.tads.dsw.gerenciador_jogos.model.Jogo;


public interface JogoRepository extends JpaRepository<Jogo, Long> {
    
}
