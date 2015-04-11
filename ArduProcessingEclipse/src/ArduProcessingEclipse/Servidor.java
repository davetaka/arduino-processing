package ArduProcessingEclipse;

import cc.arduino.Arduino;
import processing.core.PApplet;

public class Servidor extends PApplet {
	
	int led = 8;
	boolean aceso = false;
	Arduino arduino;

	
	public void setup() {
		for(String porta : Arduino.list()){
			System.out.println(porta);
		}
		
		arduino = new Arduino(this, Arduino.list()[0]);
		arduino.pinMode(led, Arduino.OUTPUT);
		
		JanelaServidor janela = new JanelaServidor(this);
		janela.setVisible(true);
		
		setVisible(false);
	}

	public void draw() {
		if(aceso){
			arduino.digitalWrite(led, Arduino.HIGH);
		}else{
			arduino.digitalWrite(led, Arduino.LOW);
		}
	}
	
	public void acende(){
		aceso = true;
	}
	
	public void apaga(){
		aceso = false;
	}
}
