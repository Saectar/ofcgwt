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
package com.rednels.ofcgwt.client.model.elements;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC sketch bar chart (drawn)
 */
public class SketchBarChart extends FilledBarChart implements JSONizable {

	/**
	 * OFC sketch bar chart bars
	 */
	public static class SketchBar extends FilledBarChart.Bar implements JSONizable {

		private Integer offset;

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 */
		public SketchBar(Number top) {
			super(top);
		}

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 * @param offset
		 *            the offset
		 */
		public SketchBar(Number top, Integer offset) {
			super(top);
			setOffset(offset);
		}

		/**
		 * Creates a new bar.
		 * 
		 * @param top
		 *            the top
		 * @param bottom
		 *            the bottom
		 * @param offset
		 *            the offset
		 */
		public SketchBar(Number top, Number bottom, Integer offset) {
			super(top, bottom);
			setOffset(offset);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.rednels.ofcgwt.client.model.elements.FilledBarChart.Bar.buildJSON
		 * ()
		 */
		public JSONValue buildJSON() {
			JSONObject json = (JSONObject) super.buildJSON();
			if (offset != null) json.put("offset", new JSONNumber(offset.doubleValue()));
			return json;
		}

		/**
		 * Gets the offset.
		 * 
		 * @return the offset
		 */
		public Integer getOffset() {
			return offset;
		}

		/**
		 * Sets the offset (the fun factor).
		 * 
		 * @param offset
		 *            the new offset
		 */
		public void setOffset(Integer offset) {
			this.offset = offset;
		}
	}

	private Integer offset;

	/**
	 * Creates a new sketch bar chart.
	 */
	public SketchBarChart() {
		this(null, null, null);
	}

	/**
	 * Creates a new sketch bar chart.
	 * 
	 * @param colour
	 *            the colour
	 * @param outlineColour
	 *            the outline colour
	 * @param offset
	 *            the offset
	 */
	public SketchBarChart(String colour, String outlineColour, Integer offset) {
		super("bar_sketch");
		setColour(colour);
		setOutlineColour(outlineColour);
		setOffset(offset);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.elements.FilledBarChart.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = (JSONObject) super.buildJSON();
		if (offset != null) json.put("offset", new JSONNumber(offset.doubleValue()));
		return json;
	}

	/**
	 * Gets the offset.
	 * 
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * Sets the offset (the fun factor).
	 * 
	 * @param offset
	 *            the new offset
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
}