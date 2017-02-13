
import javax.swing.*;
import java.lang.Math;
import java.io.*;
import java.awt.geom.Point2D;
import java.util.*;

public class QuickStarPolygonGenerator {
	
	QuickStarPolygonGenerator() {}
	
	@SuppressWarnings("resource")
	public void generate(DataGenModel aModel) throws IOException
	{
		// set up file output 
		String outFilename;
	    FileWriter f = null;
	    PrintWriter out = null;
	    
	    // do we wish polygons generated?
	    if (aModel.theGenerateQSPolygonsFlag == false)
	    	return;
	    
	    // generate output file
	    outFilename = aModel.theFilenamePrefix + "Quick-StarPolygons.txt";
		f = new FileWriter(outFilename);
		out = new PrintWriter(f);        
		System.out.println("  creating quick-star polygons datafile [" + outFilename + "]");

		// input from user
		int numOfQSPolygons = aModel.theNumberOfQSPolygons;
		int numOfQSVertices = aModel.theNumberOfQSVertices;
		double starRadius = aModel.theStarRadius;
		
		
		int outerCount = 0;	// polygon counter
		int innerCount = 0;	// vertices counter
		double angle = 0;	// angle between two consecutive vertices 
		double randRadius;	// radius in the range 0-radius
		
		// vertex coordinates
		double x;
		double y;
		
		// center of polygon
		double centerX;
		double centerY;
		double gap;		// angle gap
		
		
		gap = (2 * Math.PI) / numOfQSVertices;
		Random rand = new Random();
		
		while (outerCount < numOfQSPolygons) {
			
			out.print("CONIC-SPIRAL (");
			
			// generating random center within scene bound
			centerX = rand.nextDouble() * aModel.theSceneLength + 1;
			centerY = rand.nextDouble() * aModel.theSceneLength + 1;
			
			
			angle = 0;
			innerCount = 0;
			
			while (innerCount < numOfQSVertices) {
				randRadius = rand.nextFloat() * starRadius;
				x = centerX + randRadius * Math.cos(angle * Math.PI);
				y = centerY + randRadius * Math.sin(angle * Math.PI);
				out.printf("%f %f, ", x, y);
				angle += gap;
				innerCount++;
			}
			out.println(")");
			outerCount++;
		}
		out.close();
		System.out.println("    " + numOfQSPolygons + " quick-star polygons were generated.");

	}
}