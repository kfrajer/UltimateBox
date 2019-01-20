package com.UltimateBox;

// TO DO:
// Use reference from package malcolm.Pallete2; AND github notes
// AND processing forum post on this topic
// Generate class file

import processing.core.*; 

public class DelaySignArc {

  final public float START_ANGLE=355;

  // simple class for an arc
  
  protected processing.core.PApplet p;

  public float angle=START_ANGLE; 
  public float locx, locy; //pixels
  public float radius;  //degreeds
  public float dChange; //degrees


  // Constructor
  public DelaySignArc(processing.core.PApplet parent) {
    p=parent;
    locx=p.width/2;
    locy=p.height/2;
    radius=p.width/4;
    dChange=4;
  }

  //Constructor, setters for position, radius, color attributes, etc.
  //TBD....

  //Update function to be called when any intrinsic par is changed.
  //TBD...

  //Function setDefualt: default configuration inferred from current settings 

  public void display() {
    // display the arc

    //arc
    p.strokeWeight(20);
    p.noFill();
    p.stroke(222);
    float arc1 = p.radians(angle);

    // usage: arc(x, y, w, h, start, stop, OPEN)
    p.arc(locx, locy, 
      radius, radius, 
      p.radians(dChange), arc1, 
      PApplet.OPEN);

    angle = angle<dChange ? START_ANGLE: angle-dChange;
  } // method 

  public void reset() {
    angle=START_ANGLE;
  }
  //
} // class
//
