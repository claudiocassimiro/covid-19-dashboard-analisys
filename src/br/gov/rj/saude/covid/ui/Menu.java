package br.gov.rj.saude.covid.ui;

import br.gov.rj.saude.covid.dataProcessor.CovidDataProcessor;
import br.gov.rj.saude.covid.dataProcessor.FileManager;
import br.gov.rj.saude.covid.model.Bairro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class Menu extends JFrame {

    private FileManager fileManager;
    private CovidDataProcessor dataProcessor;
    private String caminhoArquivo;

    public Menu(FileManager fileManager, CovidDataProcessor dataProcessor, String caminhoArquivo) {
        this.fileManager = fileManager;
        this.dataProcessor = dataProcessor;
        this.caminhoArquivo = caminhoArquivo;

        // Configuração da janela
        setTitle("Análise COVID-19 - RJ");
        setSize(600, 400); // Aumentei a largura inicial da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Criando os botões
        JButton btnEstatisticas = new JButton("Exibir Estatísticas");
        JButton btnAdicionar = new JButton("Adicionar Novo Registro");
        JButton btnSair = new JButton("Sair");

        // Adicionando ações aos botões
        btnEstatisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirEstatisticas();
            }
        });

        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarNovoRegistro();
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(btnEstatisticas);
        add(btnAdicionar);
        add(btnSair);

        // Tornar a janela visível
        setVisible(true);
    }

    private void exibirEstatisticas() {
        List<Bairro> bairros = fileManager.lerArquivo(caminhoArquivo);
        StringBuilder estatisticas = new StringBuilder("<html><body><table>");

        estatisticas.append("<tr><th>Bairro</th><th>Casos Confirmados</th><th>Óbitos</th><th>Taxa de Letalidade</th></tr>");

        for (Bairro bairro : bairros) {
            estatisticas.append("<tr><td>").append(bairro.getNome()).append("</td>")
                    .append("<td>").append(bairro.getCasosConfirmados()).append("</td>")
                    .append("<td>").append(bairro.getObitos()).append("</td>")
                    .append("<td>").append(String.format("%.2f", bairro.calcularTaxaLetalidade())).append("%</td></tr>");
        }

        estatisticas.append("</table></body></html>");

        // Criação de um JTextPane para exibir o HTML
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(estatisticas.toString());
        textPane.setEditable(false);

        // Adicionando o JTextPane a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(550, 300)); // Configuração da largura e altura preferencial do scroll

        // Exibindo em um JOptionPane com scroll
        JOptionPane.showMessageDialog(this, scrollPane, "Estatísticas", JOptionPane.INFORMATION_MESSAGE);
    }

    private void adicionarNovoRegistro() {
        JTextField campoNome = new JTextField();
        JTextField campoCasos = new JTextField();
        JTextField campoObitos = new JTextField();
        JTextField campoData = new JTextField();

        Object[] campos = {
                "Nome do Bairro:", campoNome,
                "Casos Confirmados:", campoCasos,
                "Óbitos:", campoObitos,
                "Data (yyyy-mm-dd):", campoData
        };

        int option = JOptionPane.showConfirmDialog(this, campos, "Adicionar Novo Registro", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String nome = campoNome.getText();
                int casosConfirmados = Integer.parseInt(campoCasos.getText());
                int obitos = Integer.parseInt(campoObitos.getText());
                LocalDate data = LocalDate.parse(campoData.getText());

                Bairro novoBairro = new Bairro(nome, casosConfirmados, obitos, data);
                fileManager.escreverNoArquivo(caminhoArquivo, novoBairro);
                JOptionPane.showMessageDialog(this, "Registro adicionado com sucesso!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar registro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}