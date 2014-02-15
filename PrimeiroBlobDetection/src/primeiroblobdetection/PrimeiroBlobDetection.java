package primeiroblobdetection;

import processing.core.PApplet;
import processing.core.PImage;
import blobDetection.Blob;
import blobDetection.BlobDetection;
import blobDetection.EdgeVertex;


public class PrimeiroBlobDetection extends PApplet {

	PImage imagem;
	BlobDetection bd;
	
	int alt = 640, lar = 480;
	
	public void setup() {
		size(alt, lar);
		
		imagem = loadImage("foto_gol.jpg");
		imagem.resize(alt, lar);
	
		bd = new BlobDetection(alt, lar);
		bd.setThreshold(0.6f);
		bd.computeBlobs(imagem.pixels);
	}
	

	public void draw() {
		background(0);
		contornar();
	}
	
	private void contornar(){
		Blob contorno;
		EdgeVertex a,b;
		
		for(int i = 0; i < bd.getBlobNb(); i++){
			contorno = bd.getBlob(i);
			
			if(null != contorno){
				stroke(255,255,0);
				
				for(int y = 0; y < contorno.getEdgeNb(); y++){
					a = contorno.getEdgeVertexA(y);
					b = contorno.getEdgeVertexB(y);
					
					if(a != null && b != null){
						System.out.println("Ax " + a.x + "Ay " + a.y + "Bx " + b.x + "By " + b.y);
						line(a.x * lar, a.y * alt, b.x * lar, b.y * alt);
					}
				}
			}
		}
	}
}
