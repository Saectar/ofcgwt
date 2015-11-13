### Does this work with GWT X.Y.Z ? ###
OFCGWT has only been tested with GWT 1.5.2 and whilst it should technically work with earlier versions, it has not been tested. If you encounter problems, please let us know.

### Can you add a new feature to the charts? ###
OFCGWT is a wrapper library around [Open Flash Chart v2](http://teethgrinder.co.uk/open-flash-chart-2/) (OFC) and does not actually provide the charting capabilities. In saying that, OFC is an open source project and you are welcome to query the author for new features. Also, if you are aware of an OFC feature we have not made available, please let us know.

### I can't seem to get feature XYZ in OFC v1 working? ###
OFCGWT is based on version 2 of OFC ([Open Flash Chart v2](http://teethgrinder.co.uk/open-flash-chart-2/)). Version 1 is no longer being actively developed, does not have as advanced Javascript interaction (kinda required for GWT) and is built on non-open source tools from Adobe (which makes any specific customisation of OFC kinda limitted).

### Charts don't show when I'm using Netbeans IDE? ###
A few folk faced the same problem, and we have no clue why the Netbeans module does not fully comply with the GWT URL scheme. A trivial workaround is:

In gwt.properties change the line gwt.output.dir to gwt.output.dir=/

This will deploy the GWT files (all of them, including the .html and the ofcgwt.swf files) at the root http://localhost:8080/testapp/ which then means that the .js, .swf and .html files are all located in the same folder and thus all on the same path.

Note that you still need to edit the welcomeGWT.html file to point to the correct GWT .js file, that is: you need to remove the org.test.Main/ part in the script tag.

### What other versions of OFCGWT exist? ###
There's an ExtJS GXT version based on a slightly modified version of OFCGWT that supports ExtGWT -> see here http://code.google.com/p/ext-ux-ofcgxt/

### I have a question not answered here - who can I ask? ###
Please log an issue or join the OFCGWT group and post your question - I will respond as quickly as I can.