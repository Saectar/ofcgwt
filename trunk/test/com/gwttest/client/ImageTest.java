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
package com.gwttest.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.rednels.ofcgwt.client.ChartWidget;
import com.rednels.ofcgwt.client.model.ChartData;
import com.rednels.ofcgwt.client.model.elements.PieChart;

/**
 * Example Test using OFCGWT
 */
public class ImageTest implements EntryPoint {

	String[] panels = { "Home", "Pie", "Bar", "Line", "Scatter",
			"Horizontal Bar", "Area", "Sketch" };

	public void onModuleLoad() {
		SimplePanel main = new SimplePanel();
		main.setHeight("100%");
		main.setWidth("100%");

		DecoratedTabPanel tabPanel = new DecoratedTabPanel();
		tabPanel.setAnimationEnabled(true);

		// add home page
		HTML homeText = new HTML(
				"<h2>Welcome to OFCGWT</h2>"
						+ "<i>....the OpenFlashChart GWT Library</i></br></br>"
						+ "This demonstration site will showcase the many different types of</br>charts that can be inserted into a GWT application.");
		tabPanel.add(homeText, panels[0]);

		// add pie chart
		SimplePanel pieSp = new SimplePanel();
		pieSp.add(addPieChart());
		tabPanel.add(pieSp, panels[1]);

		tabPanel.selectTab(0);
		RootPanel.get().add(tabPanel);
	}

	private ChartWidget addPieChart() {
		ChartWidget chart = new ChartWidget();
		ChartData cd = new ChartData("Sales by Region",
				"font-size: 14px; font-family: Verdana; text-align: center;");
		cd.setBackgroundColour("#ffffff");
		PieChart pie = new PieChart();
		pie.setAlpha(0.3f);
		pie.setNoLabels(true);
		pie.setTooltip("#label# $#val#<br>#percent#");
		pie.setAnimateOnShow(true);
		pie.setGradientFill(true);
		pie.setColours("#ff0000", "#00ff00", "#0000ff", "#ff9900", "#ff00ff");
		pie.addSlices(new PieChart.Slice(11000, "AU"));
		pie.addSlices(new PieChart.Slice(88000, "USA"));
		pie.addSlices(new PieChart.Slice(62000, "UK"));
		pie.addSlices(new PieChart.Slice(14000, "JP"));
		pie.addSlices(new PieChart.Slice(43000, "EU"));

		/*
		 * 
		 * HERE IS THE SAMPLE CODE FOR ONCLICK HANDLER
		 * 
		 * If you see the example from teethgrinder, view source on
		 * "Open in New Window"
		 * http://teethgrinder.co.uk/open-flash-chart-2/save-image-js.php
		 * 
		 * You'll see this
		 * 
		 * <html><head><title>Charts: Export as Image</title></head><body> <img
		 * src='data:image/png;base64,
		 * iVBORw0KGgoAAAANSUhEUgAAAiYAAAD6CAYAAACVrYw7AAA1H0lEQVR42u2dWXMbWZbf9R2me579
		 * Beah3qyxPT1
		 * +8bg322FH9BdQxESMo+0O997V01VTm1QqVZU2ruC+EwA3cCdFcRMpStRCUlxFUhJF
		 * kcTCBQBXSf1wnOeykgVBBJDJXHCB
		 * /D/8okoLD5Q3b+b54S7nnnv9eoEAAAAAAGTgHBoBAAAAABAT ...
		 * 2w2cvmwBUzkQE4iJAxMu2gKJxOx1ExgNwHvK7Hwnc3
		 * /CGhMMveOBNymZyP4tRIYXJM91Q0z0P3uQ
		 * EqxZMvP9DTHBrpysWfyaCUOAsgyV4htu4mkutBHEBLvh0vuOiv2CADEBAAAAAICYAAAAAABiAgAA
		 * AAAAMQEAAABAJvL/AVKygk7bfjZxAAAAAElFTkSuQmCC
		 * 
		 * The image data is encoded into the img url. We can take that data and
		 * get the download prompt from it.
		 */

		ImageServiceAsync imgService = (ImageServiceAsync) GWT
				.create(ImageService.class);
		ServiceDefTarget target = (ServiceDefTarget) imgService;
		target.setServiceEntryPoint("ImageService");

		String imageData = "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";

		// pie.addOnSaveClickListener(String imageData) {
		imgService.getImageToken(imageData, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(String result) {
				Window.Location.assign(GWT.getModuleBaseURL()
						+ "image?var=img_" + result);
			}
		});
		// }

		/*
		 * 
		 * END SAMPLE
		 */

		cd.addElements(pie);
		chart.setSize("300", "300");
		chart.setJsonData(cd.toString());
		return chart;
	}

}