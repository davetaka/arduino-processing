package gravityswarm;

import processing.core.PApplet;


public class GravitySwarm extends PApplet {

	Particle[] Z = new Particle[6000]; //Cria um array com 6000 particulas
	float colour = random(1); //Cor qualquer randomica.

	public void setup() {
		//smooth(); //Suaviza todas as formas geometricas

		size(500,500,P2D); //Tamanho 500, 500 2D

		//TODO: fundo branco
		background(0);

		//TODO Para cada item do Array de particulas
		//TODO: inicializar a particula com new Particle( random(width), random(height), 0, 0, 1 );
		for(int i = 0; i < Z.length; i++)
			Z[i] = new Particle( random(width), random(height), 0, 0, 1 );



		frameRate(60); //Alterando a taxa de atualiza'c~ao para 60


		//TODO: Modo de COR = RGB, ate 255
		

	}

	public void draw() {
		float r;
		colorMode(RGB, 255);
		//background(0);
		
		//TODO: Pintar linha de preto
		stroke(0);
		//TODO: Preencher de branco
		fill(0);
		//TODO: Quadrado comecando em 0, 0, do tamanho total da tela (width, height)
		//rect(0,0,displayWidth, displayHeight);

		colorMode(HSB,1); //Alterando o esquema de cor

		//TODO: Para cada item do array de particulas
		for(int i = 0; i< Z.length; i++){

			//TODO: SE o mouse estiver pressionado E o botao do mouse for o ESQUERDO entao
			if(mousePressed && mouseButton == LEFT){
				//Atrair uma nova particula =  Z[i].gravitate( new Particle( mouseX, mouseY, 0, 0, 1 ) );
				Z[i].gravitate( new Particle( mouseX, mouseY, 0, 0, 1 ) );

				//TODO: FIM SE
			}else if(mousePressed && mouseButton == RIGHT){
				//TODO: SENAO SE o mouse estiver pressionado e o botao for o ESQUERDO enta
				//Dividir a particula =  Z[i].repel( new Particle( mouseX, mouseY, 0, 0, 1 ) );
				Z[i].repel( new Particle( mouseX, mouseY, 0, 0, 1 ) );

				//TODO: FIM SENAO SE
			}else{
				//TODO: SENAO
				//Deteriorar a particula =  Z[i].deteriorate();
				Z[i].deteriorate();


				//TODO FIM SENAO
			}

			//TODO: Atualizar a particula: Z[i].update();
			Z[i].update();

			//Preencher r com um numero qualquer: r = (float)i/Z.length;
			r = (float)i/Z.length;

			//Pintar a cor da borda com uma cor qualquer: stroke( colour, (float)Math.pow(r,0.1), 1-r, (float) 0.15 );
			stroke( colour, (float)Math.pow(r,0.1), 1-r, (float) 0.15 );

			//Mostrar a particula: Z[i].display();
			Z[i].display();
		}
		//TODO: FIM PARA CADA


		//Alterar a cor para esquema RGB, 255
		colorMode(RGB);

		colour+=Math.random();
		if( colour > 1 ) {
			colour = colour%1;
		}

	}















	/* classe particula */
	class Particle {

		float x;
		float y;
		float px;
		float py;
		float magnitude;
		float angle;
		float mass;

		Particle( float dx, float dy, float V, float A, float M ) {
			x = dx;
			y = dy;
			px = dx;
			py = dy;
			magnitude = V;
			angle = A;
			mass = M;
		}

		void reset( float dx, float dy, float V, float A, float M ) {
			x = dx;
			y = dy;
			px = dx;
			py = dy;
			magnitude = V;
			angle = A;
			mass = M;
		}

		void gravitate( Particle Z ) {
			float F, mX, mY, A;
			if( sq( x - Z.x ) + sq( y - Z.y ) != 0 ) {
				F = mass * Z.mass;
				mX = ( mass * x + Z.mass * Z.x ) / ( mass + Z.mass );
				mY = ( mass * y + Z.mass * Z.y ) / ( mass + Z.mass );
				A = findAngle( mX - x, mY - y );

				mX = F * cos(A);
				mY = F * sin(A);

				mX += magnitude * cos(angle);
				mY += magnitude * sin(angle);

				magnitude = sqrt( sq(mX) + sq(mY) );
				angle = findAngle( mX, mY );
			}
		}

		void repel( Particle Z ) {
			float F, mX, mY, A;
			if( sq( x - Z.x ) + sq( y - Z.y ) != 0 ) {
				F = mass * Z.mass;
				mX = ( mass * x + Z.mass * Z.x ) / ( mass + Z.mass );
				mY = ( mass * y + Z.mass * Z.y ) / ( mass + Z.mass );
				A = findAngle( x - mX, y - mY );

				mX = F * cos(A);
				mY = F * sin(A);

				mX += magnitude * cos(angle);
				mY += magnitude * sin(angle);

				magnitude = sqrt( sq(mX) + sq(mY) );
				angle = findAngle( mX, mY );
			}
		}

		void deteriorate() {
			//magnitude *= 0.925;
			magnitude = 1;
		}

		void update() {

			x += magnitude * cos(angle);
			y += magnitude * sin(angle);

		}

		void display() {
			line(px,py,x,y);
			px = x;
			py = y;
		}


	}

	float findAngle( float x, float y ) {
		float theta;
		if(x == 0) {
			if(y > 0) {
				theta = HALF_PI;
			}
			else if(y < 0) {
				theta = 3*HALF_PI;
			}
			else {
				theta = 0;
			}
		}
		else {
			theta = atan( y / x );
			if(( x < 0 ) && ( y >= 0 )) { theta += PI; }
			if(( x < 0 ) && ( y < 0 )) { theta -= PI; }
		}
		return theta;
	}

}





















