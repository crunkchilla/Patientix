package de.teambluebaer.patientix.xmlParser;

/**
 * Created by Simon on 07.05.2015.
 *
 * represents a <code>MetaData</code> and <code>Form</code> combination
 * @see MetaData
 * @see Form
 *
 */
public class MetaandForm {

    private MetaData meta;
    private Form form;

    /**
     * Constructor
     */
    public MetaandForm() {

    }

    /**
     * set MetaData of a <code>MetaAnfForm</code> Object
     * @param meta <code>MetaData</code> you want to add
     */
    protected void setMeta(MetaData meta){
        this.meta = meta;
    }

    /**
     * set Form of a <code>MetaAnfForm</code> Object
     * @param form <code>Form</code> you want to add
     */
    protected void setForm(Form form){
        this.form = form;
    }

    /**
     * give you access to the <code>Form</code> Object of <code>MetaAnfForm</code>
     * @return the Form of MetaandForm
     */
    public Form getForm(){
        return this.form;
    }

    /**
     * give you access to the <code>MetaData</code> Object of <code>MetaAnfForm</code>
     * @return the MetaData of MetaandForm
     */
    public MetaData getMeta(){
        return this.meta;
    }

    public String toXMLString(){
        String xmlString = new String();
        xmlString = xmlString + "<root>";
        xmlString = xmlString + this.meta.toXMLString();
        xmlString = xmlString + this.form.toXMLString();
        xmlString = xmlString + "</root>";

        return xmlString;
    }
}