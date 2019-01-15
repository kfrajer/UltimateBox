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

/**
 * This class displays a loading arc. It is a demonstration
 * used to start building the library in the ultimatebox library
 * project. This demonstrations only shows how to compile the library
 * using either gradle or java CLI. It also shows how to create the 
 * documentation.
 *
 * <p>Possible support to <tt>Eclipse</tt> (Under consideration...)
 *
 * <p>Quick usage of javadoc to generate html documentation:
 * Use the tag p (no closing one) to signal a new paragraph. Use tt or i to
 * indicate <tt>code/monospace</tt> or <i>italics</i> font. <b>Would</b> b and u <u>work</u> as well? 
 * Notice these tags need a closing tag. More info at: <a href="https://www.oracle.com/technetwork/articles/java/index-137868.html">Oracle Javadocs</a>
 *
 * <p>Notice that only fields and functions with public/protected/private descriptors
 * are published in the reference. 
 * 
 * <p>To access the reference, go to the generated reference/javadoc folder and open the <i>index.html</i> 
 * file in your favorite browser. Notice a tmp folder containing building info is located in <i>library/tmp/javadoc</i>
 *
 *
 * @author  Kf
 * @author  Chrisir
 * @since   1.0
 */
public class UFooter{

    /**
     * Parent handle to hook to 
     * active user's process
     */
    protected processing.core.PApplet p;   
    protected PFont pfontNormal;
    protected String msg;

    // consts colors

    final public int BLACK = 0;
    final public int WHITE = 0xffffffff;
    final public int RED   = 0xffff0000;
    final public int GREEN = 0xff00ff00;
    final public int BLUE  = 0xff0000ff;
    final public int GRAY =  0xff888888;

    
    /**
     * Constructor: TBD 
     * @param parent Handdle of active sketch calling this object
     * @param message to display
     */
    public UFooter(processing.core.PApplet parent,String message) {
	p=parent;
	msg=message;
        pfontNormal = p.createFont("Arial", 12);   // small
    }

     /**
     * UFooterLeft: TBD
     */
    public void UFooterLeft() {
	UFooterLeft(msg);
    }

    /**
     * UFooterLeft: TBD
     * @param message to display
     */
    public void UFooterLeft(String message) {
	// different footers
	// box
	msg=message;
	p.fill(GRAY);
	p.noStroke();  
	p.rect(0, p.height-21, 
	       p.width, 22);
	// line on the box   
	p.stroke(BLACK);
	p.line( 0, p.height-21, 
		p.width, p.height-21); 

	//  message 
	p.textFont(pfontNormal); // small font "pfontNormal" is default
	p.fill(WHITE);  // white
	p.text(message, 6, p.height-6);
    }

    /**
     * UFooterCenter: TBD
     */
    public void UFooterCenter() {
	UFooterCenter(msg);
    }

    /**
     * UFooterCenter: TBD
     * @param message to display
     */
    public void UFooterCenter(String message) {
	// different footers
	// box 
	msg=message;
	p.fill(GRAY);
	p.noStroke();  
	p.rect(0, p.height-21, 
	       p.width, 22);
	// line on the box   
	p.stroke(BLACK);
	p.line( 0, p.height-21, 
		p.width, p.height-21); 

	//  message 
	p.textFont(pfontNormal); // small font "pfontNormal" is default
	p.fill(WHITE);  // white
	p.textAlign(PApplet.CENTER);   // Middle 
	p.text(message, p.width/2, p.height-6);
	p.textAlign(PApplet.LEFT);       // LEFT is default, restore default
    }

    /**
     * UFooterRight: TBD
     */
    public void UFooterRight() {
	UFooterRight(msg);
    }

    /**
     * UFooterRight: TBD
     * @param message to display
     */
    public void UFooterRight(String message) {
	// different footers
	// box 
	msg=message;
	p.fill(GRAY);
	p.noStroke();  
	p.rect(0, p.height-21, 
	       p.width, 22);
	// line on the box   
	p.stroke(BLACK);
	p.line( 0, p.height-21, 
		p.width, p.height-21); 

	//  message 
	p.textFont(pfontNormal); // small font "pfontNormal" is default
	p.fill(WHITE);  // white
	p.textAlign(PApplet.RIGHT);     // RIGHT 
	p.text(message, p.width-8, p.height-6);
	p.textAlign(PApplet.LEFT);       // LEFT is default, restore default
    }
}
