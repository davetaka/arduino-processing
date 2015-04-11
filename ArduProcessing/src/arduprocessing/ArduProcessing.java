package arduprocessing;

import cc.arduino.Arduino;
import processing.core.PApplet;


public class ArduProcessing extends PApplet {
	int led = 12;
	Arduino arduino;
	
	public void setup() {
		println(Arduino.list());
		
		String porta = Arduino.list()[0];
		arduino = new Arduino(this, porta, 57600);
		arduino.pinMode(led, Arduino.OUTPUT);
		
		
		
	}

	public void draw() {
		arduino.digitalWrite(led, Arduino.HIGH);
		delay(1000);
		arduino.digitalWrite(led, Arduino.LOW);
		System.out.println("draw");
	}
}
