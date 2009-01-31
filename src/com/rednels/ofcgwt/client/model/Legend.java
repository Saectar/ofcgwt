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
package com.rednels.ofcgwt.client.model;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;

/**
 * Base class for an OFC text element
 */
public class Legend implements JSONizable {
	private Position position;
	private boolean border;
	private Integer stroke;
	private boolean shadow;
	private String borderColour;
	private String bgColour;
	private Float alpha;
	private Integer margin;
	private Integer padding;

	/**
	 * Create a new Legend instance with the given position and border.
	 * 
	 * @param pos
	 *            Position
	 * @param border
	 *            boolean
	 */
	public Legend(Position pos, boolean border) {
		setPosition(pos);
		setBorder(border);
	}

	/**
	 * Get the position
	 * 
	 * @return Position pos
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * 
	 * @param pos
	 *            Position
	 */
	public void setPosition(Position pos) {
		this.position = pos;
	}

	/**
	 * Get the border 
	 * 
	 * @return true if border is enabled
	 */
	public boolean getBorder() {
		return border;
	}

	/**
	 * Sets the border, whether or not to draw a line around the side legend.
	 * 
	 * @param border
	 *            boolean
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = new JSONObject();
		json.put("visible", JSONBoolean.getInstance(true));
		json.put("bg_colour",  new JSONString("#fefefe"));
		if (position != null)
			json.put("position", new JSONString(position.getString()));
		json.put("border", JSONBoolean.getInstance(border));
		return json;
	}

	/**
	 * Enumeration Position - used with legend.
	 */
	public static enum Position {

		/** CLOSEST */
		TOP("top"),

		/** FOLLOW */
		RIGHT("right");

		/** The pos. */
		private String pos;

		/**
		 * Creates a new bar style.
		 * 
		 * @param pos
		 *            the pos
		 */
		Position(String pos) {
			this.pos = pos;
		}

		/**
		 * Gets the pos.
		 * 
		 * @return the pos
		 */
		public String getString() {
			return pos;
		}
	}
}