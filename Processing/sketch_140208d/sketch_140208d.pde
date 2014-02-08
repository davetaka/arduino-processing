
void setup(){
  size(600,600);
  background(255);
}


void draw(){
  //strokeWeight(abs(pmouseX - mouseX) + abs(pmouseY - mouseY));
  strokeWeight(10);
  stroke(abs(pmouseX - mouseX) * 5, abs(pmouseY - mouseY) * 5, abs(pmouseX - mouseX) + abs(pmouseY - mouseY));
  line(pmouseX, pmouseY, mouseX, mouseY);
}
