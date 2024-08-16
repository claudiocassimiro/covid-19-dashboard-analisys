package br.gov.rj.saude.covid.model;

import java.time.LocalDate;

public class Bairro {
    private String nome;
    private int casosConfirmados;
    private int obitos;
    private LocalDate data;

    public Bairro(String nome, int casosConfirmados, int obitos, LocalDate data) {
        this.nome = nome;
        this.casosConfirmados = casosConfirmados;
        this.obitos = obitos;
        this.data = data;
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public int getCasosConfirmados() {
        return casosConfirmados;
    }

    public int getObitos() {
        return obitos;
    }

    public LocalDate getData() {
        return data;
    }

    // Métodos para calcular estatísticas

    public double calcularTaxaCrescimento(int casosAnteriores) {
        return ((double) (casosConfirmados - casosAnteriores) / casosAnteriores) * 100;
    }

    public double calcularTaxaLetalidade() {
        return ((double) obitos / casosConfirmados) * 100;
    }
}

