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


public class UArrow3D {

  // some color consts
  final static int RED = 0xffff0000;        //color(255, 0, 0);
  final static int GREEN = 0xff00ff00;      //color(0, 255, 0);
  final static int BLUE = 0xff0000ff;       //color(0, 0, 255);
  final static int LIGHTGRAY = 0xffCCCCCC;  //color(111);

  /**
   * This class displays .... It is a demonstration...
   * 
   */

  /**
   * Parent handle to hook to 
   * active user's process
   */
  protected PApplet p;

  float xpos;
  float ypos;
  float zpos; 
  float anglex; 
  float angley; 
  float anglez;

  public UArrow3D(PApplet parent, float xpos, float ypos, float zpos, 
    float anglex, float angley, float anglez) {

    p=parent;
    p.registerMethod("draw", this);
    this.xpos=xpos;
    this.ypos=ypos;
    this.zpos=zpos; 
    this.anglex=anglex; 
    this.angley=angley; 
    this.anglez=anglez;

    // The point xpos,ypos,zpos is the point at the tip of the arrow.
    // The angles are the angles towards it (radians).
  }


  public void draw() {

    p.println("arrow draw");

    // points in 2D
    final int[] x = {
      -50, 0, 50, 25, 25, -25, -25, -50
    };
    final int[] y = {
      50, 0, 50, 50, 100, 100, 50, 50
    };

    // how thick is the arrow (1/2 of it)
    final int halfOfTheThickness = 12; 

    p.pushMatrix(); 

    p.translate(xpos, ypos, zpos);
    p.rotateX(anglex);
    p.rotateY(angley);
    p.rotateZ(anglez);

    // all no Stroke
    p.noStroke();

    // arrow Form - ceiling 
    p.fill(RED); // RED
    p.beginShape();
    for (int i = 0; i<x.length; i++) {
      p.vertex(x[i], y[i], -halfOfTheThickness);
    }
    p.endShape(PApplet.CLOSE);
    //
    // arrow Form - floor
    p.fill(RED); // BLUE
    p.beginShape();
    for (int i = 0; i<x.length; i++) {
      p.vertex(x[i], y[i], halfOfTheThickness);
    }
    p.endShape(PApplet.CLOSE);
    //
    // walls of the arrow
    p.fill(BLUE); //  GREEN
    p.beginShape(PApplet.QUAD_STRIP);
    for (int i = 0; i<x.length; i++) {
      p.vertex(x[i], y[i], -halfOfTheThickness);
      p.vertex(x[i], y[i], halfOfTheThickness);
    }
    p.endShape(PApplet.CLOSE);

    p.popMatrix();
  } // func
  //
}
