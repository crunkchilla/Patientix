package de.teambluebaer.patientix.helper;

import java.io.File;

import de.teambluebaer.patientix.xmlParser.MetaandForm;

/**
 *This class is used to represent the columns for the overview
 * activity. There are just 2 columns, but you can add more columns
 * if you want. If you want to add more columns you also have to
 * prepare the code of OverviewActivity.java and activity_overview.xml
 */
public class Constants {

    public static final String FIRST_COLUMN = "First";
    public static final String SECOND_COLUMN = "Second";
    public static final String TABLET_ID= "3";
    public static MetaandForm globalMetaandForm;
    public static File formFile = new File("form.xml");


}
