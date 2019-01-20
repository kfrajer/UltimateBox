//import com.UltimateBox;

//REFERENCE: Making a library using CLI: https://discourse.processing.org/t/including-shared-pde-files-across-projects/1701/14

//import com.UltimateBox.DelaySignArc.DelaySignArc;


DelaySignArc delayIndicator;

void setup() {
  delayIndicator = new DelaySignArc(this);
}

void draw() {
  background(50);
  delayIndicator.display();
}
