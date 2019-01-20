package com.UltimateBox;


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

import processing.core.*;


public class UArrow2D {

  final static public int RIGHT_SENSE=0;
  final static public int BOTTOM_SENSE=90;
  final static public int LEFT_SENSE=180;
  final static public int TOP_SENSE=270;

  /**
   * This class displays .... It is a demonstration...
   * 
   */

  /**
   * Parent handle to hook to 
   * active user's process
   */
  protected PApplet p;

  public int x1;
  public int y1;
  public int x2;
  public int y2;
  public float sense;  //0: right, 1: bottom, 2: left 3:top

  public int   arrowStrokeWeight;  //Line width for head and arrow body
  public float arrowLen;           //Head length: Percentage of arrowStrokeWeight
  public float arrowHeight;         //Head height: Percentage of arrowStrokeWeight

  public int arrowColor;
  //DIS*004 color arrowBGColor;

  public boolean dashArrow;
  public int spacing;

  float lenHFactor;
  float heightHFactor;

  private int hlen; //headAndBodyHalfLength
  private int hh;   //headHalfHeight

  //PGraphics arrPG;


  public UArrow2D(PApplet parent) {
    p=parent;
    SetLeftSolidArrow();
  }

  public UArrow2D(PApplet parent, int x11, int y11, int x22, int y22) {
    p=parent;
    createUArrow2D(x11, y11, x22, y22, LEFT_SENSE, 3, 3);
  }

  public UArrow2D(PApplet parent, int x11, int y11, int x22, int y22, int sen, float lenHFactor, float heightHFactor ) {
    p=parent;
    createUArrow2D(x11, y11, x22, y22, sen, lenHFactor, heightHFactor);
  }  

  /**
   * Arrow is draw from -hlen to hlen
   * Arrow head length defined by line thickness * lenHFactor
   * Arrow head height defined by line thickness * heightHFactor
   * Direction defined by sense, a number from 0 to 360 degrees
   * Currently head len and length depends on thickness variable
   * Defining thickness corrects for arrow body to overlap head tip
   * Arrow is draw from its center. 
   * Future improvements. Draw arrow from one end and using sense, define where the head is placed.
   */
  public void createUArrow2D( int x11, int y11, int x22, int y22, int sen, float _lenHFactor, float _heightHFactor ) {

    x1=x11;
    y1=y11;
    x2=x22;
    y2=y22;

    sense=sen;

    lenHFactor   = _lenHFactor;
    heightHFactor = _heightHFactor;

    setStrokeWeight(18);
    arrowColor=p.color(250, 140, 20);
    //DIS*004 arrowBGColor=color(140, 0);
    setArrowAsSolid();   

    hlen    = (x2-x1)/2;  
    hh      = (y2-y1)/2;  

    //arrPG=p.createGraphics(PApplet.round(hlen*2.25f), PApplet.round(hh+arrowHeight));
  }


  public void draw() {

    p.pushStyle();
    p.pushMatrix();
    p.translate(x1+hlen, y1+hh);
    p.rotate(sense * PApplet.PI / 180.0f);

    drawArrow();

    p.popMatrix();
    p.popStyle();
  }

  void drawArrow() {

    //Draws body
    p.stroke(arrowColor);
    p.strokeCap(PApplet.SQUARE);
    p.strokeWeight(arrowStrokeWeight);


    if (dashArrow==false) {
      //line(-hlen, 0, hlen-arrowLen, 0);
      //line(-hlen, 0, hlen-arrowLen*3/4, 0);
      p.line(-hlen, 0, hlen-arrowLen*1/4, 0);
    } else {
      //DIS*004 stroke(arrowBGColor);
      int skip=spacing*3;
      int tr;
      //for (tr=-hlen; tr<hlen-spacing-arrowLen-skip; tr+=spacing+skip) {   //TANDEM  +++
      for (tr=-hlen; tr<hlen-spacing-0-skip; tr+=spacing+skip) {     //TANDEM  oooo
        p.line(tr, 0, tr+skip, 0);
      }
      //line(tr, 0, hlen-arrowLen, 0);             //TANDEM  +++
      //line(tr, 0, hlen-arrowLen*3/4, 0);         //TANDEM  +++
      //line(tr, 0, hlen-arrowLen*3/4, 0);         //TANDEM  oooo (disable)
    }

    //Draws head
    p.noFill();
    p.strokeWeight(arrowStrokeWeight);
    p.strokeJoin(PApplet.MITER);

    p.beginShape();
    p.stroke(arrowColor); 
    //noStroke();         //By disabling stroke and enabling fill, when the arrow is set into a lower alpha value, there is no artifact due to overlaping arrow's sections
    //fill(arrowColor);
    p.vertex(hlen-arrowLen, -arrowHeight/2);
    p.vertex(hlen, 0);
    p.vertex(hlen-arrowLen, arrowHeight/2);
    p.endShape();


    drawAids();
  }

  public void drawAids() {

    //BODY
    p.noStroke();
    p.fill(0, 255, 0);
    p.ellipse(-hlen, 0, 2, 2);             //Arrowless end
    p.ellipse(0, 0, 5, 5);                 //Center
    p.ellipse(hlen-arrowLen*1/4, 0, 7, 7); //Arrow end

    //HEAD
    p.fill(100, 0, 250);
    p.ellipse(hlen-arrowLen, -arrowHeight/2, 5, 5);
    p.ellipse(hlen, 0, 5, 5);
    p.ellipse(hlen-arrowLen, arrowHeight/2, 5, 5);
  }

  public void setStrokeColor(int c) {
    arrowColor=c;
  }

  public int getStrokeColor() { 
    return arrowColor;
  }

  //DIS*004 void setBGColor(color c) {
  //DIS*004   arrowBGColor=c;
  //DIS*004 }

  //DIS*004 color getBGColor() { 
  //DIS*004   return arrowBGColor;
  //DIS*004 }

  public void setStrokeWeight(int c) {
    arrowStrokeWeight=c;

    //arrowStrokeWeight = 18;                      //12  18
    arrowLen   = arrowStrokeWeight*lenHFactor;     //3   6
    arrowHeight = arrowStrokeWeight*heightHFactor; //3   4
    spacing    = arrowStrokeWeight;
  }

  public int getStrokeWeight() { 
    return arrowStrokeWeight;
  }

  public void setSense(float c) {
    sense=c;
  }

  public float getSense() { 
    return sense;
  }

  public void setArrowAsDash() {
    dashArrow=true;
  }

  public boolean isDashArrow() { 
    return dashArrow;
  }

  public void setArrowAsSolid() {
    dashArrow=false;
  }

  public boolean isSolidArrow() { 
    return !isDashArrow();
  }

  public void SetLeftSolidArrow() {

    int x1=50;
    int y1=200;
    int x2=250;
    int y2=200;

    //sense=LEFT_SENSE;

    int thickness=12;
    float perHeadLength=4;
    float perHeadHeight=4;

    createUArrow2D(x1, y1, x2, y2, LEFT_SENSE, perHeadLength, perHeadHeight);

    setStrokeWeight(thickness);
    setArrowAsSolid();
  }

  public void SetLeftDashedArrow() {
    SetLeftSolidArrow();
    setArrowAsDash();
  }

  public void SetRightSolidArrow() {
    SetLeftSolidArrow();
    sense=RIGHT_SENSE;
  }

  public void SetRightDashedArrow() {
    SetLeftSolidArrow();
    sense=RIGHT_SENSE;
    setArrowAsDash();
  }

  public void SetTopSolidArrow() {
    SetLeftSolidArrow();
    sense=TOP_SENSE;
  }

  public void SetTopDashedArrow() {
    SetLeftSolidArrow();
    sense=TOP_SENSE;
    setArrowAsDash();
  }

  public void setRandomSenseSolidArrow() {
    SetLeftSolidArrow();
    sense=p.random(0, 360);
  }
}
