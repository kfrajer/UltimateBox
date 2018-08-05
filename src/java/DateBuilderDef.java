// Do nt modify this class. Modify test case demo
class DateBuilder {

  final String INVALID="INVALID";
  String lastInquire;
  String format;
  StringList data;

  DateBuilder() {
    lastInquire="";
    format=defaultFormat();
    data=new StringList();
  }

  String defaultFormat() {
    return "$DD/$MO - $HH:$MM";  //2157-1257
  }


  String printCurrentDate() {
    return printCurrentDate(format);
  }

  String printCurrentDate(String fmt) { 

    String outdate="";
    data.clear();
    dateTokenizer(fmt);
    outdate=buildDate();

    //Update memory
    lastInquire=outdate;
    format=fmt;

    return outdate;
  }

  void dateTokenizer(String instr) {

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

  String buildDate() {
    String ss="";

    for (String tok : data) {
      if (tok.length()>0) {
        if (tok.charAt(0)=='$') {
          switch(tok) {
          case "$YY":
            ss+=nf(year(), 2);
            break;
          case "$MO":
            ss+=nf(month(), 2);
            break;
          case "$DD":
            ss+=nf(day(), 2);
            break;
          case "$HH":
            ss+=nf(hour(), 2);
            break;
          case "$MM":
            ss+=nf(minute(), 2);
            break;
          case "$SS":
            ss+=nf(second(), 2);
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

  //Attemps to extract token of the form $xx. If fails, it assigns INVALID. 
  //Return i+3 so to read the next char in source string 
  int validateDateToken(String str, int idxStr) {

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

  boolean isALetter(char c) {    
    return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
  }
  boolean isANumber(char c) {    
    return (c >= '0' && c <= '9');
  }  
  boolean isAlphaNumeric(char c) {
    return isALetter(c) || isANumber(c);
  }

  char upperCase(char c) {

    if (isANumber(c)) return c;

    if (c >= 'a' && c <= 'z') return char(int(c)+(int('A')-int('a')));

    return  c; //Nothing to do
  }

  String testCase(String fmt) {

    String ss = "Format requested\t>>"+fmt+"<<\n\tResult\t>>"+printCurrentDate(fmt)+"<<";
    return ss;
  }
}
