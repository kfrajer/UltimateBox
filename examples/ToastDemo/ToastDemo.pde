//import com.UltimateBox;


//INSTRUCTIONS:
//
// Created a new toast with every mouse clicked
// Toggle queueing toast messages with any key 

UToast toast;

void setup() {
  size(640, 480);
  toast=new UToast(this);
}

void draw() {
  background(120);
}

void mouseReleased() {
  toast.create("Hello World @ "+hour()+":"+minute()+":"+second()+"."+nf(millis()%1000, 3, 0));
}

void keyReleased() {
  toast.queueFlag=!toast.queueFlag;
  println("It is now ",toast.queueFlag);
}
