package de.teambluebaer.patientix.xmlParser;

import android.content.Context;
import android.util.TypedValue;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import de.teambluebaer.patientix.R;
import de.teambluebaer.patientix.helper.Constants;
import de.teambluebaer.patientix.helper.TextSize;

/**
 * Created by Simon on 06.05.2015.
 * <p/>
 * Represents a radiobutton that could insert into a <code>Row</code>
 *
 * @see Row
 * @see Element
 */
public class Radio extends Commentar implements Element {
    private String radioText;
    private boolean checked;
    private boolean highlight;


    /**
     * constructor
     *
     * @param radioText represents the showen Answer to this Radio Button
     */
    public Radio(String radioText, String checked, String patientCommentar, String mtraCommentar,
                 String doctorCommentar, String highlight) {

        if(checked != null && !checked.isEmpty()){
            this.checked = checked.equals("1");
        } else {
            this.checked = false;
        }
        if(radioText != null && !radioText.isEmpty()){
            this.radioText = radioText;
        } else {
            this.radioText = "";
        }
        if(patientCommentar != null && !patientCommentar.isEmpty()){
            this.patientCommentar = patientCommentar;
        } else {
            this.patientCommentar = "";
        }
        if(mtraCommentar != null && !mtraCommentar.isEmpty()){
            this.mtraCommentar = mtraCommentar;
        } else {

            this.mtraCommentar = "";
        }
        if(doctorCommentar != null && !doctorCommentar.isEmpty()){
            this.doctorCommentar = doctorCommentar;
        } else {
            this.doctorCommentar = "";
        }
        if(highlight != null && !highlight.isEmpty()){
            this.highlight = (highlight.equals("1"));
        } else {
            this.highlight = false;
        }
    }

    @Override
    public void addToView(Context context, LinearLayout layout) {
        RadioButton radio = new RadioButton(context);
        radio.setText(radioText);
        radio.setChecked(checked);
        layout.addView(radio);
    }

    public void addToView(Context context, RadioGroup radiogroup) {
        RadioButton radio = new RadioButton(context);
        radio.setText(radioText);
        radio.setButtonDrawable(R.drawable.radio);
        radiogroup.addView(radio);
        radio.setChecked(checked);
        radio.setMinWidth(300);
        radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;
            }
        });

        if(Constants.resign){
            radio.setClickable(false);
        }

        if(Constants.zoomed){
            radio.setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize.SUBTITEL.zoomedSize);

        }else{
            radio.setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize.SUBTITEL.normalSize);
        }
    }


    public String toXMLString() {
        String xmlString = new String();
        xmlString = xmlString + "<radiobutton ";
        xmlString = xmlString + "text=\"" + this.radioText + "\" ";
        xmlString = xmlString + "checked=\"" + this.checked + "\" ";
        xmlString = xmlString + "comment=\"" + this.patientCommentar + "\" ";
        xmlString = xmlString + "mtraComment=\"" + this.mtraCommentar + "\" ";
        xmlString = xmlString + "docComment=\"" + this.doctorCommentar + "\" ";
        xmlString = xmlString + "highlight=\"" + this.highlight + "\" ";
        xmlString = xmlString + "/>";

        return xmlString;
    }

    public String getRadioText() {
        return radioText;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getPatientCommentar() {
        return patientCommentar;
    }

    public String getMtraCommentar() {
        return mtraCommentar;
    }

    public String getDoctorCommentar() {
        return doctorCommentar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Radio radio = (Radio) o;

        if (checked != radio.checked) return false;
        if (radioText != null ? !radioText.equals(radio.radioText) : radio.radioText != null)
            return false;
        if (patientCommentar != null ? !patientCommentar.equals(radio.patientCommentar) : radio.patientCommentar != null)
            return false;
        if (mtraCommentar != null ? !mtraCommentar.equals(radio.mtraCommentar) : radio.mtraCommentar != null)
            return false;
        return !(doctorCommentar != null ? !doctorCommentar.equals(radio.doctorCommentar) : radio.doctorCommentar != null);

    }

    @Override
    public int hashCode() {
        int result = radioText != null ? radioText.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (patientCommentar != null ? patientCommentar.hashCode() : 0);
        result = 31 * result + (mtraCommentar != null ? mtraCommentar.hashCode() : 0);
        result = 31 * result + (doctorCommentar != null ? doctorCommentar.hashCode() : 0);
        return result;
    }
}