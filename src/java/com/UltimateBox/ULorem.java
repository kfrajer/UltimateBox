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

public final class ULorem {

    /**
     * This class displays a .... It is a demonstration...
     *
     */

    final public static String lorem="Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    static protected int currentLength;

    /**
     * Constructor 
     * Private simulating a pure static class 
     */
    private ULorem(){

        //REFERENCE: https://github.com/mgeisler/lipsum
	//REFERENCE:
	currentLength = lorem.length();
    }

    /**
     * Returns a lorem string of standard size
     */
    public static String ULorem(){
	return(lorem);
    }

    /**
     * Returns a lorem string based on specified length.
     * Returned value is built by using internal lorem string 
     * of defualt size
     * @param len Length of the lorem string to output
     */
    public static String ULorem(int len){
	String ret="";

	if(len < lorem.length())
	    return lorem.substring(0,len);

	int whole = len / lorem.length();
	int reminder = len % lorem.length();
	
	for(int i=0;i<whole;i++){
	    ret += lorem;
	}
	
	ret+=lorem.substring(0,reminder);	
	
	return(ret);
    }
}
