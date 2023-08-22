package com.example.provapomeridiana;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Patrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Long valore;
    private Integer annoDiCreazione;

    // Costruttori

    public Patrimonio() {
    }

    public Patrimonio(String nome, Long valore, Integer annoDiCreazione) {
        this.nome = nome;
        this.valore = valore;
        this.annoDiCreazione = annoDiCreazione;
    }

    // Metodi getter e setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getValore() {
        return valore;
    }

    public void setValore(Long valore) {
        this.valore = valore;
    }

    public Integer getAnnoDiCreazione() {
        return annoDiCreazione;
    }

    public void setAnnoDiCreazione(Integer annoDiCreazione) {
        this.annoDiCreazione = annoDiCreazione;
    }
}
