package arduprocessingeclipse;

import java.awt.Event;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JanelaServidorNew extends JFrame {

	private JPanel contentPane;
	ServidorNew servidor;
	int totalLeds = 0;
	
	@Override
	public boolean action(Event arg0, Object arg1) {
		// TODO Auto-generated method stub
		return super.action(arg0, arg1);
	}
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public JanelaServidorNew(ServidorNew s, int totalLeds) {
		servidor = s;
		this.totalLeds = totalLeds;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 299, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JSlider slider = new JSlider();
		slider.setMaximum(totalLeds + 1);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				servidor.acendeTodosAte(slider.getValue() ); 
			}
		});
		slider.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
			}
			public void ancestorMoved(AncestorEvent arg0) {
				
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		slider.setValue(0);
		slider.setBounds(67, 166, 226, 29);
		contentPane.add(slider);
		
		JSlider slider_1 = new JSlider();
		slider_1.setValue(5);
		slider_1.setMaximum(10);
		slider_1.setOrientation(SwingConstants.VERTICAL);
		slider_1.setBounds(34, 6, 21, 189);
		contentPane.add(slider_1);
	}
}
