//import com.UltimateBox;

UHeader tbar;
UFooter bbar;

void setup() {
  size(400, 200);
  tbar=new UHeader(this, "Header Showcase", 0xffff0000);
  bbar = new UFooter(this, "Message @ ceNTer");
}

void draw() {
  tbar.header("Header Showcase", 0xffff0000);
  bbar.UFooterCenter();
}
