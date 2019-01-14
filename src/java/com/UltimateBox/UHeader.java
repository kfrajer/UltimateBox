package com.UltimateBox.DelaySignArc;

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


public class UHeader{

    /**
     * Parent handle to hook to 
     * active user's process
     */
    protected PApplet p;
    PFont pfontNormal; 
    PFont pfontHeadline; 

    /**
     * TBD
     */    
    public UHeader(PApplet parent, String textHeadline, int colorHeadline ){

	p=parent;
	pfontNormal   = p.createFont("Arial", 12); // small
        pfontHeadline = p.createFont("Arial", 19); // big 
	p.textFont (pfontNormal);                  // small is default
	header(textHeadline, colorHeadline);
    }

    /**
     * TBD
     */
    public void header(String textHeadline, int colorHeadline) {  
	// show Headline : long form
	// set aligement, color and font
	p.textAlign(PApplet.CENTER);  
	p.fill(colorHeadline);   
	p.textFont(pfontHeadline);
	// show text 
	p.text(textHeadline, 
	       p.width/2, 19);
	// reset values to default 
	p.textAlign(PApplet.LEFT);       // LEFT is default, restore default 
	p.textFont(pfontNormal); // small font "pfontNormal" is default, restore default
    }//method 
    
}
