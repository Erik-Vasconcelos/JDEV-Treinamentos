package thread;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaThread extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel jPanel = new JPanel();
	
	private JLabel label = new JLabel("Email");
	private JTextField campoEmail = new JTextField();
	private JButton botaoAdd = new JButton("Adicionar");
	
	private FilaThread filaThread = new FilaThread();
	
	public TelaThread() {
		setTitle("Thread");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(new Dimension(300, 200));
		setResizable(false);
		
		configPainel();
		add(jPanel);
		
		filaThread.start();
		setVisible(true);
	}
	
	private void configPainel() {
		campoEmail.setPreferredSize(new Dimension(200, 25));

		botaoAdd.addActionListener(e -> {
			Email email = new Email(campoEmail.getText());
			FilaThread.add(email);
		});
		
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		jPanel.setLayout(layout);
		
		c.gridy = 1;
		jPanel.add(label, c);
		
		c.gridy = 2;
		jPanel.add(campoEmail, c);
		
		c.gridy = 3;
		jPanel.add(botaoAdd, c);
		
	}
	
	public static void main(String[] args) {
		new TelaThread();
	}
	
}
