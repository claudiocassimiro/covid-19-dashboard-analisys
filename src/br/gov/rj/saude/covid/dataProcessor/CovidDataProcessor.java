package br.gov.rj.saude.covid.dataProcessor;

import br.gov.rj.saude.covid.model.Bairro;

import java.util.*;

public class CovidDataProcessor {

    public Map<String, Double> calcularTaxasCrescimento(List<Bairro> bairros) {
        Map<String, Double> taxasCrescimento = new HashMap<>();

        for (Bairro bairro : bairros) {
            // Aqui seria necessário obter os dados anteriores para calcular a taxa de crescimento
            int casosAnteriores = obterCasosAnteriores(bairro.getNome(), bairros);
            double taxaCrescimento = bairro.calcularTaxaCrescimento(casosAnteriores);
            taxasCrescimento.put(bairro.getNome(), taxaCrescimento);
        }

        return taxasCrescimento;
    }

    public Map<String, Double> calcularTaxasLetalidade(List<Bairro> bairros) {
        Map<String, Double> taxasLetalidade = new HashMap<>();

        for (Bairro bairro : bairros) {
            double taxaLetalidade = bairro.calcularTaxaLetalidade();
            taxasLetalidade.put(bairro.getNome(), taxaLetalidade);
        }

        return taxasLetalidade;
    }

    public List<Bairro> identificarBairrosMaisImpactados(List<Bairro> bairros) {
        List<Bairro> bairrosMaisImpactados = new ArrayList<>();

        // Adicione lógica para identificar os bairros mais impactados com base nas estatísticas

        return bairrosMaisImpactados;
    }

    private int obterCasosAnteriores(String nomeBairro, List<Bairro> bairros) {
        // Lógica para obter o número de casos anteriores de um bairro
        return 0; // Placeholder
    }
}

