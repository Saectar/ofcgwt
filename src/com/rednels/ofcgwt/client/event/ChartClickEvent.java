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
package com.rednels.ofcgwt.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class ChartClickEvent extends GwtEvent<ChartClickHandler> {

	private static final Type<ChartClickHandler> TYPE = new Type<ChartClickHandler>();

	/**
	 * Gets the event type associated with click events.
	 * 
	 * @return the handler type
	 */
	public static Type<ChartClickHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<ChartClickHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(ChartClickHandler handler) {
		handler.onClick(this);
	}
}
