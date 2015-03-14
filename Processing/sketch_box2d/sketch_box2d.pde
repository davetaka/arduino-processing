import shiffman.box2d.*;

Box2DProcessing box2d;

void setup(){
  size(600,600);
  
  frameRate = 30;
  
  smooth(); //permite mais suavidade nos movimentos
 
  box2d = new Box2DProcessing(this);
  box2d.createWorld();
  
  
  
}

void draw(){
  background(255);
  box2d.step();

  if(mousePressed){
    Caixa nova = new Caixa(mouseX, mouseY);
  }
}
