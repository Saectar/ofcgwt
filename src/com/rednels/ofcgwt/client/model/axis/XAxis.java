/*
Copyright (C) 2009 Grant Slender

This file is part of OFCGWT.
http://code.google.com/p/ofcgwt/

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
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC x-axis
 */
public class XAxis extends AbstractAxis implements JSONizable {

	/**
	 * OFC x-axis label
	 */
	public class Labels extends Label implements JSONizable {

		private Integer steps;
		private Rotation rotate;
		private String colour;
		private Integer size;
		private Boolean visible;
		private List<Object> labels;

		/**
		 * Creates a new labels.
		 * 
		 * @param labels
		 *            the labels
		 */
		public Labels(List<String> labels) {
			checkLabelsNotNull();
			this.labels.addAll(labels);
		}

		/**
		 * Creates a new labels.
		 * 
		 * @param labels
		 *            the labels
		 */
		public Labels(String... labels) {
			addLabels(labels);
		}

		/**
		 * Adds the labels.
		 * 
		 * @param labels
		 *            the labels
		 */
		public void addLabels(Label... labels) {
			checkLabelsNotNull();
			this.labels.addAll(Arrays.asList(labels));
		}

		/**
		 * Adds the labels.
		 * 
		 * @param labels
		 *            the labels
		 */
		public void addLabels(List<Label> labels) {
			checkLabelsNotNull();
			this.labels.addAll(labels);
		}

		/**
		 * Adds the labels.
		 * 
		 * @param labels
		 *            the labels
		 */
		public void addLabels(String... labels) {
			checkLabelsNotNull();
			this.labels.addAll(Arrays.asList(labels));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.rednels.ofcgwt.client.model.axis.Label.buildJSON()
		 */
		public JSONValue buildJSON() {
			JSONObject json = (JSONObject) super.buildJSON();
			if (steps != null) json.put("steps", new JSONNumber(steps));
			if (colour != null) json.put("colour", new JSONString(colour));
			if (size != null) json.put("size", new JSONNumber(size));
			if (rotate != null) json.put("rotate", new JSONString(rotate.toString()));
			if (visible != null) json.put("visible", JSONBoolean.getInstance(visible));
			if (labels == null) return json;
			JSONArray ary = new JSONArray();
			int index = 0;
			for (Object o : getLabels()) {
				if (o instanceof String) ary.set(index++, new JSONString((String) o));
				if (o instanceof Label) ary.set(index++, ((Label) o).buildJSON());
			}
			if (index != 0) json.put("labels", ary);
			return json;
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
		 * Gets the labels.
		 * 
		 * @return the labels
		 */
		public List<Object> getLabels() {
			return labels;
		}

		/**
		 * Gets the rotation.
		 * 
		 * @return the rotation
		 */
		public Rotation getRotation() {
			return rotate;
		}

		/**
		 * Gets the size.
		 * 
		 * @return the size
		 */
		public Integer getSize() {
			return size;
		}

		/**
		 * Gets the steps.
		 * 
		 * @return the steps
		 */
		public Integer getSteps() {
			return steps;
		}

		/**
		 * Gets the visible.
		 * 
		 * @return the visible
		 */
		public Boolean getVisible() {
			return visible;
		}

		/**
		 * Sets the colour in HTML hex format (#ffffff)
		 * 
		 * @param colour
		 *            the colour
		 */
		public void setColour(String colour) {
			this.colour = colour;
		}

		/**
		 * Sets the rotation.
		 * 
		 * @param rotate
		 *            the rotate
		 */
		public void setRotation(Rotation rotate) {
			this.rotate = rotate;
		}

		/**
		 * Sets the size.
		 * 
		 * @param size
		 *            the size
		 */
		public void setSize(Integer size) {
			this.size = size;
		}

		/**
		 * Sets the steps.
		 * 
		 * @param steps
		 *            the new steps
		 */
		public void setSteps(Integer steps) {
			this.steps = steps;
		}

		/**
		 * Sets the visible.
		 * 
		 * @param visible
		 *            the visible
		 */
		public void setVisible(Boolean visible) {
			this.visible = visible;
		}

		/**
		 * Check labels not null.
		 */
		private synchronized void checkLabelsNotNull() {
			if (labels == null) labels = new ArrayList<Object>();
		}
	}

	private Integer tickHeight;
	private Labels labels;

	/**
	 * Adds the labels.
	 * 
	 * @param labels
	 *            the labels
	 */
	public void addLabels(Label... labels) {
		checkLabelsNotNull();
		this.labels.addLabels(labels);
	}

	/**
	 * Adds the labels.
	 * 
	 * @param labels
	 *            the labels
	 */
	public void addLabels(List<Label> labels) {
		checkLabelsNotNull();
		this.labels.addLabels(labels);
	}

	/**
	 * Adds the labels.
	 * 
	 * @param labels
	 *            the labels
	 */
	public void addLabels(String... labels) {
		checkLabelsNotNull();
		this.labels.addLabels(labels);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.axis.AbstractAxis.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (tickHeight != null) json.put("tick-height", new JSONNumber(tickHeight));
		if (labels != null) json.put("labels", labels.buildJSON());
		return json;
	}

	/**
	 * Gets the labels.
	 * 
	 * @return the labels
	 */
	public Labels getLabels() {
		return labels;
	}

	/**
	 * Gets the tick height.
	 * 
	 * @return the tick height
	 */
	public Integer getTickHeight() {
		return tickHeight;
	}

	/**
	 * Sets the labels.
	 * 
	 * @param labels
	 *            the new labels
	 */
	public void setLabels(List<String> labels) {
		this.labels = new Labels(labels);
	}

	/**
	 * Sets the labels.
	 * 
	 * @param labels
	 *            the new labels
	 */
	public void setLabels(String... labels) {
		this.labels = new Labels(labels);
	}

	/**
	 * Sets the tick height.
	 * 
	 * @param tick_height
	 *            the new tick height
	 */
	public void setTickHeight(Integer tick_height) {
		this.tickHeight = tick_height;
	}

	/**
	 * Sets the x axis labels.
	 * 
	 * @param labels
	 *            the new x axis labels
	 */
	public void setXAxisLabels(Labels labels) {
		this.labels = labels;
	}

	/**
	 * Check labels not null.
	 */
	private synchronized void checkLabelsNotNull() {
		if (labels == null) labels = new Labels();
	}
}
