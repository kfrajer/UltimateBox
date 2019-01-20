package com.UltimateBox;


//references: https://github.com/processing/processing/blob/master/core%2Fsrc%2Fprocessing%2Fcore%2FPApplet.java#L5309   To L5875
//REFERENCES: 
//REFERENCES:



/*
  This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation, version 2.1.
 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.
 
 You should have received a copy of the GNU Lesser General
 Public License along with this library; if not, write to the
 Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 Boston, MA  02111-1307  USA
 */


//TO IMPLEMENT: https://forum.processing.org/two/discussion/22791/export-gif-file#latest

//INSTRUCTIONS:
//         *--
//         *--
//         *--
//         *--

//===========================================================================
// IMPORTS:
import processing.core.*;



/*

 public class UImageGenerator{
 
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
 
 //@Override
 public PImage loadImage(String filename) {
 if (printFlag==true)
 println("HERe we go: " + filename);
 
 return p.loadImage(filename, null);
 }
 
 */

public class UImageGenerator {

  /**
   * This class displays a loading arc. It is a demonstration
   *
   */


  /**
   * Parent handle to hook to 
   * active user's process
   */
  protected PApplet p;

  final public static int SAME_MODE=0;
  final public static int ORDER_NUMERIC_MODE=1;
  final public static int RANDOM_COLOR_MODE=2;

  final public int DEFAULTW=100;
  final public int DEFAULTH=100;

  public int customW=DEFAULTW;
  public int customH=DEFAULTH;

  public boolean testEnabled=false;
  public int counter=0;
  public int mode=SAME_MODE;

  public int ww=100;
  public int hh=100;


  /**
   * Constructor for creating a TestSession
   * 
   * @param parent The parent PApplet (Processing sketch) that uses this instance
   */
  public UImageGenerator(PApplet parent) { 
    p=parent;
    initState();
  }

  /**
   * Init state of TestSession
   *
   */
  public void initState() {
    testEnabled=false;
    counter=-1;
    mode=SAME_MODE;

    prepareForNextImage();
  }

  /**
   * Init next width and height for next iamge to load in case they are not provided
   * It also increases the counter in case of using a mode relying on this counter.
   */
  public void prepareForNextImage() {
    ww=customW;
    hh=customH;
    counter++;
  }

  /**
   * Loads image using filename and default (or re-defined global) image dimensions.   
   * 
   * @param fn File name of image
   */
  public PImage loadImage(String fn) {
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

  public PImage loadImage(String fn, float aw, float ah) {
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
  public PImage loadImage(String fn, int aw, int ah) {
    PGraphics pg;
    ww=aw;
    hh=ah;

    checkProvidedDimension();  //Adjust  ww,hh values, if needed

    p.println("Now " + ww + " " + hh );

    if (testEnabled==false) {
      PImage obj=p.loadImage(fn);
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
  public PGraphics getSamePattern() {
    PGraphics pg;
    pg = p.createGraphics(ww, hh);
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
  public PGraphics  getNumericPattern() {
    PGraphics pg; 
    pg = p.createGraphics(ww, hh);
    pg.beginDraw();
    pg.background(102);
    pg.textAlign(PApplet.CENTER, PApplet.CENTER);
    pg.fill(p.random(200), p.random(180), p.random(200));
    pg.textSize(0.25f*p.max(ww, hh));
    pg.text(""+counter, pg.width>>1, pg.height>>1);
    pg.endDraw();

    return pg;
  }

  /**
   * Creates an image with a chessboard pattern of random colors
   * 
   * @return An image object with a colorful pattern
   */
  public PGraphics  getRandomPattern() {
    PGraphics pg; 
    float dw=ww*0.10f;
    float dh=hh*0.10f;

    int[] r2={(int)p.random(5, 255), 0};
    r2[1]=(int)p.random(r2[0]-4, 255);

    int[] g2={(int)p.random(5, 255), 0};
    g2[1]=(int)p.random(g2[0]-4, 255);

    int[] b2={(int)p.random(5, 255), 0};
    b2[1]=(int)p.random(b2[0]-4, 255);

    pg = p.createGraphics(ww, hh);
    pg.beginDraw();
    pg.rectMode(PApplet.CORNER);
    for (int i=0; i<ww; i+=dw) {
      for (int j=0; j<hh; j+=dh) {
        pg.fill(p.random(r2[0], r2[1]), p.random(g2[0], g2[1]), p.random(b2[0], b2[1]));
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
  public void checkProvidedDimension() {

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
