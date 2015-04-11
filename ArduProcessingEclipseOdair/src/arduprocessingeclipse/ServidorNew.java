package arduprocessingeclipse;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import cc.arduino.Arduino;


public class ServidorNew extends PApplet {
	private int pinosLeds[] = {1, 8};
	private List<Led> leds = new ArrayList<Led>();
	
	private Arduino arduino;
	
	
	public void setup() {
		for(String porta : Arduino.list()){
			System.out.println(porta);
		}		
		
		arduino = new Arduino(this, Arduino.list()[5]);
		
		for(int l : pinosLeds){
			arduino.pinMode(l, Arduino.OUTPUT);
			Led led = new Led();
			led.setPino(l);
			led.setAceso(false);
			leds.add(led);
		}
		leds.get(0).setAceso(true);
		JanelaServidorNew janela = new JanelaServidorNew(this, leds.size());
		janela.setVisible(true); // mostra outra janela
		setVisible(false);//esconde janela atual
	}

	public void draw() {
		for(Led led : leds){
			if(led.isAceso()){
				arduino.digitalWrite(led.getPino(), Arduino.HIGH);
			} else{
				arduino.digitalWrite(led.getPino(), Arduino.LOW);
			}
		}
	}
	
	public void acende(int indexLed){
		if(!leds.isEmpty()){
			Led led = leds.get(indexLed);
			led.setAceso(true);
		}
	}
	
	public void apagaTodos(){
		for(Led led : leds){
			led.setAceso(false);	
		}
	}
	
	public void acendeTodosAte(int ate){
		apagaTodos();
		for(int i=0; i<=ate; i++){
			acende(i);
		}
	}
	
}
