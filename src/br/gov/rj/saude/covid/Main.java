package br.gov.rj.saude.covid;

import br.gov.rj.saude.covid.dataProcessor.CovidDataProcessor;
import br.gov.rj.saude.covid.dataProcessor.FileManager;
import br.gov.rj.saude.covid.ui.Menu;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Uso: java -jar covid-analise.jar <caminho do arquivo>");
            return;
        }

        String caminhoArquivo = args[0];
        FileManager fileManager = new FileManager();
        CovidDataProcessor dataProcessor = new CovidDataProcessor();

        SwingUtilities.invokeLater(() -> new Menu(fileManager, dataProcessor, caminhoArquivo));
    }
}
