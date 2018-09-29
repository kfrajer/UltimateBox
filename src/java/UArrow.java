class ArrowPG {

  int x1=20;
  int y1=50;
  int x2=80;
  int y2=50;
  int sense=2;  //0: right, 1: bottom, 2: left 3:top

  int arrowStrokeWeight;
  int arrowLen;
  int arrowWidth;

  color arrowColor;
  //DIS*004 color arrowBGColor;

  boolean dashArrow;
  int spacing;

  private int hlen;
  private int hh;

  PGraphics arrPG;


  ArrowPG(int x11, int y11, int x22, int y22, int sen, int lenHFactor, int widthHFactor ) {
    x1=x11;
    y1=y11;
    x2=x22;
    y2=y22;
    sense=sen;
    arrowStrokeWeight=18;                      //12  18
    arrowLen=arrowStrokeWeight*lenHFactor;     //3   6
    arrowWidth=arrowStrokeWeight*widthHFactor; //3   4

    arrowColor=color(250, 140, 20);
    //DIS*004 arrowBGColor=color(140, 0);

    setArrowAsSolid();
    spacing=arrowStrokeWeight;
    hlen=(x2-x1)/2;
    hh=(y2-y1)/2;

    arrPG=createGraphics(round(hlen*2.25), round(hh+arrowWidth));
  }

  void draw() {

    pushStyle();
    //background(140);
    pushMatrix();
    translate(x1+hlen, y1+hh);
    rotate(sense*90.0*PI/180.0);

    strokeCap(SQUARE);
    strokeWeight(arrowStrokeWeight);
    stroke(arrowColor);

    if (dashArrow==false) {
      //line(-hlen, 0, hlen-arrowLen, 0);
      //line(-hlen, 0, hlen-arrowLen*3/4, 0);
      line(-hlen, 0, hlen-arrowLen*1/4, 0);
    } else {
      //DIS*004 stroke(arrowBGColor);
      int skip=spacing*3;
      int tr;
      //for (tr=-hlen; tr<hlen-spacing-arrowLen-skip; tr+=spacing+skip) {   //TANDEM  +++
      for (tr=-hlen; tr<hlen-spacing-0-skip; tr+=spacing+skip) {     //TANDEM  oooo
        line(tr, 0, tr+skip, 0);
      }
      //line(tr, 0, hlen-arrowLen, 0);           //TANDEM  +++
      //line(tr, 0, hlen-arrowLen*3/4, 0);       //TANDEM  +++
      //line(tr, 0, hlen-arrowLen*3/4, 0);         //TANDEM  oooo (disable)
    }

    noFill();
    strokeWeight(arrowStrokeWeight);
    strokeJoin(MITER);
    beginShape();
    stroke(arrowColor); 
    //noStroke();         //By disabling stroke and enabling fill, when the arrow is set into a lower alpha value, there is no artifact due to overlaping arrow's sections
    //fill(arrowColor);

    vertex(hlen-arrowLen, -arrowWidth/2);
    vertex(hlen, 0);
    vertex(hlen-arrowLen, arrowWidth/2);

    endShape();

    popMatrix();
    popStyle();
  }

  void setStrokeColor(color c) {
    arrowColor=c;
  }

  color getStrokeColor() { 
    return arrowColor;
  }

  //DIS*004 void setBGColor(color c) {
  //DIS*004   arrowBGColor=c;
  //DIS*004 }

  //DIS*004 color getBGColor() { 
  //DIS*004   return arrowBGColor;
  //DIS*004 }

  void setStrokeWeight(int c) {
    arrowStrokeWeight=c;
  }

  color getStrokeWeight() { 
    return arrowStrokeWeight;
  }

  void setStrokeSense(int c) {
    sense=c;
  }

  color getStrokeSense() { 
    return sense;
  }

  void setArrowAsDash() {
    dashArrow=true;
  }

  boolean isDashArrow() { 
    return dashArrow;
  }

  void setArrowAsSolid() {
    dashArrow=false;
  }

  boolean isSolidArrow() { 
    return !isDashArrow();
  }
}