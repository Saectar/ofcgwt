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
package com.rednels.ofcgwt.client.model.elements.dot;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.rednels.ofcgwt.client.event.DataValueEvents;
import com.rednels.ofcgwt.client.model.JSONizable;

/**
 * OFC line dots
 */
public abstract class BaseDot extends DataValueEvents implements JSONizable {

	private Integer haloSize;
	private Integer size;
	private Number value;
	private String colour;
	private String type;
	private String tooltip;
	private Number x;
	private Number y;

	public BaseDot(String type) {
		this.type = type;
	}

	/**
	 * Creates a new dot.
	 * 
	 * @param type
	 *            the type
	 * @param value
	 *            the value
	 */
	public BaseDot(String type, Number value) {
		this(type);
		setValue(value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rednels.ofcgwt.client.model.JSONizable.buildJSON()
	 */
	public JSONValue buildJSON() {
		JSONObject json = new JSONObject();
		if (haloSize != null) json.put("halo-size", new JSONNumber(haloSize.doubleValue()));
		if (size != null) json.put("dot-size", new JSONNumber(size.doubleValue()));
		if (value != null) json.put("value", new JSONNumber(value.doubleValue()));
		if (colour != null) json.put("colour", new JSONString(colour));
		if (type != null) json.put("type", new JSONString(type));
		if (tooltip != null) json.put("tip", new JSONString(tooltip));
		if (x != null) json.put("x", new JSONNumber(x.doubleValue()));
		if (y != null) json.put("y", new JSONNumber(y.doubleValue()));
		if (onClick != null) json.put("on-click", new JSONString(onClick));
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
	 * Gets the halo size.
	 * 
	 * @return the halo size
	 */
	public Integer getHaloSize() {
		return haloSize;
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
	 * Gets the tooltip.
	 * 
	 * @return the tooltip
	 */
	public String getTooltip() {
		return tooltip;
	}

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public Number getValue() {
		return value;
	}

	/**
	 * Gets the x.
	 * 
	 * @return the x
	 */
	public Number getX() {
		return x;
	}

	/**
	 * Gets the y.
	 * 
	 * @return the y
	 */
	public Number getY() {
		return y;
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
	 * Sets the halo size.
	 * 
	 * @param haloSize
	 *            the halo size
	 */
	public void setHaloSize(Integer haloSize) {
		this.haloSize = haloSize;
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
	 * Sets the tooltip.
	 * 
	 * @param tooltip
	 *            the new tooltip
	 */
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the value
	 */
	public void setValue(Number value) {
		this.value = value;
	}

	/**
	 * Sets the x.
	 * 
	 * @param x
	 *            the new x
	 * @param y
	 *            the new y
	 */
	public void setXY(Number x, Number y) {
		this.x = x;
		this.y = y;
	}
}