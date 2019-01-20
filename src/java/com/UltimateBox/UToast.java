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
//import java.util.ArrayList<E>;

public class UToast {


  /**
   * This class displays a toast message with short duration by default.
   * If queueFlag is enabled, all messages are loaded in the queue and 
   * display as FIFO. Otherwise, everytime a toast is created, the
   * latest will be displayed overriding any previous toast created.
   * 
   * @author  Krisfrajer
   * @author  Chrisir
   * @since   1.0
   */

  protected class ToastMessage {

    final static int INIT_TRANS=250;
    final static int END_TRANS=300;
    final static int SHORT_TOAST=1000;
    final static int LONG_TOAST=3000;

    protected String msg;
    protected int alphaCh;
    protected int outlineColor;
    protected int bgcolor;
    protected int fontColor;
    protected int curve_r_px;
    protected int duration_ms;

    public ToastMessage(String message, int duration) {
      alphaChannel(255);
      msg=message;

      if (duration>LONG_TOAST)
        duration=LONG_TOAST;

      duration_ms=INIT_TRANS+duration+END_TRANS;
    }

    public void updateStyle() {
      outlineColor=p.color(15, alphaCh);
      bgcolor=p.color(144, alphaCh);
      fontColor=p.color(15, alphaCh);
      curve_r_px=10;
    }

    //public void alphaChannel(int alphaChannel) {
    //  alphaCh=0;
    //  updateStyle();
    //}

    public void alphaChannel(int timeLeft) {

      if (timeLeft>duration_ms-INIT_TRANS) {
        int aux=INIT_TRANS-(timeLeft-(duration_ms-INIT_TRANS));
        alphaCh= (int)((aux)*1.0/INIT_TRANS * 255);
      } else if (timeLeft<END_TRANS) {
        int aux=END_TRANS - (END_TRANS-timeLeft);
        alphaCh= (int)(aux*1.0/END_TRANS * 255);
      } else {
        alphaCh=255;
      }      

      //if(alphaCh!=255)p.println(timeLeft,alphaCh);

      updateStyle();
    }
  }

  java.util.ArrayList<ToastMessage> msgStack;
  protected boolean enabledFlag;
  public boolean queueFlag;

  /**
   * Parent handle to hook to 
   * active user's process
   */
  protected PApplet p;

  protected float locx, locy; 
  protected int targetTime_ms;

  public UToast(PApplet parent) {
    p=parent;
    msgStack = new java.util.ArrayList<ToastMessage>();
    prepareToast();
  }

  /**
   * Creates a toast with short duration
   * @param message Message to display
   */
  public void create( String message) {
    create(message, ToastMessage.SHORT_TOAST);
  }

  /**
   * Creates a toast with custom duration
   * If multiple toast are created, they will be handled FIFO
   * If toast are already in the stack, no need to fire toast
   * as it will be manage by stackManager
   * @param message Message to display
   * @param duration Time message will last in msecs
   */
  public void create( String message, int duration) {

    if (queueFlag==false) {
      resetQueue();
    }

    msgStack.add(new ToastMessage(message, duration));
    fireToast();
  }

  private void prepareToast() {

    p.registerMethod("dispose", this);

    locx=p.width*0.5f;
    locy=p.height*0.9f;

    resetTargetTime();  //Init
    enabledFlag=false;
    queueFlag=true;
  }

  public void draw() {

    ToastMessage ctoast=msgStack.get(0);

    int wrec=p.ceil(p.textWidth(ctoast.msg)*1.20f);
    int hrec=p.ceil((p.textAscent()+p.textDescent())*1.20f);    

    ctoast.alphaChannel(targetTime_ms-p.millis());

    p.pushMatrix();
    p.pushStyle();

    p.fill(ctoast.bgcolor);
    p.stroke(ctoast.outlineColor);
    p.rectMode(PApplet.CENTER);
    p.rect(locx, locy, wrec, hrec, ctoast.curve_r_px);

    p.fill(ctoast.fontColor);
    p.textAlign(PApplet.CENTER, PApplet.CENTER);
    p.text(ctoast.msg, locx, locy);

    p.popStyle();
    p.popMatrix();
  }
  
  public void resetTargetTime() {
    targetTime_ms=-1;
  }

  public void resetQueue() {
    msgStack.clear();
    resetTargetTime();
  }

  public void post() {
    if ( p.millis() >= targetTime_ms) {
      resetTargetTime();
      stackManager();
    }
  }

  protected void fireToast() {

    if (!enabledFlag) {
      p.registerMethod("draw", this);
      p.registerMethod("post", this);
      enabledFlag=true;
      resetTargetTime();
    }   

    if (targetTime_ms<0) {
      targetTime_ms = p.millis()+msgStack.get(0).duration_ms;
    }

    //p.println("TargetTime", targetTime_ms, "duration", msgStack.get(0).duration_ms );
    //p.println("Enabled called");
  }


  protected void stackManager() {

    if (msgStack.size()!=0) {

      if (queueFlag==false) {
        resetQueue();
      } else {
        msgStack.remove(0);
      }
    }

    if (msgStack.size()==0) {
      dispose();
    } else {
      fireToast();
    }
  }

  public void dispose() {	

    //p.println("Dispose called");
    enabledFlag=false;
    p.unregisterMethod("draw", this);
    p.unregisterMethod("post", this);
  }
}
