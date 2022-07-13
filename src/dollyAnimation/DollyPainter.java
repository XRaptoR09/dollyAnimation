package dollyAnimation;

import java.awt.*;            
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
public class DollyPainter extends Frame{
  
	public static void main(String[] args) throws Exception{ // 1
	    DollyPainter myDolly = new DollyPainter("Dolly");
	    myDolly.setSize(600, 600);
	    myDolly.setVisible(true);
	    myDolly.go();
	  }

	  DollyPainter(String title) { // 2
	    super(title);
	    addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent ev) {
	        System.exit(0);
	      }
	    });
	  }
	  public void go() throws Exception{
	    while(true) { 
	      repaint();                    // invoke update
	      Thread.sleep(4);
	      if(!clockwise) {
				if(angle > -angleCritical) {
					angle = angle - angleRotate;
				}else{
					clockwise=true;
				}
			}else{
				if(angle < angleCritical){
					angle = angle + angleRotate;
				}else{
					clockwise=false;
				}
			}
	    }
	  }
	//Vars for declaring
	  boolean clockwise = false;
	  double angleCritical = 0.8;
	  
	  int xStart = 200, yStart = 100;
	  int diameterHigh = 40, diameterMiddle = 70, diameterLow = 120, handsDiameter = 30;

	  // Vars for calculate
	  int yLow = yStart + diameterHigh + diameterMiddle;
	  int xCenter = xStart + diameterLow / 2;
	  int xHigh = xCenter - diameterHigh / 2;
	  int xMiddle = xCenter - diameterMiddle / 2, yMiddle = yStart + diameterHigh;
	  int xLeftHand = xCenter - diameterMiddle / 2 - handsDiameter;
	  int xRightHand = xCenter + diameterMiddle / 2;
	  int yHands = yStart + diameterHigh + diameterMiddle / 2 - handsDiameter / 2;
	  
	  double angleRotate = 0.01;
	  double angle = 0;
	  
	  public void update(Graphics g) { // УВАГА НЕ ЗАБУТИ ЗМІНИТИ!!!!! при анімації
	    int w = getSize().width, h = getSize().height;
	    BufferedImage bi = (BufferedImage) createImage(w, h);
	    Graphics2D big = bi.createGraphics(); // 3
	    //////////////////////////////////////////////////////
	    Ellipse2D.Double start = new Ellipse2D.Double(xStart, yLow, diameterLow, diameterLow);
	    Ellipse2D.Double middle = new Ellipse2D.Double(xMiddle, yMiddle, diameterMiddle, diameterMiddle);
	    Ellipse2D.Double high = new Ellipse2D.Double(xHigh, yStart, diameterHigh, diameterHigh);
	    Ellipse2D.Double leftHand = new Ellipse2D.Double(xLeftHand, yHands, handsDiameter, handsDiameter);
	    Ellipse2D.Double rigthHand = new Ellipse2D.Double(xRightHand, yHands, handsDiameter, handsDiameter);

	    GeneralPath dolly = new GeneralPath(start);
	    dolly.append(middle, false);
	    dolly.append(high, false);
	    dolly.append(leftHand, false);
	    dolly.append(rigthHand, false);
	    big.rotate(angle, xCenter, yLow + diameterLow);
	    big.draw(dolly);
	    ////////////////////////////////////////
	    g.drawImage(bi, 0, 0, this);
	  }

}