package com.UltimateBox.UToast;

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

public class UToast {

  
/**
 * This class displays a toast message and 
 * they can have different durations.
 * 
 * @author  Krisfrajer
 * @author  Chrisir
 * @since   1.0
 */

    final int SHORT_TOAST=1000;
    final int LONG_TOAST=3000;
    
    
    /**
     * Parent handle to hook to 
     * active user's process
     */
    protected PApplet p;

    protected float locx,locy;
    protected String msg;
    protected int bgcolor;
    protected int fontColor;    
    protected int duration_ms;
    protected int targetTime_ms;
     
   public UToast(PApplet parent, String message){
	p=parent;
	msg=message;
	prepareToast(SHORT_TOAST);	
    }

    public UToast(PApplet parent, String message, int duration){
	p=parent;
	msg=message;
	prepareToast(duration);	
    }

    private void prepareToast(int duration){
	
	p.registerMethod("dispose", this);
	
        locx=p.width*0.5f;
	locy=p.height*0.9f;
	bgcolor=p.color(120);
	fontColor=p.color(15);
	
	if(duration>LONG_TOAST)
	    duration=LONG_TOAST;
	
	duration_ms=duration;
	targetTime_ms=-1;  //Init
	enableToast();
    }

    public void draw() {

	int wrec=p.ceil(p.textWidth(msg)*1.20f);
	int hrec=p.ceil((p.textAscent()+p.textDescent())*1.20f);
	
	p.pushMatrix();
	p.pushStyle();

	p.fill(bgcolor);
	p.stroke(fontColor);
	p.rectMode(PApplet.CENTER);
	p.rect(locx,locy,wrec,hrec);

	p.textAlign(PApplet.CENTER,PApplet.CENTER);
	p.text(msg,locx,locy);
	
	p.popStyle();
	p.popMatrix();
	
    }

    public void post(){
	if( p.millis() >= targetTime_ms){
	    disableToast();
	}
    }

    protected void enableToast(){
	targetTime_ms = p.millis() + duration_ms;
	p.registerMethod("draw", this);
	p.registerMethod("post", this);
    }


    protected void disableToast(){
	dispose();
    }
    
    protected void dispose(){	
	p.unregisterMethod("draw", this);
	p.unregisterMethod("post", this);
    }

  
  
}
