//import com.UltimateBox.*;

// arrow
UArrow3D myarrow;

// angles (radians)
float incX = -1.1;
float incY = -0.5;
float incZ = -0.4;

// ------------------------------------------------------------

void setup() {
  size(600, 400, OPENGL);
  myarrow= new UArrow3D(this, 300, 66, -60, 
    incX, incY, incZ);
  noLoop();
}

void draw() {
  background(0);
  lights();

  println("main draw");

  pushMatrix(); 
  translate(300, 66, -60);
  noStroke(); 
  fill(0, 255, 0); 
  sphere(5); 
  popMatrix(); 

  // incX += .01;
  // incY += .01;
  // incZ += .02;
  // println(incZ);
  incZ=incZ%TWO_PI;
  //
}
