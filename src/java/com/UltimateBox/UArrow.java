
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


public class UArrow {

    /**
     * This class displays .... It is a demonstration...
     * 
     */

    /**
     * Parent handle to hook to 
     * active user's process
     */
    protected PApplet p;

    int x1=20;
    int y1=50;
    int x2=80;
    int y2=50;
    int sense=2;  //0: right, 1: bottom, 2: left 3:top

    int arrowStrokeWeight;
    int arrowLen;
    int arrowWidth;

    color arrowColor;
    //DIS*004 color arrowBGColor;

    boolean dashArrow;
    int spacing;

    private int hlen;
    private int hh;

    PGraphics arrPG;


    public ArrowPG(PApplet parent, int x11, int y11, int x22, int y22, int sen, int lenHFactor, int widthHFactor ) {
	p=parent;
	x1=x11;
	y1=y11;
	x2=x22;
	y2=y22;
	sense=sen;
	arrowStrokeWeight=18;                      //12  18
	arrowLen=arrowStrokeWeight*lenHFactor;     //3   6
	arrowWidth=arrowStrokeWeight*widthHFactor; //3   4

	arrowColor=color(250, 140, 20);
	//DIS*004 arrowBGColor=color(140, 0);

	setArrowAsSolid();
	spacing=arrowStrokeWeight;
	hlen=(x2-x1)/2;
	hh=(y2-y1)/2;

	arrPG=p.createGraphics(p.round(hlen*2.25), p.round(hh+arrowWidth));
    }

    public void draw() {

	p.pushStyle();
	//p.background(140);
	p.pushMatrix();
	p.translate(x1+hlen, y1+hh);
	p.rotate(sense*90.0*PI/180.0);

	p.strokeCap(PApplet.SQUARE);
	p.strokeWeight(arrowStrokeWeight);
	p.stroke(arrowColor);

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
	    //line(tr, 0, hlen-arrowLen, 0);           //TANDEM  +++
	    //line(tr, 0, hlen-arrowLen*3/4, 0);       //TANDEM  +++
	    //line(tr, 0, hlen-arrowLen*3/4, 0);         //TANDEM  oooo (disable)
	}

	p.noFill();
	p.strokeWeight(arrowStrokeWeight);
	p.strokeJoin(PApplet.MITER);
	p.beginShape();
	p.stroke(arrowColor); 
	//noStroke();         //By disabling stroke and enabling fill, when the arrow is set into a lower alpha value, there is no artifact due to overlaping arrow's sections
	//fill(arrowColor);

	p.vertex(hlen-arrowLen, -arrowWidth/2);
	p.vertex(hlen, 0);
	p.vertex(hlen-arrowLen, arrowWidth/2);

	p.endShape();

	p.popMatrix();
	p.popStyle();
    }

    public void setStrokeColor(color c) {
	arrowColor=c;
    }

    public color getStrokeColor() { 
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
    }

    public color getStrokeWeight() { 
	return arrowStrokeWeight;
    }

    public void setStrokeSense(int c) {
	sense=c;
    }

    public color getStrokeSense() { 
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
}
