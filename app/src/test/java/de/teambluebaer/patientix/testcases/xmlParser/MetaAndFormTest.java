package de.teambluebaer.patientix.testcases.xmlParser;

import junit.framework.TestCase;

import de.teambluebaer.patientix.xmlParser.Form;
import de.teambluebaer.patientix.xmlParser.MetaData;
import de.teambluebaer.patientix.xmlParser.MetaAndForm;

/**
 * Created by Simon on 29.05.2015.
 */
public class MetaAndFormTest extends TestCase {

    MetaAndForm metaAndForm;
    Form form;
    MetaData meta;

    @Override
    protected void setUp() throws Exception {
        metaAndForm = new MetaAndForm();
        meta = new MetaData();
        form = new Form();
        metaAndForm.setForm(form);
        metaAndForm.setMeta(meta);
        metaAndForm.setSignature("DAS IST EINE TEST SIGNATUR");
    }


    public void testToXMLString() throws Exception {
        String inputXML = metaAndForm.toXMLString();
        String testXML = "<root><meta><pID></pID><pExamID></pExamID><pFirstName></pFirstName><pLastName>" +
                "</pLastName><pDate>Unbekannt</pDate><name></name></meta><form></form><sign image=\"DAS IST EINE TEST SIGNATUR\" /></root>";
        assertEquals(testXML,inputXML);
    }
}
