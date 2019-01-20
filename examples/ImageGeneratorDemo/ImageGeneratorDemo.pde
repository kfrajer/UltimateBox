//import com.UltimateBox;

UImageGenerator uimg;

boolean printFlag=true;
int n;
ArrayList<PImage> imgs;

void setup() {
  size(640, 480);
  textAlign(CENTER, CENTER);

  fill(255);
  strokeWeight(2);

  uimg = new UImageGenerator(this);
  uimg.testEnabled=true;
  uimg.mode=2;
  uimg.customW=200;
  uimg.customH=200; 

  imgs=new ArrayList<PImage>();
  n=64;

  float dw=width*(sqrt(n)/n);
  float dh=height*(sqrt(n)/n);

  for (int i=0; i<n; i++) {
    PImage aimg=uimg.loadImage("pic0.jpg", dw, dh);
    imgs.add(aimg);
  }

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
