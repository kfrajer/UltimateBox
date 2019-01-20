//import com.UltimateBox;


//INSTRUCTIONS:
//
//Press a key from 1 to 4 to change the image pattern.
//This example assumes you have an image called data0001.jpg in your data/ folder
//- If option 1 is chosen, a sequenced of numbers is placed on each title.
//  Sometimes the starting number is reset to 0 or to a number from 5000 to
//  10000 or it just follows the previous counting history.
//-If 2 is choosen, a random pattern is created in each title
//-For three, the data0001 image is loaded in each title
//-Any other key loads a the same pattern on each title.

UImageGenerator uimg;

boolean printFlag=true;
int n;
ArrayList<PImage> imgs;

void setup() {
  size(640, 480);
  textAlign(CENTER, CENTER);

  fill(255);
  strokeWeight(2);

  surface.setTitle("ImageGenerator: Press either 1..4 to change mode");

  uimg = new UImageGenerator(this);
  uimg.testEnabled=true;
  uimg.mode=UImageGenerator.RANDOM_COLOR_MODE;
  uimg.customW=200;
  uimg.customH=200; 

  imgs=new ArrayList<PImage>();
  n=64;
  generateImages();

  noLoop();
}

void draw() {
  background(0);

  int ctr=0;
  float dw=width*(sqrt(n)/n);
  float dh=height*(sqrt(n)/n);

  for (int i=0; i<width; i+=dw) {
    for (int j=0; j<height; j+=dh) {      
      image(imgs.get(ctr), i, j);//, i+dw, j+dh);
      ctr++;
      //println("Ctr="+ ctr);      
      //println("i*dw="+ i+":"+(i+dw)+" vs. "+j+":"+(j+dh));
    }
  }
}

//void mouseReleased() {
//  generateImages();
//}

void generateImages() {

  float dw=width*(sqrt(n)/n);
  float dh=height*(sqrt(n)/n);

  imgs.clear();
  for (int i=0; i<n; i++) {
    PImage aimg=uimg.loadImage("data0001.jpg", dw, dh);
    imgs.add(aimg);
  }

  redraw();
}

void keyReleased() {

  switch(key) {
  case '1':
    uimg.mode=UImageGenerator.ORDER_NUMERIC_MODE;
    uimg.testEnabled=true;

    //Resets counter to 0 20% of the time, or generates a new starting point 
    //for the counter for another 20% of the time. Otherwise, it increases
    //counter from previous counting history
    float selCounterSet=random(1);
    println("change",selCounterSet);
    if (selCounterSet<0.2) {
      uimg.counter=(int)random(5000, 10000);
    } else if (selCounterSet<0.4) {
      uimg.counter=0;
    }
    break;
  case '2':
    uimg.mode=UImageGenerator.RANDOM_COLOR_MODE;
    uimg.testEnabled=true;
    break;
  case '3':
    uimg.testEnabled=false;
    break;
  default:
    uimg.mode=UImageGenerator.SAME_MODE;
    uimg.testEnabled=true;
    break;
  };

  generateImages();
}
