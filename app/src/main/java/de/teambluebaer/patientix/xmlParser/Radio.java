package de.teambluebaer.patientix.xmlParser;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Simon on 06.05.2015.
 *
 * Represents a radiobutton that could insert into a <code>Row</code>
 * @see Row
 * @see Element
 */
public class Radio implements Element {
    private String radioText;
    private boolean checked;
    private RadioGroup radiogroup;
    private String patientCommentar;
    private String mtraCommentar;
    private String doctorCommentar;

    /**
     * changes the state of the boolean checked
     */
    void check(){
        checked = !checked;
    }

    /**
     * constructor
     * @param radioText represents the showen Answer to this Radio Button
     */
    public Radio(String radioText){
        this.radioText = radioText;
        this.checked = false;

    }

    @Override
    public void addToView(Context context, LinearLayout layout) {
        RadioButton radio = new RadioButton(context);
        radio.setChecked(checked);
        radiogroup.addView(radio);
        layout.addView(radio);
    }

    public void addToView(Context context, LinearLayout layout,RadioGroup radiogroup) {
        RadioButton radio = new RadioButton(context);
        radio.setChecked(checked);
        this.radiogroup = radiogroup;
        radiogroup.addView(radio);
        layout.addView(radio);
    }

    /**
     * Insert a patient comment field to this Radiobutton
     */
    public void addPatCommant(){

    }
    /**
     * Insert a MTRA comment field to this Radiobutton
     */
    public void addMTRACommant(){

    }
    /**
     * Insert a Doctor comment field to this Radiobutton
     */
    public void addDocCommant(){

    }


}
