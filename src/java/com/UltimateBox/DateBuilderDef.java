
package com.ultimatebox;

import processing.data.StringList;
import processing.core.*;


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

/**
 * Class for generating strings with date-time commands
 * 
 * <p>
 * This class permits the generation of strings with date-time data codes embeded <br>
 * in the actual returning string Codes used follow the standard defined at <br>
 * Java Oracle 
 * More comments....
 * </p>
 * @author Kfrajer/Chrisir
 * 
 * @version 0.10
 * 
 * @since 0.10
 */
public class DateBuilderDef {

    /**
     * This class displays .... It is a demonstration...
     * 
     */

    /**
     * Parent handle to hook to 
     * active user's process
     */
    protected PApplet p;
    
    final String INVALID="INVALID";
    String lastInquire;
    String format;
    StringList data;



    /**
     * Constructor for creating a Stopwatch
     * 
     * @param parent The parent PApplet (Processing sketch) that uses this instance
     */
    public DateBuilderDef(PApplet parent) {
	p=parent;
	lastInquire="";
	format=defaultFormat();
	data=new StringList();
	//   check   C:\Users\C\Documents\Processing\CMsketches\workDnD\timeStampGeneration
    }

    /**
     * @return Returns the current date and time. This defines the default format
     */
    public String defaultFormat() {
	return "$DD/$MO - $HH:$MM";  //2157-1257
    }

    /**
     *
     *  TBD
     *
     */
    public String printCurrentDate() {
	return printCurrentDate(format);
    }

    /**
     *
     *  TBD
     *
     */
    public String printCurrentDate(String fmt) { 

	String outdate="";
	data.clear();
	dateTokenizer(fmt);
	outdate=buildDate();

	//Update memory
	lastInquire=outdate;
	format=fmt;

	return outdate;
    }

    /**
     *
     *  TBD
     *
     */    
    public void dateTokenizer(String instr) {

	String s="";
	for (int i=0; i<instr.length(); i++) {

	    if (instr.charAt(i)=='$') {

		if (s.length()>0) 
		    data.append(s);

		i=validateDateToken(instr, i);
		s="";  //Reset
	    } else {
		s+=instr.charAt(i);
	    }

	    //End of string, flush content
	    if (i==instr.length()-1) {
		data.append(s);
		s="";
	    }
	}
    }  //END dateTokenizer

    /**
     *
     *  TBD
     *
     */    
    public String buildDate() {
	String ss="";

	for (String tok : data) {
	    if (tok.length()>0) {
		if (tok.charAt(0)=='$') {
		    switch(tok) {
		    case "$YY":
			ss+=p.nf(p.year(), 2);
			break;
		    case "$MO":
			ss+=p.nf(p.month(), 2);
			break;
		    case "$DD":
			ss+=p.nf(p.day(), 2);
			break;
		    case "$HH":
			ss+=p.nf(p.hour(), 2);
			break;
		    case "$MM":
			ss+=p.nf(p.minute(), 2);
			break;
		    case "$SS":
			ss+=p.nf(p.second(), 2);
			break;
		    default:
			ss+=INVALID;
			break;
		    }
		} else {
		    ss+=tok;
		}
	    }
	}

	return ss;
    }

    /**
     *
     *  TBD
     *
     */    
    //Attemps to extract token of the form $xx. If fails, it assigns INVALID. 
    //Return i+3 so to read the next char in source string 
    public int validateDateToken(String str, int idxStr) {

	char s1=' ';
	char s2=' ';

	//Nothing to do as it does not start with $
	if (str.charAt(idxStr)!='$')
	    return idxStr;

	//Not enough chars to read
	if (idxStr+2>=str.length()) {
	    data.append(INVALID);
	    return str.length();  //Ensures end of string is reached
	}

	s1=upperCase(str.charAt(idxStr+1));
	s2=upperCase(str.charAt(idxStr+2));

	if (!isAlphaNumeric(s1)) {
	    data.append(INVALID);
	    return idxStr+1;  //Set for next position to read
	}

	if (!isAlphaNumeric(s2)) {
	    data.append(INVALID);
	    return idxStr+2; //Set for next position to read
	}

	//Ok here we have a valid format
	String tok="$"+s1+s2;
	data.append(tok);


	return idxStr+2;
    }

    /**
     *
     *  TBD
     *
     */    
    public boolean isALetter(char c) {    
	return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
    }

    /**
     *
     *  TBD
     *
     */    
    public boolean isANumber(char c) {    
	return (c >= '0' && c <= '9');
    }

    /**
     *
     *  TBD
     *
     */    
    public boolean isAlphaNumeric(char c) {
	return isALetter(c) || isANumber(c);
    }

    /**
     *
     *  TBD
     *
     */    
    public char upperCase(char c) {

	if (isANumber(c)) return c;

	if (c >= 'a' && c <= 'z') return Character.toUpperCase(c); //p.char(p.int(c)+p.int('A')-p.int('a'));

	return  c; //Nothing to do
    }

    /**
     *
     *  TBD
     *
     */    
    public String testCase(String fmt) {

	String ss = "Format requested\t>>"+fmt+"<<\n\tResult\t>>"+printCurrentDate(fmt)+"<<";
	return ss;
    }
}
