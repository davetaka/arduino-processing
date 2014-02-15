package primeirobox2d;

import java.util.ArrayList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.contacts.Contact;

import pbox2d.PBox2D;
import processing.core.PApplet;


public class PrimeiroBox2D extends PApplet {

	ArrayList<Box> caixas = new ArrayList<Box>();
	
	PBox2D mundo; //intancia do mundo
	
	public void setup() {
		size(600,600);
		smooth();
		
		frameRate(30);
		
		//cria o mundo
		mundo = new PBox2D(this);
		mundo.createWorld();
		
		//define a gravidade
		mundo.setGravity(0, -20);
		
		mundo.listenForCollisions();
	}
	
	public void beginContact(Contact c){
		System.out.println("Colidiu !!");
		
		Fixture textura1 = c.getFixtureA();
		Fixture textura2 = c.getFixtureB();
		
		Body corpoA = textura1.getBody();
		Body corpoB = textura2.getBody();
		
		corpoA.setUserData("colidido");
		corpoB.setUserData("colidido");
		
		mundo.destroyBody(corpoA);
		mundo.destroyBody(corpoB);
	}
	
	public void endContact(Contact c){
		Fixture textura1 = c.getFixtureA();
		Fixture textura2 = c.getFixtureB();
		
		Body corpoA = textura1.getBody();
		Body corpoB = textura2.getBody();

		corpoA.setUserData("");
		corpoB.setUserData("");
	}

	public void draw() {
		background(255);
		
		//inicializa o mundo
		mundo.step();
		
		if(mousePressed){
			
			Box b =  new Box(mouseX,mouseY,16,16, mouseButton == RIGHT);
			caixas.add(b);
			
		}
		
		for(Box c: caixas){
			c.display();
			
		}
	}

	class Box{
		float x, y, w, h;
		Body corpo;
		boolean isFixed;

		public Box(float _x, float _y, float _w, float _h, boolean _isFixed) {
			x = _x;
			y = _y;
			w = _w;
			h = _h;
			isFixed = _isFixed;

			//definicao do corpo
			BodyDef definicao = new BodyDef();
			
			//conversor de medidas entre os mundos
			Vec2 posicao = mundo.coordPixelsToWorld(x,y);
			
			definicao.position.set(posicao);
			definicao.bullet = true;
			
			definicao.type = isFixed ? BodyType.STATIC : BodyType.DYNAMIC; //statico ou dinamico, se é afetado pela gravidade ou nao
			
			corpo = mundo.createBody(definicao); //cria o corpo
			
			PolygonShape shape = new PolygonShape();
			
			float wMundoReal = mundo.scalarPixelsToWorld(w/2);
			float hMundoReal = mundo.scalarPixelsToWorld(h/2);
			
			shape.setAsBox(wMundoReal, hMundoReal);
			
			//aplica valores fisica ao corpo
			FixtureDef textura = new FixtureDef();
			textura.shape = shape;
			textura.density = 1;
			textura.friction = 0.3f;
			
			corpo.createFixture(textura);
			
			
			
		}
		
		void display(){
			Vec2 posB = mundo.getBodyPixelCoord(corpo);
			float a = corpo.getAngle();
			
			pushMatrix(); //permite interacao nas posicoes
			translate(posB.x, posB.y);
			
			rotate(-a);
			
			if(isFixed){
				fill(175);	
			}else{
				if(corpo.getUserData() == "colidido"){
					fill(255,0,0);
				}else{
					fill(0,150,0);
				}

			}
			
			stroke(0);
			rectMode(CENTER);
			rect(0, 0, w, h);
			
			
			popMatrix(); //termina interacoes nas posicoes
			
		}
	}
}
