//REFERENCES: https://github.com/processing/processing/blob/master/core%2Fsrc%2Fprocessing%2Fcore%2FPApplet.java#L5309   To L5875
//REFERENCES: 
//REFERENCES:

//TO IMPLEMENT: https://forum.processing.org/two/discussion/22791/export-gif-file#latest

//INSTRUCTIONS:
//         *--
//         *--
//         *--
//         *--

//===========================================================================
// IMPORTS:


//===========================================================================
// FINAL FIELDS:


//===========================================================================
// GLOBAL VARIABLES:
boolean printFlag=true;

PImage im;
TestSession ts;

ArrayList<PImage> imgs;
int n;


/////OVERRIDING current load image!
//PImage loadImage(String name){
//  println("LOADING: "+name);
//  return super.loadImage(name);
  
//}


//===========================================================================
// PROCESSING DEFAULT FUNCTIONS:

void settings() {
  size(800, 800);
}

void setup() {

  textAlign(CENTER, CENTER);
  //rectMode(CENTER);

  fill(255);
  strokeWeight(2);

  ts = new TestSession(this);
  ts.testEnabled=true;
  ts.mode=2;
  ts.customW=200;
  ts.customH=200;
  //im=ts.loadImage("pic0.jpg");
  //im=loadImage("pic0.jpg");     //Overriding: not useful as I cannot make it into a library

  imgs=new ArrayList<PImage>();
  n=64;

  float dw=width*(sqrt(n)/n);
  float dh=height*(sqrt(n)/n);

  for (int i=0; i<n; i++) {
    PImage aimg=ts.loadImage("pic0.jpg", dw, dh);
    imgs.add(aimg);
  }
}



void draw() {
  background(150);  
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

void keyReleased() {
}

void mouseReleased() {
}



//===========================================================================
// OTHER FUNCTIONS:

@Override
  PImage loadImage(String filename) {
  if (printFlag==true)
    println("HERe we go: " + filename);

  return loadImage(filename, null);
}


class TestSession {

  protected final PApplet parent;
  final int SAME_MODE=0;
  final int ORDER_NUMERIC_MODE=1;
  final int RANDOM_COLOR_MODE=2;

  final int DEFAULTW=100;
  final int DEFAULTH=100;

  int customW=DEFAULTW;
  int customH=DEFAULTH;

  boolean testEnabled=false;
  int counter=0;
  int mode=SAME_MODE;

  int ww=100;
  int hh=100;


  /**
   * Constructor for creating a TestSession
   * 
   * @param par The parent PApplet (Processing sketch) that uses this instance
   */
  TestSession(PApplet par) { 
    parent=par;
    initState();
  }

  /**
   * Init state of TestSession
   *
   */
  void initState() {
    testEnabled=false;
    counter=-1;
    mode=SAME_MODE;

    prepareForNextImage();
  }

  /**
   * Init next width and height for next iamge to load in case they are not provided
   * It also increases the counter in case of using a mode relying on this counter.
   */
  void prepareForNextImage() {
    ww=customW;
    hh=customH;
    counter++;
  }

  /**
   * Loads image using filename and default (or re-defined global) image dimensions.   
   * 
   * @param fn File name of image
   */
  PImage loadImage(String fn) {
    return loadImage(fn, 0, 0);
  }

  /**
   * Loads image using filename and provided width x height dimensions (float type).
   * Notice dimension of image related to this test session are reset at the end to prepare
   * for a next image
   * 
   * @param fn File name of image
   * @param aw Desired width 
   * @param ah Desired height
   */

  PImage loadImage(String fn, float aw, float ah) {
    return loadImage(fn, (int)aw, (int)ah);
  }

  /**
   * Loads image using filename and provided width x height dimensions (int type).
   * Notice dimension of image related to this test session are reset at the end to prepare
   * for a next image
   * 
   * @param fn File name of image
   * @param aw Desired width 
   * @param ah Desired height
   */
  PImage loadImage(String fn, int aw, int ah) {
    PGraphics pg;
    ww=aw;
    hh=ah;

    checkProvidedDimension();  //Adjust  ww,hh values, if needed

    println("Now " + ww + " " + hh );

    if (testEnabled==false) {
      PImage obj=parent.loadImage(fn);
      if (!(aw==0 && ah==0)) { 
        obj.resize(ww, hh);
      }
      return obj;
    }

    switch(mode) {
    case SAME_MODE:
      pg=getSamePattern();
      break;
    case ORDER_NUMERIC_MODE:
      pg=getNumericPattern();
      break;
    case RANDOM_COLOR_MODE:
      pg=getRandomPattern();
      break;
    default:
      pg=getSamePattern();
      break;
    }

    prepareForNextImage();

    return pg.get();
  }

  /**
   * Creates an image with a pre-defined pattern
   * 
   * @return An image object with a default ummutable pattern
   */
  PGraphics getSamePattern() {
    PGraphics pg;
    pg = createGraphics(ww, hh);
    pg.beginDraw();
    pg.background(102);
    pg.stroke(255);
    pg.line(0, 0, pg.width>>1, pg.height>>1);
    pg.endDraw();

    return pg;
  }

  /**
   * Creates a numbered image, the number in the center of the image
   * 
   * @return An image object with an ordered pattern
   */
  PGraphics  getNumericPattern() {
    PGraphics pg; 
    pg = createGraphics(ww, hh);
    pg.beginDraw();
    pg.background(102);
    pg.textAlign(CENTER, CENTER);
    pg.fill(random(200), random(180), random(200));
    pg.textSize(0.25*max(ww, hh));
    pg.text(""+counter, pg.width>>1, pg.height>>1);
    pg.endDraw();

    return pg;
  }

  /**
   * Creates an image with a chessboard pattern of random colors
   * 
   * @return An image object with a colorful pattern
   */
  PGraphics  getRandomPattern() {
    PGraphics pg; 
    float dw=ww*0.10;
    float dh=hh*0.10;

    int[] r2={(int)random(5, 255), 0};
    r2[1]=(int)random(r2[0]-4, 255);

    int[] g2={(int)random(5, 255), 0};
    g2[1]=(int)random(g2[0]-4, 255);

    int[] b2={(int)random(5, 255), 0};
    b2[1]=(int)random(b2[0]-4, 255);

    pg = createGraphics(ww, hh);
    pg.beginDraw();
    pg.rectMode(CORNER);
    for (int i=0; i<ww; i+=dw) {
      for (int j=0; j<hh; j+=dh) {
        pg.fill(random(r2[0],r2[1]), random(g2[0],g2[1]), random(b2[0],b2[1]));
        pg.rect(i, j, i+dw, j+dh);
      }
    }
    pg.endDraw();

    return pg;
  }

  /**
   * Ensures current dimension defined in session are proper for next iamge to be loaded.
   * If not proper, dimension are defined to class'es default: 100x100
   */
  void checkProvidedDimension() {

    if (customW<=0 && customH<=0) {
      customW=DEFAULTW;
      customH=DEFAULTH;
    }

    if (ww<=0 && hh<=0) {
      ww=customW;
      hh=customH;
    } else { 

      //If one is zero, only re-defined if we are in test mode. Otherwise let it
      //handled by the resize() function
      if (testEnabled==true) {
        if (ww<=0) {
          ww=customW;
        } else if (hh<=0) {
          hh=customH;
        }
      }
    }
    //End checking for wwxhh integrity
  }
}