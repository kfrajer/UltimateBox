//import com.UltimateBox;


//INSTRUCTIONS:
//
// Created a new toast with every mouse clicked
// Toggle queueing toast messages with any key 

UToast toast;
DateBuilderDef cdate;


void setup() {
  size(640, 480);
  toast=new UToast(this);
  cdate=new DateBuilderDef(this);
}

void draw() {
  background(120);
}

void mouseReleased() {
  toast.create("Hello World @ "+cdate.printCurrentDate("$DD-$MO-$YY -> $HH:$MM:$SS.$MS "));
}

void keyReleased() {
  toast.queueFlag=!toast.queueFlag;
  println("It is now ",toast.queueFlag);
}
