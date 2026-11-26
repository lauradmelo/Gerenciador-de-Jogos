package br.senac.tads.dsw.gerenciador_jogos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity //Informa que a classe sera uma tabela no banco
public class Jogo {

    @Id //Define chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gera o ID automaticamente.
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotNull(message = "O ano é obrigatório.")
    @Min(value = 1950, message = "O ano deve ser igual ou maior que 1950.")
    private Integer ano;

    @NotBlank(message = "A plataforma é obrigatório.")
    private String plataforma;

    @NotNull(message = "Informe a disponobilidade.")
    private Boolean disponivel;

    private String observacoes;

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
