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


/*
  ColorPicker cp;
  boolean modeColorSelector=true; 
  color col1; 
 
  void setup() 
  {
  size( 500, 500 );
  frameRate( 100 );
 
  cp = new ColorPicker( 10, 10, 400, 400, 255 );
  }
 
  void draw ()
  {
 
 
 
  if (modeColorSelector) {
  background( 80 );
  cp.render();
  fill(255); 
  text("Select a color. Hit d to draw with this color", 
  20, height-9);
  } else
  {
 
  col1=cp.getColor(); 
  stroke(col1); 
  line(mouseX, mouseY, 
  pmouseX, pmouseY);
  fill(111);
 
  noStroke(); 
  rect(0, height-21, width, height);  
  fill(255); 
  text("Hit d to choose another color", 
  20, height-9);
  }
  }
 
  void keyPressed() {
  if (key=='d') { 
  modeColorSelector=!modeColorSelector;
  background( 80 );
  }
  }

*/
// ================================================
 
public class ColorPicker {

    /**
     * This class displays a loading arc. It is a demonstration
     *
     */
    
    int x, y, 
	w, h, 
	c;
    
    PImage cpImage;
    
 
    public ColorPicker ( int x, int y, int w, int h, int c )
    {
	this.x = x;
	this.y = y;
	this.w = w;
	this.h = h;
	this.c = c;
 
	cpImage = new PImage( w, h );
 
	init();
    }
 
    private void init ()
    {
	// draw color.
	int cw = w - 60;
	for ( int i=0; i<cw; i++ ) 
	    {
		float nColorPercent = i / (float)cw;
		float rad = (-360 * nColorPercent) * (PI / 180);
		int nR = (int)(Math.cos(rad) * 127 + 128) << 16;
		int nG = (int)(Math.cos(rad + 2 * PI / 3) * 127 + 128) << 8;
		int nB = (int)(Math.cos(rad + 4 * PI / 3) * 127 + 128);
		int nColor = nR | nG | nB;
 
		setGradient( i, 0, 1, h/2, 0xFFFFFF, nColor );
		setGradient( i, (h/2), 1, h/2, nColor, 0x000000 );
	    }
 
	// draw black/white.
	drawRect( cw, 0, 30, h/2, 0xFFFFFF );
	drawRect( cw, h/2, 30, h/2, 0 );
 
	// draw grey scale.
	for ( int j=0; j<h; j++ )
	    {
		int g = 255 - (int)(j/(float)(h-1) * 255 );
		drawRect( w-30, j, 30, 1, color( g, g, g ) );
	    }
    }
 
    private void setGradient(int x, int y, float w, float h, int c1, int c2 )
    {
	float deltaR = red(c2) - red(c1);
	float deltaG = green(c2) - green(c1);
	float deltaB = blue(c2) - blue(c1);
 
	for (int j = y; j<(y+h); j++)
	    {
		int c = p.color( p.red(c1)+(j-y)*(deltaR/h), p.green(c1)+(j-y)*(deltaG/h), p.blue(c1)+(j-y)*(deltaB/h) );
		cpImage.set( x, j, c );
	    }
    }
 
    private void drawRect( int rx, int ry, int rw, int rh, int rc )
    {
	for (int i=rx; i<rx+rw; i++) 
	    {
		for (int j=ry; j<ry+rh; j++) 
		    {
			cpImage.set( i, j, rc );
		    }
	    }
    }
 
    public void render ()
    {
	p.image( cpImage, x, y );
	if ( mousePressed &&
	     mouseX >= x && 
	     mouseX < x + w &&
	     mouseY >= y &&
	     mouseY < y + h )
	    {
		c = p.get( p.mouseX, p.mouseY );
	    }
	p.fill( c );
	p.rect( x, y+h+10, 20, 20 );
    }
 
    color getColor() {
	return c;
    }
}
//
