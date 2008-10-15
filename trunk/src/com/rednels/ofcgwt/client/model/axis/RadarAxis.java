/*
Copyright (C) 2008 Grant Slender

This file is part of OFCGWT.

OFCGWT is free software: you can redistribute it and/or modify
it under the terms of the Lesser GNU General Public License as
published by the Free Software Foundation, either version 3 of
the License, or (at your option) any later version.

OFCGWT is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

See <http://www.gnu.org/licenses/lgpl-3.0.txt>.
*/
package com.rednels.ofcgwt.client.model.axis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.rednels.ofcgwt.client.model.JSONizable;
/**
 * Class for an OFC radar axis that extends AbstractAxis   
 * @see com.rednels.ofcgwt.client.model.axis.AbstractAxis
 */
public class RadarAxis extends AbstractAxis implements JSONizable {

    /** The labels. */
    private Labels labels;
    
    /** The spoke-labels. */
    private Labels spokelabels;
    
    /**
     * Gets the labels.
     * 
     * @return the labels
     */
    public Labels getLabels() {
        return labels;
    }
    
    /**
     * Sets the radar axis labels.
     * 
     * @param labels the new radar axis labels
     */
    public void setRadarAxisLabels(Labels labels) {
        this.labels = labels;
    }
    
    /**
     * Sets the labels.
     * 
     * @param labels the new labels
     */
    public void setLabels(String... labels) {
        this.labels = new Labels(labels);
    }

    /**
     * Sets the labels.
     * 
     * @param labels the new labels
     */
    public void setLabels(List<String> labels) {
        this.labels = new Labels(labels);
    }
    
    /**
     * Adds the labels.
     * 
     * @param labels the labels
     */
    public void addLabels(String... labels) {
        checkLabelsNotNull();
        this.labels.addLabels(labels);
    }
    
    /**
     * Check labels not null.
     */
    private synchronized void checkLabelsNotNull() {
        if (labels == null) labels = new Labels();
    }    
    

    /**
     * Gets the spoke labels.
     * 
     * @return the labels
     */
    public Labels getSpokeLabels() {
        return spokelabels;
    }
    
    /**
     * Sets the radar axis spoke labels.
     * 
     * @param labels the new radar axis spoke labels
     */
    public void setSpokeLabels(Labels labels) {
        this.spokelabels = labels;
    }
    
    /**
     * Sets the spoke labels.
     * 
     * @param labels the new spoke labels
     */
    public void setSpokeLabels(String... labels) {
        this.spokelabels = new Labels(labels);
    }

    /**
     * Sets the spoke labels.
     * 
     * @param labels the new spoke labels
     */
    public void setSpokeLabels(List<String> labels) {
        this.spokelabels = new Labels(labels);
    }
    
    /**
     * Adds the spoke labels.
     * 
     * @param labels the labels
     */
    public void addSpokeLabels(String... labels) {
        checkSpokeLabelsNotNull();
        this.spokelabels.addLabels(labels);
    }
        
    /**
     * Check labels not null.
     */
    private synchronized void checkSpokeLabelsNotNull() {
        if (spokelabels == null) spokelabels = new Labels();
    }    

	/* (non-Javadoc)
	 * @see com.rednels.ofcgwt.client.model.axis.AbstractAxis#buildJSONObject()
	 */
	public JSONObject buildJSONObject() {		
    	JSONObject json = super.buildJSONObject();     	
    	if (labels != null) json.put("labels", labels.buildJSONObject());
    	if (spokelabels != null) json.put("spoke-labels", spokelabels.buildJSONObject());
    	return json;
	}

	/**
	 * Class for an OFC radar axis label 
	 */
	public class Labels implements JSONizable {
	    
    	/** The steps. */
	    private String colour;
	    
    	/** The labels. */
    	private List<Object> labels;
	    	    
	    /**
    	 * Creates a new labels.
    	 * 
    	 * @param labels the labels
    	 */
    	public Labels(String... labels) {
	        addLabels(labels);
	    }
	    
	    /**
    	 * Creates a new labels.
    	 * 
    	 * @param labels the labels
    	 */
    	public Labels(List<String> labels) {
	    	 checkLabelsNotNull();
	         this.labels.addAll(labels);
	    }    
	    
	    /**
    	 * Gets the labels.
    	 * 
    	 * @return the labels
    	 */
    	public List<Object> getLabels() {
	        return labels;
	    }
	    
	    /**
    	 * Adds the labels.
    	 * 
    	 * @param labels the labels
    	 */
    	public void addLabels(String... labels) {
	        checkLabelsNotNull();
	        this.labels.addAll(Arrays.asList(labels));
	    }
	    
	    /**
    	 * Adds the labels.
    	 * 
    	 * @param labels the labels
    	 */
    	public void addLabels(Label... labels) {
	        checkLabelsNotNull();
	        this.labels.addAll(Arrays.asList(labels));
	    }
	    
	    /**
    	 * Adds the labels.
    	 * 
    	 * @param labels the labels
    	 */
    	public void addLabels(List<Label> labels) {
	        checkLabelsNotNull();
	        this.labels.addAll(labels);
	    }
	    	    
	    /**
    	 * Check labels not null.
    	 */
    	private synchronized void checkLabelsNotNull() {
	        if (labels == null) labels = new ArrayList<Object>();
	    }
    	
        /**
         * Gets the colour.
         * 
         * @return the colour
         */
        public String getColour() {
            return colour;
        }
        
        /**
         * Sets the colour in HTML hex format (#ffffff) 
         * 
         * @param colour the new colour
         */
        public void setColour(String colour) {
            this.colour = colour;
        }
        
    	/* (non-Javadoc)
    	 * @see com.rednels.ofcgwt.client.model.JSONizable#buildJSONObject()
    	 */
		public JSONObject buildJSONObject() {		
	    	JSONObject json = new JSONObject();
	    	if (colour != null) json.put("colour", new JSONString(colour));
	    	if (labels == null) return json;
	    	JSONArray ary = new JSONArray();
	    	int index = 0;
	    	for (Object o : getLabels()) {
	    		if (o instanceof String) ary.set(index++, new JSONString((String)o));
	    		if (o instanceof Label) ary.set(index++, ((Label)o).buildJSONObject());
	        }
	    	if (index != 0) json.put("labels",ary);
	    	return json;
		}
	}
}
