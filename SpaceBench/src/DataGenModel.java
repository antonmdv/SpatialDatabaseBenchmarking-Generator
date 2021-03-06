/*
 *
 * Original Author: Tim Faulkner
 * Editing Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file has to do with the mechanics behind the main application window.
 * It also validates options entered 
 * and reads / writes to CSV files for options loading / saving
 */

import javax.swing.*;
import java.io.*;
import javax.swing.JFileChooser;
import java.util.StringTokenizer;

public class DataGenModel {

   //***
   // instance variables
   //***

   private static final boolean TRACE = false;

   // scene options
   public double theSceneLength;
   public String theFilenamePrefix;
   
   // point values
   public boolean theGeneratePointsFlag;
   public int theNumberOfPoints;
   public boolean theUniquePointsFlag;
   
   // square values
   public boolean theGenerateSquaresFlag;
   public int theNumberOfSquares;
   public double theMaximumSquareSideLength;

   // triangle values
   public boolean theGenerateTrianglesFlag;
   public int theNumberOfTriangles;
   public double theTriangleBBoxHeight;
   public double theTriangleBBoxWidth;

   // polygon values
   public boolean theGeneratePolygonsFlag;
   public int theNumberOfPolygons;
   public int thePolygonMaxVertexCount;
   public int thePolygonBBoxLength;
   public int thePolygonMinVertexCount;
   
   // linestring values
   public boolean theGenerateLineStringsFlag;
   public int theNumberOfLineStrings;
   public int theLineStringMaxSegmentCount;
   public int theLineStringMinSegmentCount;
   
   
   //Random Walk values
   public boolean theGenerateRandomWalksFlag;
   public int theNumberOfRandomWalks;
   public double theMaximumStepLength;
   public int theNumberOfSteps;
   
   //Conic Spiral values
   public boolean theGenerateConicSpiralsFlag;
   public int theNumberOfConicSpirals;
   public double theMaximumRadiusLength;
   public double theSegmentInc;
   public double theRadius;
   public double theAngleGapLower;
   public double theAngleGapUpper;
   
   //Midpoint Displacement values
   public boolean theGenerateMidpointDisplacementsFlag;
   public int theNumberofMidpointDisplacements;
   public int theRecursionDepth;
   public double theDisplacementBound;
   public double theDisplacementBoundReduction;
   
   // Quick-Star Polygon values
   public boolean theGenerateQSPolygonsFlag;
   public int theNumberOfQSPolygons;
   public int theNumberOfQSVertices;
   public double theStarRadius;
   
   /*
    * DataGenModel()
    * 
    * This method create the main application window
    */
   
   DataGenModel()
   {
   }

   /*
    * validate()
    *
    * This method validates the model
    */
   
   public boolean validate(JFrame aFrame)
   {
      boolean isValid = true;
      String msg = "";
      
      if (TRACE)
         System.out.println("DataGenModel: validate");
         
      //***
      // validate point options
      //***
      
      if (isValid && theGeneratePointsFlag)
      {
         if (theNumberOfPoints <= 0)
         {
            msg = "Points file must contain at least 1 element";
            isValid = false;
         }
      }
      
      //***
      // validate square options
      //***

      if (isValid && theGenerateSquaresFlag)
      {
         if (theNumberOfSquares <= 0)
         {
            msg = "Squares file must contain at least 1 element";
            isValid = false;
         }
         if ((theMaximumSquareSideLength <= 0) || (theMaximumSquareSideLength >= theSceneLength))
         {
            msg = "Maximum square length has to be < scene length, and > 0";
            isValid = false;
         }
      }

      //***
      // validate triangle options
      //***
      
      if (isValid && theGenerateTrianglesFlag)
      {
         if (theNumberOfTriangles <= 0)
         {
            msg = "Triangles file must contain at least 1 element";
            isValid = false;
         }
         if ((theTriangleBBoxHeight <= 0) || (theTriangleBBoxHeight >= theSceneLength))
         {
            msg = "Maximum triangle height has to be < scene length, and > 0.";
            isValid = false;
         }
         if ((theTriangleBBoxWidth <= 0) || (theTriangleBBoxWidth >= theSceneLength))
         {
            msg = "Maximum triangle width has to be < scene length, and > 0.";
            isValid = false;
         }
      }

      //***
      // validate polygon options
      //***

      if (isValid && theGeneratePolygonsFlag)
      {
         if (theNumberOfPolygons <= 0)
         {
            msg = "Polygon file must contain at least 1 element";
            isValid = false;
         }
         if (thePolygonMaxVertexCount < 3)
         {
            msg = "Polygons must have at least 3 verticies.";
            isValid = false;
         }
         if (thePolygonMinVertexCount < 3)
         {
            msg = "Polygons must have at least 3 verticies.";
            isValid = false;
         }
         if (thePolygonMinVertexCount > thePolygonMaxVertexCount)
         {
            msg = "The Minimum Vertex Count should not be more than the Maximum.";
            isValid = false;
         }
         if ((thePolygonBBoxLength <= 0) || (thePolygonBBoxLength >= theSceneLength))
         {
            msg = "Maximum polygon width has to be < scene length, and > 0.";
            isValid = false;
         }
      }

      //***
      // validate line string options
      //***
      
      if (isValid && theGenerateLineStringsFlag)
      {
         if (theNumberOfLineStrings <= 0)
         {
            msg = "Line string file must contain at least 1 element";
            isValid = false;
         }
         if (theLineStringMaxSegmentCount < 1)
         {
            msg = "LineStrings must have at least 1 segments.";
            isValid = false;
         }
         if (theLineStringMinSegmentCount < 1)
         {
            msg = "LineStrings must have at least 1 segments.";
            isValid = false;
         }
         if (theLineStringMaxSegmentCount < theLineStringMinSegmentCount)
         {
            msg = "The Minimum Segment Count must not be more than the Maximum.";
            isValid = false;
         }
         
      }

      //***
      // validate random walk options
      //***

      if (isValid && theGenerateRandomWalksFlag)
      {
         if (theNumberOfRandomWalks <= 0)
         {
            msg = "Random Walk file must contain at least 1 element";
            isValid = false;
         }
         if ((theMaximumStepLength <= 0) || (theMaximumStepLength >= theSceneLength))
         {
            msg = "Maximum step length has to be < scene length, and > 0";
            isValid = false;
         }
         if ((theNumberOfSteps <= 2) || (theNumberOfSteps >= theSceneLength))
         {
            msg = "The number of steps has to be < scene length, and > 2";
            isValid = false;
         }
      }
      //***
      // validate conic spiral options
      //***

      if (isValid && theGenerateConicSpiralsFlag)
      {
         if (theNumberOfConicSpirals <= 0)
         {
            msg = "Conic Spiral file must contain at least 1 element";
            isValid = false;
         }
         if ((theMaximumRadiusLength <= 0) || (theMaximumRadiusLength >= theSceneLength))
         {
            msg = "Maximum radius length has to be < scene length, and > 0";
            isValid = false;
         }
         if ((theSegmentInc < 10) || (theSegmentInc > 50)) {
        	 msg = "Segment increment has to be > 10 and < 50";
             isValid = false;
         }
         if ((theRadius < 0) || (theRadius > theMaximumRadiusLength)) {
        	 msg = "Radius has to be > 0 and < Maximum radius length";
             isValid = false;
         }
      }
      //***
      // validate Quick-Star Polygons options
      //***

      if (isValid && theGenerateQSPolygonsFlag)
      {
         if (theNumberOfQSPolygons <= 0)
         {
            msg = "Quick-Star Polygon file must contain at least 1 element";
            isValid = false;
         }
         if ((theNumberOfQSPolygons <= 0) || (theNumberOfQSPolygons >= theSceneLength))
         {
            msg = "Maximum radius length has to be < scene length, and > 0";
            isValid = false;
         }
      }
      //********************************************************
      //***
      // validate square options -> needs validation
      //***
      //********************************************************
      
      //***
      // show error message
      //***

      if (isValid == false)
      {
         JOptionPane.showMessageDialog(
            aFrame,
            msg,
            "Notice",
            JOptionPane.INFORMATION_MESSAGE,
            null);
      }

      return isValid;
   }

   /*
    * save()
    *
    * This method saves the model content to disk
    */
   
   public void save(JFrame anAppFrame)
   {
      if (TRACE)
         System.out.println("DataGenModel: save options file ");

      // get file for model update
      String outputFn = getOutputFilename(anAppFrame);
      if (outputFn.length() == 0)
         return;

      // do model update
      if (writeData(outputFn) == false)
      {
         JOptionPane.showMessageDialog(
            anAppFrame,
            "Error saving to options file.",
            "Notice",
            JOptionPane.INFORMATION_MESSAGE,
            null);
      }
   }

   /**
    * load()
    *
    * This method loads the model content from disk
    */
   public void load(JFrame anAppFrame)
   {
      if (TRACE)
         System.out.println("DataGenModel: load options file");

      // get file for model update
      String inputFn = getInputFilename(anAppFrame);
      if (inputFn.length() == 0)
         return;

      // do model update
      if (readData(inputFn) == false)
      {
         JOptionPane.showMessageDialog(
            anAppFrame,
            "Error opening selected options file.",
            "Notice",
            JOptionPane.INFORMATION_MESSAGE,
            null);
      }
   }

   /**
    * getInputFilename()
    *
    * This method returns the input filename
    */
   private String getInputFilename(JFrame anAppFrame)
   {
      String fn = "";

      // setup file chooser
      JFileChooser csvChooser = new JFileChooser();
      CSVFileFilter filter = new CSVFileFilter();
      csvChooser.setFileFilter(filter);

      // prompt user for input file
      int returnVal = csvChooser.showOpenDialog(anAppFrame);

      // process result
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
         fn = csvChooser.getSelectedFile().getPath();
         if (TRACE)
            System.out.println("DataGenModel: opening " + fn);
      }
      return fn;
   }

   /*
    * getOutputFilename()
    *
    * This method returns the output filename
    */
   
   private String getOutputFilename(JFrame anAppFrame)
   {
      String fn = "";

      // setup file chooser
      JFileChooser csvChooser = new JFileChooser();
      CSVFileFilter filter = new CSVFileFilter();
      csvChooser.setFileFilter(filter);

      // prompt user for output file
      int returnVal = csvChooser.showSaveDialog(anAppFrame);

      // process result
      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
         fn = csvChooser.getSelectedFile().getPath();
         if (TRACE)
            System.out.println("DataGenModel: saving " + fn);
      }
      return fn;
   }

   /**
    * writeData()
    *
    * This method write the data to the given CSV file
    */
   private boolean writeData(String aCSVFilename)
   {
      try
      {
         // open the chosen file
         File file = new File(aCSVFilename);
         BufferedWriter bufWtr = new BufferedWriter(new FileWriter(file));

         // write each line to text file
         writeKeyValuePairs(bufWtr);

         //close the file
         bufWtr.close();
         return true;
      }
      catch (Exception e)
      {
         return false;
      }
   }

   /*
    * readData()
    *
    * This method read the data from the given CSV file
    */
   
   private boolean readData(String aCSVFilename)
   {
      try
      {
         // open the chosen file
         File file = new File(aCSVFilename);
         BufferedReader bufRdr = new BufferedReader(new FileReader(file));

         // read each line of text file
         String line = null;
         while ((line = bufRdr.readLine()) != null)
         {
            StringTokenizer st = new StringTokenizer(line, ",");
            while (st.hasMoreTokens())
            {
               String key = st.nextToken();
               String value = st.nextToken();
               processKeyValuePair(key, value);
            }
         }
         //close the file
         bufRdr.close();
         return true;
      }
      catch (Exception e)
      {
         return false;
      }
   }

   /*
    * processKeyValuePair()
    *
    * This method converts the key/value pair into appropriate option
    */
   
   private void processKeyValuePair(String aKey, String aValue)
   {
      if (TRACE)
         System.out.println("DataGenModel: " + aKey + " " + aValue);

      // scene options
      if (aKey.equalsIgnoreCase("SceneLength"))
         theSceneLength = Double.valueOf(aValue);
      if (aKey.equalsIgnoreCase("FilenamePrefix"))
         theFilenamePrefix = aValue;

      // point options
      if (aKey.equalsIgnoreCase("GeneratePointsFlag"))
         theGeneratePointsFlag = Boolean.valueOf(aValue);
      if (aKey.equalsIgnoreCase("NumberOfPoints"))
         theNumberOfPoints = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("UniquePointsFlag"))
         theUniquePointsFlag = Boolean.valueOf(aValue);

      // square options
      if (aKey.equalsIgnoreCase("GenerateSquaresFlag"))
         theGenerateSquaresFlag = Boolean.valueOf(aValue);
      if (aKey.equalsIgnoreCase("NumberOfSquares"))
         theNumberOfSquares = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("MaximumSquareSideLength"))
         theMaximumSquareSideLength = Double.valueOf(aValue);

      // triangle options
      if (aKey.equalsIgnoreCase("GenerateTrianglesFlag"))
         theGenerateTrianglesFlag = Boolean.valueOf(aValue);
      if (aKey.equalsIgnoreCase("NumberOfTriangles"))
         theNumberOfTriangles = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("TriangleBBoxHeight"))
         theTriangleBBoxHeight = Double.valueOf(aValue);
      if (aKey.equalsIgnoreCase("TriangleBBoxWidth"))
         theTriangleBBoxWidth = Double.valueOf(aValue);

      // polygon options
      if (aKey.equalsIgnoreCase("GeneratePolygonsFlag"))
         theGeneratePolygonsFlag = Boolean.valueOf(aValue);
      if (aKey.equalsIgnoreCase("NumberOfPolygons"))
         theNumberOfPolygons = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("PolygonMaxVertexCount"))
         thePolygonMaxVertexCount = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("PolygonBBoxLength"))
         thePolygonBBoxLength = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("PolygonMinVertexCount"))
    	  thePolygonMinVertexCount = Integer.valueOf(aValue);
  
      // linestring options
      if (aKey.equalsIgnoreCase("GenerateLineStringsFlag"))
         theGenerateLineStringsFlag = Boolean.valueOf(aValue);
      if (aKey.equalsIgnoreCase("NumberOfLineStrings"))
         theNumberOfLineStrings = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("LineStringMaxSegmentCount"))
         theLineStringMaxSegmentCount = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("LineStringMinSegmentCount"))
          theLineStringMinSegmentCount = Integer.valueOf(aValue);
      
      // random walks options
      if (aKey.equalsIgnoreCase("GenerateRandomWalkFlag"))
          theGenerateRandomWalksFlag = Boolean.valueOf(aValue);
      if (aKey.equalsIgnoreCase("NumberOfRandomWalks"))
          theNumberOfRandomWalks = Integer.valueOf(aValue);
      if (aKey.equalsIgnoreCase("MaximumStepLength"))
          theMaximumStepLength = Double.valueOf(aValue);
      if (aKey.equals("NumberOfSteps"))
    	   theNumberOfSteps = Integer.valueOf(aValue);
       
       // conic spiral options
       if (aKey.equalsIgnoreCase("GenerateConicSpiralFlag"))
    	   theGenerateConicSpiralsFlag = Boolean.valueOf(aValue);
       if (aKey.equalsIgnoreCase("NumberOfConicSpirals"))
        	theNumberOfConicSpirals = Integer.valueOf(aValue);
       if (aKey.equalsIgnoreCase("MaximumRadiusLength"))
        	theMaximumRadiusLength = Double.valueOf(aValue);
       if (aKey.equalsIgnoreCase("SegmentInc"))
       		theSegmentInc = Double.valueOf(aValue);
       if (aKey.equalsIgnoreCase("BaseRadius"))
      		theRadius = Double.valueOf(aValue);
       if (aKey.equals("AngleGapLower"))
        	theAngleGapLower = Double.valueOf(aValue);
       if (aKey.equals("AngleGapUpper"))
       		theAngleGapUpper = Double.valueOf(aValue);
       
       // Midpoint Displacement options
       if (aKey.equalsIgnoreCase("GenerateMidPointDisplacemntFlag"))
    	   theGenerateMidpointDisplacementsFlag = Boolean.valueOf(aValue);
       if (aKey.equalsIgnoreCase("NumberOfMidPointDisplacemnts"))
    	   theNumberofMidpointDisplacements = Integer.valueOf(aValue);
       if (aKey.equalsIgnoreCase("RecursionDepth"))
    	   theRecursionDepth = Integer.valueOf(aValue);
       if (aKey.equals("DisplacementBound"))
    	   theDisplacementBound = Double.valueOf(aValue);
       if(aKey.equals("DisplacementBoundReduction"))
    	   theDisplacementBoundReduction = Double.valueOf(aValue);
       
       // quick-star polygon options
       if (aKey.equalsIgnoreCase("theGenerateQSPolygonsFlag"))
    	   theGenerateQSPolygonsFlag = Boolean.valueOf(aValue);
       if (aKey.equalsIgnoreCase("theNumberOfQSPolygons"))
    	   theNumberOfQSPolygons = Integer.valueOf(aValue);
       if (aKey.equalsIgnoreCase("theNumberOfQSVertices"))
    	   theNumberOfQSVertices = Integer.valueOf(aValue);
       if (aKey.equalsIgnoreCase("theStarRadius"))
    	   theStarRadius = Double.valueOf(aValue);
   }

   /*
    * writeKeyValuePair()
    *
    * This method converts the key/value pair into appropriate option
    */
   
   private void writeKeyValuePairs(BufferedWriter bufWtr) throws IOException
   {
      // scene options
      bufWtr.write("SceneLength," + Double.toString(theSceneLength) + "\n");
      bufWtr.write("FilenamePrefix," + theFilenamePrefix + "\n");

      // point options
      bufWtr.write("GeneratePointsFlag," + Boolean.toString(theGeneratePointsFlag) + "\n");
      bufWtr.write("NumberOfPoints," + Integer.toString(theNumberOfPoints) + "\n");
      bufWtr.write("UniquePointsFlag," + Boolean.toString(theUniquePointsFlag) + "\n");

      // square options
      bufWtr.write("GenerateSquaresFlag," + Boolean.toString(theGenerateSquaresFlag) + "\n");
      bufWtr.write("NumberOfSquares," + Integer.toString(theNumberOfSquares) + "\n");
      bufWtr.write("MaximumSquareSideLength," + Double.toString(theMaximumSquareSideLength) + "\n");

      // triangle options
      bufWtr.write("GenerateTrianglesFlag," + Boolean.toString(theGenerateTrianglesFlag) + "\n");
      bufWtr.write("NumberOfTriangles," + Integer.toString(theNumberOfTriangles) + "\n");
      bufWtr.write("TriangleBBoxHeight," + Double.toString(theTriangleBBoxHeight) + "\n");
      bufWtr.write("TriangleBBoxWidth," + Double.toString(theTriangleBBoxWidth) + "\n");

      // polygon options
      bufWtr.write("GeneratePolygonsFlag," + Boolean.toString(theGeneratePolygonsFlag) + "\n");
      bufWtr.write("NumberOfPolygons," + Integer.toString(theNumberOfPolygons) + "\n");
      bufWtr.write("PolygonMaxVertexCount," + Integer.toString(thePolygonMaxVertexCount) + "\n");
      bufWtr.write("PolygonBBoxLength," + Integer.toString(thePolygonBBoxLength) + "\n");
      bufWtr.write("PolygonMinVertexCount," + Integer.toString(thePolygonMinVertexCount) + "\n");
      

      // linestring options
      bufWtr.write("GenerateLineStringsFlag," + Boolean.toString(theGenerateLineStringsFlag) + "\n");
      bufWtr.write("NumberOfLineStrings," + Integer.toString(theNumberOfLineStrings) + "\n");
      bufWtr.write("LineStringMaxSegmentCount," + Integer.toString(theLineStringMaxSegmentCount) + "\n");
      bufWtr.write("LineStringMinSegmentCount," + Integer.toString(theLineStringMinSegmentCount) + "\n");
      
      // random walks options
      bufWtr.write("GenerateRandomWalkFlag," + Boolean.toString(theGenerateRandomWalksFlag) + "\n");
      bufWtr.write("NumberOfRandomWalks," + Integer.toString(theNumberOfRandomWalks) + "\n");
      bufWtr.write("MaximumStepLength," + Double.toString(theMaximumStepLength) + "\n");
      bufWtr.write("NumberOfSteps," + Integer.toString(theNumberOfSteps) + "\n");
      
      // conic spiral options
      bufWtr.write("GenerateConicSpiralFlag," + Boolean.toString(theGenerateConicSpiralsFlag) + "\n");
      bufWtr.write("NumberOfConicSpirals," + Integer.toString(theNumberOfConicSpirals) + "\n");
      bufWtr.write("MaximumRadiusLength," + Double.toString(theMaximumRadiusLength) + "\n");
      bufWtr.write("SegmentInc," + Double.toString(theSegmentInc) + "\n");
      bufWtr.write("BaseRadius," + Double.toString(theRadius) + "\n");
      bufWtr.write("AngleGapLower," + Double.toString(theAngleGapLower) + "\n");
      bufWtr.write("AngleGapUpper," + Double.toString(theAngleGapUpper) + "\n");
      
      // Midpoint Displacement options
      bufWtr.write("midpointDisplacementFlag," + Boolean.toString(theGenerateMidpointDisplacementsFlag) + "\n");
      bufWtr.write("NumberOfMidpointDisplacements," + Integer.toString(theNumberofMidpointDisplacements) + "\n");
      bufWtr.write("RecursionDepth," + Integer.toString(theRecursionDepth) + "\n");
      bufWtr.write("DisplacementBound," + Double.toString(theDisplacementBound) + "\n");
      bufWtr.write("DisplacementBoundReduction," + Double.toString(theDisplacementBoundReduction) + "\n");
      
      // quick-star polygon options
      bufWtr.write("theGenerateQSPolygonsFlag," + Boolean.toString(theGenerateQSPolygonsFlag) + "\n");
      bufWtr.write("theNumberOfQSPolygons," + Integer.toString(theNumberOfQSPolygons) + "\n");
      bufWtr.write("theNumberOfQSVertices," + Integer.toString(theNumberOfQSVertices) + "\n");
      bufWtr.write("theStarRadius," + Double.toString(theStarRadius) + "\n");
   }
}
