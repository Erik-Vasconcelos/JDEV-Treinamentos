package arquivos.exercicios.exercicio1;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author Erik Vasconcelos
 * @since 19/08/2023
 * 
 *        Crie um programa que escreva textos em um arquivo txt e que também
 *        possa ler o seu conteúdo
 */
public class Tela extends JFrame {
	private JLabel labelNomeArquivo = new JLabel("Selecione um arquivo");
	private JButton btnSelecionar = new JButton("Selecionar");
	private JTextArea textArea = new JTextArea();
	private JButton btnEscrever = new JButton("Escrever");

	private JFileChooser jFileChooser = new JFileChooser();
	private File arquivo;

	public Tela() {
		setLocationRelativeTo(null);
		setSize(new Dimension(500, 350));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		configComponents();
		setVisible(true);
	}

	private void configComponents() {
		JPanel painel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		painel.setLayout(layout);

		textArea.setPreferredSize(new Dimension(450, 200));
		btnSelecionar.addActionListener(e -> obterArquivo());
		btnEscrever.addActionListener(e -> salvarArquivo());
		btnEscrever.setEnabled(false);
		
		configChooser();

		c.gridy = 0;
		painel.add(labelNomeArquivo, c);

		c.weightx = 1;
		painel.add(btnSelecionar, c);

		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		painel.add(textArea, c);

		c.gridy = 2;
		c.gridx = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.CENTER;
		painel.add(btnEscrever, c);

		add(painel);
	}

	private void configChooser() {
		jFileChooser.setFileFilter(new FileNameExtensionFilter("arquivo", "txt"));
		jFileChooser.setAcceptAllFileFilterUsed(false);
	}

	private void obterArquivo() {
		jFileChooser.showOpenDialog(this);
		arquivo = jFileChooser.getSelectedFile();
		
		try {
			FileInputStream entrada = new FileInputStream(arquivo);
			Scanner leitor = new Scanner(arquivo, "UTF-8");
			String texto = "";
			while (leitor.hasNext()) {
				texto += leitor.nextLine() + "\n";
			}

			textArea.setText(texto);
			btnEscrever.setEnabled(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void salvarArquivo() {
		String texto = textArea.getText();

		FileWriter escritor;
		try {
			escritor = new FileWriter(arquivo);
			escritor.write(texto);
			escritor.flush();
			escritor.close();

			textArea.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Tela();
	}

}
