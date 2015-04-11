package ArduProcessingEclipse;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JanelaServidor extends JFrame {

	private JPanel contentPane;
	Servidor servidor;

	/**
	 * Create the frame.
	 */
	public JanelaServidor(Servidor s) {
		servidor = s;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 209);
		contentPane.add(scrollPane);
		
		final JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(tglbtnNewToggleButton.isSelected()){
					servidor.acende();
					
				}else{
					servidor.apaga();
				}
				
			}
		});
		tglbtnNewToggleButton.setBounds(154, 227, 121, 23);
		contentPane.add(tglbtnNewToggleButton);
	}
}
