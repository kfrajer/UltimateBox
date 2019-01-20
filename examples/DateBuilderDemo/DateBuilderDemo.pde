//import com.UltimateBox.DateBuilderDef; 

DateBuilderDef cdate;


void setup() {
  size(640, 480);

  cdate = new DateBuilderDef(this);
  textAlign(CENTER, CENTER);
  noLoop();
}

void draw() {
  background(0);
  text(cdate.printCurrentDate(), 0, 0);
  println(cdate.testCase(cdate.defaultFormat()));
}

void mouseReleased() {
  redraw();
}
