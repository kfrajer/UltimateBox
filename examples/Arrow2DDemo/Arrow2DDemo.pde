//import com.UltimateBox.*;

// arrow
UArrow2D myarrow;


// ------------------------------------------------------------

void setup() {
  size(600, 400, JAVA2D);

  //int x1=20; 
  //int y1=50; 
  //int x2=80; 
  //int y2=50;

  myarrow= new UArrow2D(this);
  surface.setTitle("Press 1 to 6 to change arrow type/sense. Sense now: "+nf(myarrow.sense, 0, 0));


  noLoop();
}

void draw() {
  background(0);
  myarrow.draw();
}

void keyReleased() {

  switch(key) {
  case '1':
    myarrow.SetLeftSolidArrow();
    break;
  case '2':
    myarrow.SetLeftDashedArrow();
    break;
  case '3':
    myarrow.SetRightSolidArrow();
    break;
  case '4':
    myarrow.SetRightDashedArrow();
    break;
  case '5':
    myarrow.SetTopSolidArrow();
    break;
  case '6':
    myarrow.SetTopDashedArrow();
    break;
  default:
    myarrow.setRandomSenseSolidArrow();
    break;
  };

  surface.setTitle("Press 1 to 6 to change arrow type/sense. Sense now: "+nf(myarrow.sense, 0, 0));
  redraw();
}
