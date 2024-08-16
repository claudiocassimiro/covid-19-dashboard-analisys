package br.gov.rj.saude.covid.dataProcessor;

import br.gov.rj.saude.covid.model.Bairro;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public List<Bairro> lerArquivo(String caminhoArquivo) {
        List<Bairro> bairros = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(caminhoArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String nome = dados[0];
                int casosConfirmados = Integer.parseInt(dados[1]);
                int obitos = Integer.parseInt(dados[2]);
                LocalDate data = LocalDate.parse(dados[3]);

                bairros.add(new Bairro(nome, casosConfirmados, obitos, data));
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return bairros;
    }

    public void escreverNoArquivo(String caminhoArquivo, Bairro bairro) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(caminhoArquivo), StandardOpenOption.APPEND)) {
            String novaLinha = String.format("%s,%d,%d,%s", bairro.getNome(), bairro.getCasosConfirmados(), bairro.getObitos(), bairro.getData());
            bw.write(novaLinha);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }
}

