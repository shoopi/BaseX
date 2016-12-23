package main.java.nl.tue.ieis.is.basex.gui;

import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.canvas.Canvas;
import org.zkoss.canvas.DrawingStyle.LineCap;
import org.zkoss.canvas.DrawingStyle.LineJoin;
import org.zkoss.canvas.DrawingStyle.TextAlign;
import org.zkoss.canvas.DrawingStyle.TextBaseline;
import org.zkoss.canvas.drawable.Path;

public class DrawComponent {
	
	private List<Path> _paths;
	private Canvas cvs;
	
	public DrawComponent(Canvas cvs) {
		_paths = new ArrayList<Path>();
		this.cvs = cvs;
	}
	
	private void createFiveCircles() {
		Path p5 = new Path(new Path2D.Double(new Ellipse2D.Double(0, 0, 1000, 1000)));
		p5.getDrawingStyle().setFillStyle("#9ACDFF");
		p5.getDrawingStyle().setShadowColor("#800000");
		p5.getDrawingStyle().setShadowBlur(5);
		p5.getDrawingStyle().setShadowOffset(0.5, 0.5);
		_paths.add(p5);
		
		Path p4 = new Path(new Path2D.Double(new Ellipse2D.Double(100, 100, 800, 800)));
		p4.getDrawingStyle().setFillStyle("#BFDFFF");
		p4.getDrawingStyle().setShadowColor("#800000");
		p4.getDrawingStyle().setShadowBlur(5);
		p4.getDrawingStyle().setShadowOffset(0.5, 0.5);
		_paths.add(p4);
		
		Path p3 = new Path(new Path2D.Double(new Ellipse2D.Double(200, 200, 600, 600)));
		p3.copyStyleFrom(p4);
		p3.getDrawingStyle().setFillStyle("#D1E8FF");
		_paths.add(p3);
		
		Path p2 = new Path(new Path2D.Double(new Ellipse2D.Double(300, 300, 400, 400)));
		p2.copyStyleFrom(p4);
		p2.getDrawingStyle().setFillStyle("#E5F2FF");
		_paths.add(p2);
		
		Path p1 = new Path(new Path2D.Double(new Ellipse2D.Double(400, 400, 200, 200)));
		p1.copyStyleFrom(p4);
		p1.getDrawingStyle().setFillStyle("#FFFFFF");
		_paths.add(p1);
	}
	
	public void drawCanvas(int actorsNumb) {
    	createFiveCircles();
		switch (actorsNumb) {
        case 2: {
        	Path l1 = drawActorLine(0, true);
    		
        	_paths.add(_paths.size()-1, l1);
        	cvs.add(_paths.toArray(new Path[_paths.size()]));
        	break;
    	}  case 3: {
        	Path l1 = drawActorLine(0, false);
            Path l2 = drawActorLine(120, false);
            Path l3 = drawActorLine(240, false);
    		
            _paths.add(_paths.size()-1, l1);
    		_paths.add(_paths.size()-1, l2);
    		_paths.add(_paths.size()-1, l3);
    		
    		
    		cvs.add(_paths.toArray(new Path[_paths.size()]));
    		
        	break;
        } case 4: {
	        	Path l1 = drawActorLine(0, true);
	            Path l2 = drawActorLine(90, true);
	    		
	            _paths.add(_paths.size()-1, l1);
	    		_paths.add(_paths.size()-1, l2);
	    		cvs.add(_paths.toArray(new Path[_paths.size()]));
	    		break;
    	} case 5: {
    		Path l1 = drawActorLine(0, false);
            Path l2 = drawActorLine(72, false);
            Path l3 = drawActorLine(144, false);
            Path l4 = drawActorLine(216, false);
            Path l5 = drawActorLine(288, false);

            _paths.add(_paths.size()-1, l1);
    		_paths.add(_paths.size()-1, l2);
    		_paths.add(_paths.size()-1, l3);
    		_paths.add(_paths.size()-1, l4);
    		_paths.add(_paths.size()-1, l5);
    		cvs.add(_paths.toArray(new Path[_paths.size()]));
        	break;
    	} case 6: {
    		Path l1 = drawActorLine(60, true);
            Path l2 = drawActorLine(120, true);
            Path l3 = drawActorLine(180, true);

            _paths.add(_paths.size()-1, l1);
    		_paths.add(_paths.size()-1, l2);
    		_paths.add(_paths.size()-1, l3);
    		cvs.add(_paths.toArray(new Path[_paths.size()]));
        	break;
    	} case 7: {
    		Path l1 = drawActorLine(0, false);
            Path l2 = drawActorLine(51.5, false);
            Path l3 = drawActorLine(103, false);
            Path l4 = drawActorLine(154.5, false);
            Path l5 = drawActorLine(206, false);
            Path l6 = drawActorLine(257.5, false);
            Path l7 = drawActorLine(309, false);

            _paths.add(_paths.size()-1, l1);
    		_paths.add(_paths.size()-1, l2);
    		_paths.add(_paths.size()-1, l3);
    		_paths.add(_paths.size()-1, l4);
    		_paths.add(_paths.size()-1, l5);
    		_paths.add(_paths.size()-1, l6);
    		_paths.add(_paths.size()-1, l7);
    		cvs.add(_paths.toArray(new Path[_paths.size()]));
        	break;
    	} case 8:  {
        	Path l1 = drawActorLine(0, true);
            Path l2 = drawActorLine(90, true);
            Path l3 = drawActorLine(45, true);
            Path l4 = drawActorLine(135, true);

            _paths.add(_paths.size()-1, l1);
    		_paths.add(_paths.size()-1, l2);
    		_paths.add(_paths.size()-1, l3);
    		_paths.add(_paths.size()-1, l4);
    		cvs.add(_paths.toArray(new Path[_paths.size()]));
        	break; 
        	}
		}
	}
	
	private Path drawActorLine(double degree, boolean fullLine) {
		Path line = new Path();
		if (fullLine)
			line = new Path(new Path2D.Double(new Rectangle2D.Double(2, 500, 1000, 10)));
		else 
			line = new Path(new Path2D.Double(new Rectangle2D.Double(2, 500, 500, 10)));
		
		line.getDrawingStyle().setFillStyle("#ffffff");
		line.getDrawingStyle().setShadowColor("#800000");
		line.getDrawingStyle().setShadowBlur(5);
		line.getDrawingStyle().setShadowOffset(0.5, 0.5);
		line.getDrawingStyle().setLineCap(LineCap.ROUND);
		line.getDrawingStyle().setLineJoin(LineJoin.ROUND);
    	AffineTransform at = new AffineTransform();
    	at.rotate(Math.toRadians(degree), 500, 500);
    	line.transform(at);
    	
		return line;
	}
	
	public void writeTextToCanvas(String text, int circleNumber, int numberOfActors, int selectedActor, double x, double y, double degree, String color, boolean highlightSlice) {			
		Path border = new Path();
		if(circleNumber == -1) {
			border = new Path(new Path2D.Double(new Ellipse2D.Double(400, 400, 200, 200)));
			
		} else {
			double outer_r = 500 - (100 * circleNumber);
			double inner_r = 500 - (100 * circleNumber) - 100;
			
			double center = 500;
			
			
			switch(numberOfActors) {
			case (2) : {
				
				double x1_outerLayer = center - (outer_r * Math.cos(Math.toRadians(0)));
				double y1_outerLayer = center - (outer_r * Math.sin(Math.toRadians(0)));
				double x1_innerLayer = center - (inner_r * Math.cos(Math.toRadians(0)));
				double y1_innerLayer = center - (inner_r * Math.sin(Math.toRadians(0)));
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(180)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(180)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(180)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(180)));
				
				double maxOuter = center - (1.33 * outer_r);
				double maxInner = center - (1.33 * inner_r);
				
				double minOuter = center + (1.33 * outer_r);
				double minInner = center + (1.33 * inner_r); 
				
				if(selectedActor == 1) {					
					border.moveTo(x1_innerLayer, y1_innerLayer);
					border.lineTo(x1_outerLayer, y1_outerLayer);
					border.curveTo(x1_outerLayer, maxOuter, x2_outerLayer, maxOuter, x2_outerLayer, y2_outerLayer);
					border.lineTo(x2_innerLayer, y2_innerLayer);
					border.curveTo(x2_innerLayer, maxInner, x1_innerLayer,maxInner, x1_innerLayer, y1_innerLayer);
					border.closePath();
					
				} else if (selectedActor == 2) {					
					border.moveTo(x1_innerLayer, y1_innerLayer);
					border.lineTo(x1_outerLayer, y1_outerLayer);
					border.curveTo(x1_outerLayer, minOuter, x2_outerLayer, minOuter, x2_outerLayer, y2_outerLayer);
					border.lineTo(x2_innerLayer, y2_innerLayer);
					border.curveTo(x2_innerLayer, minInner, x1_innerLayer, minInner, x1_innerLayer, y1_innerLayer);
					border.closePath();
				}
				break;
			}
			case (3) : {
				double maxOuter = center - (1.25 * outer_r);
				double maxInner = center - (1.25 * inner_r);
		
				double minOuter = center + (1.25 * outer_r);
				double minInner = center + (1.25 * inner_r); 
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(120)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(120)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(120)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(120)));
				
				double x3_outerLayer = center - (outer_r * Math.cos(Math.toRadians(240)));
				double y3_outerLayer = center - (outer_r * Math.sin(Math.toRadians(240)));
				double x3_innerLayer = center - (inner_r * Math.cos(Math.toRadians(240)));
				double y3_innerLayer = center - (inner_r * Math.sin(Math.toRadians(240)));
				
				double x4_outerLayer = center - (outer_r * Math.cos(Math.toRadians(360)));
				double y4_outerLayer = center - (outer_r * Math.sin(Math.toRadians(360)));
				double x4_innerLayer = center - (inner_r * Math.cos(Math.toRadians(360)));
				double y4_innerLayer = center - (inner_r * Math.sin(Math.toRadians(360)));
				
				
				if(selectedActor == 1) {
					border = new Path();
					
					border.moveTo(x4_innerLayer, y4_innerLayer);
					border.lineTo(x4_outerLayer, y4_outerLayer);
					border.curveTo(x4_outerLayer, y2_outerLayer, center, maxOuter, x2_outerLayer, y2_outerLayer);
					border.lineTo(x2_innerLayer, y2_innerLayer);
					border.curveTo(center, maxInner, x4_innerLayer, y2_innerLayer, x4_innerLayer, y4_innerLayer);
					border.closePath();

				
				} else if (selectedActor == 2) {
					border = new Path();
					
					border.moveTo(x2_innerLayer, y2_innerLayer);
					border.lineTo(x2_outerLayer, y2_outerLayer);
					border.curveTo(x2_outerLayer, y2_outerLayer, minOuter + (0.4 * outer_r), center, x3_outerLayer, y3_outerLayer);
					border.lineTo(x3_innerLayer, y3_innerLayer);
					border.curveTo(x3_innerLayer, y3_innerLayer, minInner + (0.5 * inner_r), center, x2_innerLayer, y2_innerLayer);
					border.closePath();

				} else { 
					
					border.moveTo(x4_innerLayer, y4_innerLayer);
					border.lineTo(x4_outerLayer, y4_outerLayer);
					border.curveTo(x4_outerLayer, y3_outerLayer, center, minOuter, x3_outerLayer, y3_outerLayer);
					border.lineTo(x3_innerLayer, y3_innerLayer);
					border.curveTo(center, minInner, x4_innerLayer, y3_innerLayer, x4_innerLayer, y4_innerLayer);
					border.closePath();
				}
				break;
				
			}
			case (4) : {
				
				double x1_outerLayer = center - (outer_r * Math.cos(Math.toRadians(0)));
				double y1_outerLayer = center - (outer_r * Math.sin(Math.toRadians(0)));
				double x1_innerLayer = center - (inner_r * Math.cos(Math.toRadians(0)));
				double y1_innerLayer = center - (inner_r * Math.sin(Math.toRadians(0)));
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(90)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(90)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(90)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(90)));
				
				double x3_outerLayer = center - (outer_r * Math.cos(Math.toRadians(180)));
				double y3_outerLayer = center - (outer_r * Math.sin(Math.toRadians(180)));
				double x3_innerLayer = center - (inner_r * Math.cos(Math.toRadians(180)));
				double y3_innerLayer = center - (inner_r * Math.sin(Math.toRadians(180)));
				
				double x4_outerLayer = center - (outer_r * Math.cos(Math.toRadians(270)));
				double y4_outerLayer = center - (outer_r * Math.sin(Math.toRadians(270)));
				double x4_innerLayer = center - (inner_r * Math.cos(Math.toRadians(270)));
				double y4_innerLayer = center - (inner_r * Math.sin(Math.toRadians(270)));
				
				if(selectedActor == 1) {
					border = new Path();
					border.moveTo(x1_innerLayer, y1_innerLayer);
					border.lineTo(x1_outerLayer, y1_outerLayer);
					border.curveTo(x1_outerLayer, y1_outerLayer, center - outer_r, y2_outerLayer, x2_outerLayer, y2_outerLayer);
					border.lineTo(x2_innerLayer, y2_innerLayer);
					border.curveTo(x2_innerLayer, y2_innerLayer, center - inner_r, y2_innerLayer, x1_innerLayer, y1_innerLayer);
					border.closePath();
				
				} else if (selectedActor == 2) {
					border = new Path();
					border.moveTo(x2_innerLayer, y2_innerLayer);
					border.lineTo(x2_outerLayer, y2_outerLayer);
					border.curveTo(x2_outerLayer, y2_outerLayer, center + outer_r, y2_outerLayer, x3_outerLayer, y3_outerLayer);
					border.lineTo(x3_innerLayer, y3_innerLayer);
					border.curveTo(x3_innerLayer, y3_innerLayer, center + inner_r, y2_innerLayer, x2_innerLayer, y2_innerLayer);
					border.closePath();
					
				} else if (selectedActor == 3) {
					border =  new Path();
					border.moveTo(x3_innerLayer, y3_innerLayer);
					border.lineTo(x3_outerLayer, y3_outerLayer);
					border.curveTo(x3_outerLayer, y3_outerLayer, center + outer_r, y4_outerLayer, x4_outerLayer, y4_outerLayer);
					border.lineTo(x4_innerLayer, y4_innerLayer);
					border.curveTo(x4_innerLayer, y4_innerLayer, center + inner_r, y4_innerLayer, x3_innerLayer, y3_innerLayer);
					border.closePath();
					
				} else if (selectedActor == 4) {
					border = new Path();
					border.moveTo(x4_innerLayer, y4_innerLayer);
					border.lineTo(x4_outerLayer, y4_outerLayer);
					border.curveTo(x4_outerLayer, y4_outerLayer, center - outer_r, y4_outerLayer, x1_outerLayer, y1_outerLayer);
					border.lineTo(x1_innerLayer, y1_innerLayer);
					border.curveTo(x1_innerLayer, y1_innerLayer, center - inner_r, y4_innerLayer, x4_innerLayer, y4_innerLayer);
					border.closePath();
					
				}
				break;
			} case (5) : {
				
				double x1_outerLayer = center - (outer_r * Math.cos(Math.toRadians(0)));
				double y1_outerLayer = center - (outer_r * Math.sin(Math.toRadians(0)));
				double x1_innerLayer = center - (inner_r * Math.cos(Math.toRadians(0)));
				double y1_innerLayer = center - (inner_r * Math.sin(Math.toRadians(0)));
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(72)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(72)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(72)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(72)));
				
				double x3_outerLayer = center - (outer_r * Math.cos(Math.toRadians(144)));
				double y3_outerLayer = center - (outer_r * Math.sin(Math.toRadians(144)));
				double x3_innerLayer = center - (inner_r * Math.cos(Math.toRadians(144)));
				double y3_innerLayer = center - (inner_r * Math.sin(Math.toRadians(144)));
				
				double x4_outerLayer = center - (outer_r * Math.cos(Math.toRadians(216)));
				double y4_outerLayer = center - (outer_r * Math.sin(Math.toRadians(216)));
				double x4_innerLayer = center - (inner_r * Math.cos(Math.toRadians(216)));
				double y4_innerLayer = center - (inner_r * Math.sin(Math.toRadians(216)));
				
				double x5_outerLayer = center - (outer_r * Math.cos(Math.toRadians(288)));
				double y5_outerLayer = center - (outer_r * Math.sin(Math.toRadians(288)));
				double x5_innerLayer = center - (inner_r * Math.cos(Math.toRadians(288)));
				double y5_innerLayer = center - (inner_r * Math.sin(Math.toRadians(288)));
				
				break;
			} case (6) : {
				
				double x1_outerLayer = center - (outer_r * Math.cos(Math.toRadians(0)));
				double y1_outerLayer = center - (outer_r * Math.sin(Math.toRadians(0)));
				double x1_innerLayer = center - (inner_r * Math.cos(Math.toRadians(0)));
				double y1_innerLayer = center - (inner_r * Math.sin(Math.toRadians(0)));
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(60)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(60)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(60)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(60)));
				
				double x3_outerLayer = center - (outer_r * Math.cos(Math.toRadians(120)));
				double y3_outerLayer = center - (outer_r * Math.sin(Math.toRadians(120)));
				double x3_innerLayer = center - (inner_r * Math.cos(Math.toRadians(120)));
				double y3_innerLayer = center - (inner_r * Math.sin(Math.toRadians(120)));
				
				double x4_outerLayer = center - (outer_r * Math.cos(Math.toRadians(180)));
				double y4_outerLayer = center - (outer_r * Math.sin(Math.toRadians(180)));
				double x4_innerLayer = center - (inner_r * Math.cos(Math.toRadians(180)));
				double y4_innerLayer = center - (inner_r * Math.sin(Math.toRadians(180)));
				
				double x5_outerLayer = center - (outer_r * Math.cos(Math.toRadians(240)));
				double y5_outerLayer = center - (outer_r * Math.sin(Math.toRadians(240)));
				double x5_innerLayer = center - (inner_r * Math.cos(Math.toRadians(240)));
				double y5_innerLayer = center - (inner_r * Math.sin(Math.toRadians(240)));
				
				double x6_outerLayer = center - (outer_r * Math.cos(Math.toRadians(300)));
				double y6_outerLayer = center - (outer_r * Math.sin(Math.toRadians(300)));
				double x6_innerLayer = center - (inner_r * Math.cos(Math.toRadians(300)));
				double y6_innerLayer = center - (inner_r * Math.sin(Math.toRadians(300)));				
				
				break;
			} case (7) : {
				
				double x1_outerLayer = center - (outer_r * Math.cos(Math.toRadians(0)));
				double y1_outerLayer = center - (outer_r * Math.sin(Math.toRadians(0)));
				double x1_innerLayer = center - (inner_r * Math.cos(Math.toRadians(0)));
				double y1_innerLayer = center - (inner_r * Math.sin(Math.toRadians(0)));
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(51.5)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(51.5)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(51.5)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(51.5)));
				
				double x3_outerLayer = center - (outer_r * Math.cos(Math.toRadians(103)));
				double y3_outerLayer = center - (outer_r * Math.sin(Math.toRadians(103)));
				double x3_innerLayer = center - (inner_r * Math.cos(Math.toRadians(103)));
				double y3_innerLayer = center - (inner_r * Math.sin(Math.toRadians(103)));
				
				double x4_outerLayer = center - (outer_r * Math.cos(Math.toRadians(154.5)));
				double y4_outerLayer = center - (outer_r * Math.sin(Math.toRadians(154.5)));
				double x4_innerLayer = center - (inner_r * Math.cos(Math.toRadians(154.5)));
				double y4_innerLayer = center - (inner_r * Math.sin(Math.toRadians(154.5)));
				
				double x5_outerLayer = center - (outer_r * Math.cos(Math.toRadians(206)));
				double y5_outerLayer = center - (outer_r * Math.sin(Math.toRadians(206)));
				double x5_innerLayer = center - (inner_r * Math.cos(Math.toRadians(206)));
				double y5_innerLayer = center - (inner_r * Math.sin(Math.toRadians(206)));
				
				double x6_outerLayer = center - (outer_r * Math.cos(Math.toRadians(257.5)));
				double y6_outerLayer = center - (outer_r * Math.sin(Math.toRadians(257.5)));
				double x6_innerLayer = center - (inner_r * Math.cos(Math.toRadians(257.5)));
				double y6_innerLayer = center - (inner_r * Math.sin(Math.toRadians(257.5)));	
				
				double x7_outerLayer = center - (outer_r * Math.cos(Math.toRadians(309)));
				double y7_outerLayer = center - (outer_r * Math.sin(Math.toRadians(309)));
				double x7_innerLayer = center - (inner_r * Math.cos(Math.toRadians(309)));
				double y7_innerLayer = center - (inner_r * Math.sin(Math.toRadians(309)));
				
				break;
			} case (8) : {
				
				double x1_outerLayer = center - (outer_r * Math.cos(Math.toRadians(0)));
				double y1_outerLayer = center - (outer_r * Math.sin(Math.toRadians(0)));
				double x1_innerLayer = center - (inner_r * Math.cos(Math.toRadians(0)));
				double y1_innerLayer = center - (inner_r * Math.sin(Math.toRadians(0)));
				
				double x2_outerLayer = center - (outer_r * Math.cos(Math.toRadians(45)));
				double y2_outerLayer = center - (outer_r * Math.sin(Math.toRadians(45)));
				double x2_innerLayer = center - (inner_r * Math.cos(Math.toRadians(45)));
				double y2_innerLayer = center - (inner_r * Math.sin(Math.toRadians(45)));
				
				double x3_outerLayer = center - (outer_r * Math.cos(Math.toRadians(90)));
				double y3_outerLayer = center - (outer_r * Math.sin(Math.toRadians(90)));
				double x3_innerLayer = center - (inner_r * Math.cos(Math.toRadians(90)));
				double y3_innerLayer = center - (inner_r * Math.sin(Math.toRadians(90)));
				
				double x4_outerLayer = center - (outer_r * Math.cos(Math.toRadians(135)));
				double y4_outerLayer = center - (outer_r * Math.sin(Math.toRadians(135)));
				double x4_innerLayer = center - (inner_r * Math.cos(Math.toRadians(135)));
				double y4_innerLayer = center - (inner_r * Math.sin(Math.toRadians(135)));
				
				double x5_outerLayer = center - (outer_r * Math.cos(Math.toRadians(180)));
				double y5_outerLayer = center - (outer_r * Math.sin(Math.toRadians(180)));
				double x5_innerLayer = center - (inner_r * Math.cos(Math.toRadians(180)));
				double y5_innerLayer = center - (inner_r * Math.sin(Math.toRadians(180)));
				
				double x6_outerLayer = center - (outer_r * Math.cos(Math.toRadians(225)));
				double y6_outerLayer = center - (outer_r * Math.sin(Math.toRadians(225)));
				double x6_innerLayer = center - (inner_r * Math.cos(Math.toRadians(225)));
				double y6_innerLayer = center - (inner_r * Math.sin(Math.toRadians(225)));	
				
				double x7_outerLayer = center - (outer_r * Math.cos(Math.toRadians(270)));
				double y7_outerLayer = center - (outer_r * Math.sin(Math.toRadians(270)));
				double x7_innerLayer = center - (inner_r * Math.cos(Math.toRadians(270)));
				double y7_innerLayer = center - (inner_r * Math.sin(Math.toRadians(270)));

				double x8_outerLayer = center - (outer_r * Math.cos(Math.toRadians(315)));
				double y8_outerLayer = center - (outer_r * Math.sin(Math.toRadians(315)));
				double x8_innerLayer = center - (inner_r * Math.cos(Math.toRadians(315)));
				double y8_innerLayer = center - (inner_r * Math.sin(Math.toRadians(315)));
				/*
				if(selectedActor == 1) {
					xt = -25.0;
					yt = -25.0;
					degree = -45.0;
				} else if (selectedActor == 2) {
					xt = -25.0;
					yt = 25.0;
					degree = 45.0;
				} else if (selectedActor == 3) {
					xt = -25.0;
					yt = 25.0;
					degree = 45.0;
				} else if (selectedActor == 4) {
					xt = -25.0;
					yt = -25.0;
					degree = -45.0;
				} else if(selectedActor == 5) {
					xt = -25.0;
					yt = -25.0;
					degree = -45.0;
				} else if (selectedActor == 6) {
					xt = -25.0;
					yt = 25.0;
					degree = 45.0;
				} else if (selectedActor == 7) {
					xt = -25.0;
					yt = 25.0;
					degree = 45.0;
				} else if (selectedActor == 8) {
					xt = -25.0;
					yt = -25.0;
					degree = -45.0;
				}
				*/
				break;
				}
			}
		}
		if(highlightSlice) {
			setSelectedSliceStyle(border);
			cvs.add(border);
		}
		breakLines(text, x, y, degree, color, border);
	}
	
	private void setSelectedSliceStyle(Path border) {
		border.getDrawingStyle().setFillStyle("#f7eeaf");
		border.getDrawingStyle().setShadowColor("#ffdf63");
		border.getDrawingStyle().setShadowBlur(5);
		border.getDrawingStyle().setShadowOffset(0.5, 0.5);
		border.getDrawingStyle().setLineJoin(LineJoin.ROUND);
		border.getDrawingStyle().setLineCap(LineCap.ROUND);
		border.getDrawingStyle().setStrokeStyle("#03124f");
		border.getDrawingStyle().setLineWidth(3);
		border.getDrawingStyle().setAlpha(0.1);
	}

	private void breakLines(String text, double xt, double yt, double degree, String color, Path border) {
		List<Path> lines = new ArrayList<Path>();
		double x = (border.getBounds2D().getWidth() / 2) + border.getBounds2D().getX();
		double y = (border.getBounds2D().getWidth() / 2) + border.getBounds2D().getY();
		
		if (isTextBorderViolated(text.trim(), border, x, y, degree, 20)) {
			int mid = findBreakableChar(text.trim(), 2);
			String line1_2 = text.substring(0, mid + 1).trim();
			String line2_2 = text.substring(mid + 1).trim();
			if(isTextBorderViolated(line1_2.trim(), border, x, y - 12, degree, 20) 
					|| isTextBorderViolated(line2_2.trim(), border, x, y + 12, degree, 20)) {
				int oneThird = findBreakableChar(text.trim(), 3);
				String line1_3 = text.substring(0, oneThird + 1).trim();
				String rest = text.substring(oneThird + 1).trim();
				int twoThird = findBreakableChar(rest.trim(), 2);
				String line2_3 = rest.substring(0, twoThird + 1).trim();
				String line3_3 = rest.substring(twoThird + 1).trim();
				
				int fontsize = 20;
				while(isTextBorderViolated(line1_3.trim(), border, x, y - 22, degree,fontsize) || 
						isTextBorderViolated(line2_3.trim(), border, x, y, degree,fontsize) || 
						isTextBorderViolated(line3_3.trim(), border, x, y + 22, degree,fontsize)) {
					fontsize = fontsize - 1;
					if(fontsize < 16)
						break;
				} 
				
				lines.addAll(drawText(line1_3, x, y - 22, degree, color, fontsize));
				lines.addAll(drawText(line2_3, x, y, degree, color, fontsize));
				lines.addAll(drawText(line3_3, x, y + 22, degree, color, fontsize));
					
			} else {
				
				lines.addAll(drawText(line1_2, x, y - 12, degree, color, 20));
				lines.addAll(drawText(line2_2, x, y + 12, degree, color, 20));
			}
		} else {
			lines.addAll(drawText(text, x, y, degree, color, 20));
		}
		Path[] pathStrings = lines.toArray(new Path[lines.size()]);
		cvs.add(pathStrings);
	}
	
	private List<Path> drawText(String s, double x, double y, double degree, String color, int fontsize) {					
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), false, false);
		Font font = new Font("Tahoma", Font.PLAIN, fontsize);
		GlyphVector gv = font.createGlyphVector(frc, s);
		int length = gv.getNumGlyphs();
		Rectangle2D bounds = gv.getVisualBounds();
		double x2 = x - (bounds.getWidth() / 2);
		
		List<Path> pathString = new ArrayList<Path>();
		for (int i = 0; i < length; i++) {
			
			AffineTransform at = new AffineTransform();
			at.rotate(Math.toRadians(degree), x2, y);
			at.translate(x2, y);
			
			Shape glyph = gv.getGlyphOutline(i);
			Shape transformedGlyph = at.createTransformedShape(glyph);
			Path letter = new Path(new Path2D.Double(transformedGlyph));
			
			letter.getDrawingStyle().setFont(fontsize + "px tahoma");
			letter.getDrawingStyle().setFillStyle(color);
			letter.getDrawingStyle().setStrokeStyle(color);
			letter.getDrawingStyle().setShadowColor("#000080");
			letter.getDrawingStyle().setShadowBlur(5);
			letter.getDrawingStyle().setShadowOffset(0.015, 0.015);
			letter.getDrawingStyle().setTextBaseline(TextBaseline.MIDDLE);
			letter.getDrawingStyle().setTextAlign(TextAlign.CENTER);			
			
			pathString.add(letter);
		}
		return pathString;
	}
	
	private int findBreakableChar(String text, int breakdown) {
		int mid = text.length() / breakdown;
		while(mid > 0 && mid < text.length() && text.length() > mid + 1 &&
				!(text.charAt(mid) != ' ' && text.charAt(mid + 1) == ' ')) {
			mid = mid - 1;
		}
		return mid;
	}
	
	private boolean isTextBorderViolated(String text, Path  border, double x, double y, double degree, int fontsize) {
		List<Path> paths = drawText(text, x, y, degree,"", fontsize);

		Area borderArea = new Area(border);
		Area letter1 = new Area(paths.get(0));
		Area letter2 = new Area(paths.get(paths.size()-1));

		Area letter1_copy = (Area) letter1.clone();
		Area letter2_copy = (Area) letter2.clone();
		
		letter1.intersect(borderArea);
		letter2.intersect(borderArea);
		 
		if(letter1.equals(letter1_copy) && letter2.equals(letter2_copy))
			return false;
		
		else return true;
	}
}
