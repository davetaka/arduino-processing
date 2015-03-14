import shiffman.box2d.*;

class Caixa{
  Body corpo;
  float a,l;
  
  Caixa(float x, float y){
    a = 16;
    l = 16;
    
    BodyDef definicao = new BodyDef();
    definicao.type = BodyType.DYNAMIC;
    definicao.position.set(box2d.coordPixelsToWorld(x,y));
    corpo = box2d.createBody(definicao);
    
    PolygonShape quadrado = new PolygonShape();
    
    float box2dAltura = box2d.scalarPixelsToWorld(a);
    float box2dLargura = box2d.scalarPixelsToWorld(l);
    quadrado.setAsBox(box2dAltura,box2dLargura);
    
    FixtureDef fd = new FixtureDef();
    fd.shape = quadrado;
    fd.density = 1;
    fd.friction = 0.3;
    fd.restitution = 0.5;    
    
    corpo.createFixture(fd);
    
  }
  
}
