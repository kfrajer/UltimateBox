//import com.UltimateBox;

void setup() {
  size(640, 480);
  textAlign(CENTER, CENTER);
  noLoop();
}

void draw() {
  background(0);
  text(ULorem.ULorem(int(random(100,200))), 0, 0,width,height);
}

void mouseReleased(){
 redraw(); 
}
