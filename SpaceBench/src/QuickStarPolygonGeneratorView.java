/*
 * QuickStarPolygonGeneratorView.java
 *
 * Authors: Jacob Gollert, Anton Medvedev, Gregory Lucas Moody, Hamad Altammami
 * Version Date: 4/13/2017
 * 
 * This file has to do with creating the panel for the Quick Star polygon generator
 */

import javax.swing.*;
import java.awt.event.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent; 
import java.text.NumberFormat;

public class QuickStarPolygonGeneratorView implements PropertyChangeListener, ItemListener
{
   //***
   // class variables
   //***

   private static final boolean TRACE = false;
   
   private static final String TAB_TITLE = "Quick-Star Polygons";
   private static final String TAB_TOOLTIP = "Quick-Star Polygon generator options";
   private static final String GENERATE_CHECKBOX_TITLE = "Generate data file?";
   private static final boolean DEFAULT_GENERATE_FLAG = true;
   private static final int DEFAULT_POLYGON_COUNT = 10;
   private static final int DEFAULT_MAX_VERTEX_COUNT = 4;
   //private static final int DEFAULT_BBOX_LENGTH = 100;
   private static final double DEFAULT_RADIUS_LENGTH = 100;
   
   //***
   // instance variables
   //***

   // parent panel for all elements
   private JPanel theTabbedPanePanel;

   // elements for 'generate' flag
   private JCheckBox theGenerateCheckbox;
   private JPanel theGeneratePanel;

   // elements for 'number of polygons'
   private NumberFormat theNumberOfPolygonsFormat;
   private JPanel theNumberOfPolygonsPanel;
   private JLabel theNumberOfPolygonsLabel;
   private JFormattedTextField theNumberOfPolygonsField;

   // elements for 'max vertex count'
   private NumberFormat theMaximumVertexCountFormat;
   private JPanel theMaximumVertexCountPanel;
   private JLabel theMaximumVertexCountLabel;
   private JFormattedTextField theMaximumVertexCountField;

   /*
   // elements for 'bounding box length'
   private NumberFormat theBBoxLengthFormat;
   private JPanel theBBoxLengthPanel;
   private JLabel theBBoxLengthLabel;
   private JFormattedTextField theBBoxLengthField;
   */
   
   
   // elements for 'star radius'
   private NumberFormat theStarRadiusFormat;
   private JPanel theStarRadiusPanel;
   private JLabel theStarRadiusLabel;
   private JFormattedTextField theStarRadiusField;

   // property values
   private boolean theGenerateFlag;
   private int theNumberOfQSPolygons;
   private int theNumberOfQSVertices;
   //private int theBBoxLength;
   private double theStarRadius;

   /*
    * PolygonGeneratorView
    *
    * This class implements the view for the QuickStarPolygonGenerator class and
    * handles related user interface events
    */
   
   QuickStarPolygonGeneratorView()
   {
      theGenerateFlag = DEFAULT_GENERATE_FLAG;
      theNumberOfQSPolygons = DEFAULT_POLYGON_COUNT;
      theNumberOfQSVertices = DEFAULT_MAX_VERTEX_COUNT;
      //theBBoxLength = DEFAULT_BBOX_LENGTH;
      theStarRadius = DEFAULT_RADIUS_LENGTH;
   }

   /*
    * setGenerateFlag
    *
    * This method sets the generate datafile property
    */
   
   public void setGenerateFlag(boolean aFlag)
   {
      if (theGenerateCheckbox != null)
      {
         if (theGenerateFlag != aFlag)
         {
            theGenerateCheckbox.doClick();
            theGenerateCheckbox.updateUI();
         }
      }
   }
   
   /*
    * setNumberOfPolygons
    * 
    * This method sets the current number of polygons to be generated
    */
   
   public void setNumberOfQSPolygons(int aCount)
   {
      theNumberOfQSPolygons = aCount;
      if (theNumberOfPolygonsField != null)
      {
         theNumberOfPolygonsField.setValue(theNumberOfQSPolygons);
         theNumberOfPolygonsField.updateUI();
      }
   }
   
   /*
    * setMaximumVertexCount
    *
    * This method sets the current maximum vertices to be generated
    */
   
   public void setVertexCount(int aCount)
   {
	   theNumberOfQSVertices = aCount;
      if (theMaximumVertexCountField != null)
      {
         theMaximumVertexCountField.setValue(theNumberOfQSVertices);
         theMaximumVertexCountField.updateUI();
      }
   }
   
   /*
    * setStarRadius
    *
    * This method sets the radius vertices can be generated within
    */
   
   public void setStarRadius(double aCount)
   {
	   theStarRadius = aCount;
      if (theStarRadiusField != null)
      {
    	 theStarRadiusField.setValue(theStarRadius);
    	 theStarRadiusField.updateUI();
      }
   }

   /*
    * setBBoxLength
    * 
    * This method sets the current maximum bounding box length
    */
   /*
   public void setBBoxLength(int aLength)
   {
      theBBoxLength = aLength;
      if (theBBoxLengthField != null)
      {
         theBBoxLengthField.setValue(theBBoxLength);
         theBBoxLengthField.updateUI();
      }
   }
	*/
  /*
   * getGenerateFlag
   *
   * This method returns the generate datafile property
   */
   
   public boolean getGenerateFlag()
   {
      return theGenerateFlag;
   }
   
   /*
    * getNumberOfPolygons
    *
    * This method returns the current number of polygons
    */
   
    public int getNumberOfQSPolygons()
    {
       return theNumberOfQSPolygons;
    }

    /*
     * getMaximumVertexCount
     *
     * This method returns the current maximum vertex count
     */
    
    public int getVertexCount()
    {
       return theNumberOfQSVertices;
    }
    
    /*
     * getStarRadius
     *
     * This method returns the quick-star radius 
     */
    
    public double getStarRadius()
    {
       return theStarRadius;
    }

    /*
     * getBBoxLength
     * 
     * This method returns the current bounding box side length
     */
    /*
    public int getBBoxLength()
    {
       return theBBoxLength;
    }
    */
    /*
     * build
     * 
     * This method builds the user interface and ties in any
     * event listeners
     */
    
    public void build(JTabbedPane aTabbedPane)
    {
       //***
       // generate flag
       //***

       // create generate flag [checkbox]
       theGenerateCheckbox = new JCheckBox(GENERATE_CHECKBOX_TITLE);
       theGenerateCheckbox.setSelected(theGenerateFlag);
       theGenerateCheckbox.addItemListener(this);

       // add to containing panel
       theGeneratePanel = new JPanel();
       theGeneratePanel.add(theGenerateCheckbox);

       //***
       // number of polygons
       //***

       // build format arguments
       theNumberOfPolygonsFormat = NumberFormat.getIntegerInstance();

       // create number of elements [label, field]
       theNumberOfPolygonsLabel = new JLabel("Number of quick-star polygons:");
       theNumberOfPolygonsLabel.setHorizontalAlignment(JLabel.LEFT);
       theNumberOfPolygonsField = new JFormattedTextField(theNumberOfPolygonsFormat);
       theNumberOfPolygonsField.setValue(new Double(theNumberOfQSPolygons));
       theNumberOfPolygonsField.setColumns(10);
       theNumberOfPolygonsField.addPropertyChangeListener("value", this);

       // add to containing panel
       theNumberOfPolygonsPanel = new JPanel();
       theNumberOfPolygonsPanel.add(theNumberOfPolygonsLabel);
       theNumberOfPolygonsPanel.add(theNumberOfPolygonsField);

       //***
       // maximum vertex count
       //***

       // build format arguments
       theMaximumVertexCountFormat = NumberFormat.getIntegerInstance();

       // create number of point elements [label, field]
       theMaximumVertexCountLabel = new JLabel("Maximum vertex count:");
       theMaximumVertexCountLabel.setHorizontalAlignment(JLabel.LEFT);
       theMaximumVertexCountField = new JFormattedTextField(theMaximumVertexCountFormat);
       theMaximumVertexCountField.setValue(new Double(theNumberOfQSVertices));
       theMaximumVertexCountField.setColumns(10);
       theMaximumVertexCountField.addPropertyChangeListener("value", this);

       // add to containing panel
       theMaximumVertexCountPanel = new JPanel();
       theMaximumVertexCountPanel.add(theMaximumVertexCountLabel);
       theMaximumVertexCountPanel.add(theMaximumVertexCountField);
       
       //***
       // star radius 
       //***

       // build format arguments
       theStarRadiusFormat = NumberFormat.getNumberInstance();

       // create number of point elements [label, field]
       theStarRadiusLabel = new JLabel("Star Radius Length:");
       theStarRadiusLabel.setHorizontalAlignment(JLabel.LEFT);
       theStarRadiusField = new JFormattedTextField(theStarRadiusFormat);
       theStarRadiusField.setValue(new Double(theStarRadius));
       theStarRadiusField.setColumns(10);
       theStarRadiusField.addPropertyChangeListener("value", this);

       // add to containing panel
       theStarRadiusPanel = new JPanel();
       theStarRadiusPanel.add(theStarRadiusLabel);
       theStarRadiusPanel.add(theStarRadiusField);
       
       /*
       //***
       // bounding box length
       //***

       // build format arguments
       theBBoxLengthFormat = NumberFormat.getNumberInstance();

       // create number of point elements [label, field]
       theBBoxLengthLabel = new JLabel("Maximum side length:");
       theBBoxLengthLabel.setHorizontalAlignment(JLabel.LEFT);
       theBBoxLengthField = new JFormattedTextField(theBBoxLengthFormat);
       theBBoxLengthField.setValue(new Double(theBBoxLength));
       theBBoxLengthField.setColumns(10);
       theBBoxLengthField.addPropertyChangeListener("value", this);

       // add to containing panel
       theBBoxLengthPanel = new JPanel();
       theBBoxLengthPanel.add(theBBoxLengthLabel);
       theBBoxLengthPanel.add(theBBoxLengthField);
       
       */
       
       //***
       // update tabbed pane
       //***

       // build tab
       theTabbedPanePanel = new JPanel();
       theTabbedPanePanel.setLayout(new BoxLayout(theTabbedPanePanel, BoxLayout.PAGE_AXIS));
       theTabbedPanePanel.add(theGeneratePanel);
       theTabbedPanePanel.add(theNumberOfPolygonsPanel);
       theTabbedPanePanel.add(theMaximumVertexCountPanel);
       theTabbedPanePanel.add(theStarRadiusPanel);
       //theTabbedPanePanel.add(theBBoxLengthPanel);

       // add new tab to tabbed pane
       aTabbedPane.addTab(TAB_TITLE, null, theTabbedPanePanel, TAB_TOOLTIP);
    }
    
    /*
     * propertyChange
     * 
     * Called when a field's "value" property changes
     */
    
    public void propertyChange(PropertyChangeEvent e)
    {
       Object source = e.getSource();
       if (source == theNumberOfPolygonsField)
       {
          theNumberOfQSPolygons = ((Number)theNumberOfPolygonsField.getValue()).intValue();
          if (TRACE)
             System.out.println("Quick-Star Polygons: number of squares = " + theNumberOfQSPolygons);
       }
       else if (source == theMaximumVertexCountField)
       {
    	   theNumberOfQSVertices = ((Number)theMaximumVertexCountField.getValue()).intValue();
          if (TRACE)
             System.out.println("Quick-Star Polygons: maximum vertex count = " + theNumberOfQSVertices);
       }
       else if (source == theStarRadiusField) {
    	   theStarRadius = ((Number)theStarRadiusField.getValue()).intValue();
           if (TRACE)
              System.out.println("Quick-Star Polygons: star radius length = " + theStarRadius);
       }
       /*
       else if (source == theBBoxLengthField)
       {
          theBBoxLength = ((Number)theBBoxLengthField.getValue()).intValue();
          if (TRACE)
             System.out.println("Quick-Star Polygons: maximum side length = " + theBBoxLength);
       }
       */
       
    }
    
    /*
     * itemStateChanged
     *
     * Called when a checkbox's state changes
     */
    
    public void itemStateChanged(ItemEvent e)
    {
       Object source = e.getItemSelectable();
       if (source == theGenerateCheckbox)
       {
          theGenerateFlag = !theGenerateFlag;
          if (theGenerateFlag)
          {
            theNumberOfPolygonsField.setEnabled(true);
            theMaximumVertexCountField.setEnabled(true);
            //theBBoxLengthField.setEnabled(true);
            theStarRadiusField.setEnabled(true);
          }
          else
          {
            theNumberOfPolygonsField.setEnabled(false);
            theMaximumVertexCountField.setEnabled(false);
            //theBBoxLengthField.setEnabled(false);
            theStarRadiusField.setEnabled(false);
          }
          if (TRACE)
             System.out.println("Quick-Star Polygons: generate = " + theGenerateFlag);
       }
    }
 }
