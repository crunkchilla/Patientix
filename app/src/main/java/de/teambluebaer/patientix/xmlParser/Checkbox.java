package de.teambluebaer.patientix.xmlParser;

import android.content.Context;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.teambluebaer.patientix.R;
import de.teambluebaer.patientix.helper.Constants;
import de.teambluebaer.patientix.helper.TextSize;

/**
 * Created by Simon on 06.05.2015.
 * <p/>
 * Represents a Checkbox that could insert into a <code>Row</code>
 *
 * @see Row
 * @see Element
 */
public class Checkbox extends Commentar implements Element{
    private String checkboxText;
    private boolean checked;
    private boolean highlight;


    /**
     * Constructor
     *
     * @param checkboxText represents the showen Answer to this Checkbox
     */
    public Checkbox(String checkboxText, String checked, String patientCommentar, String mtraCommentar,
                    String doctorCommentar, String highlight) {
        this.checkboxText = checkboxText;
        this.checked = (checked.equals("1")|checked.equals("true"));

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
            this.highlight = (highlight.equals("1")|highlight.equals("true"));
        } else {
            this.highlight = false;
        }

    }

    @Override
    public void addToView(Context context, final LinearLayout layout) {
        CheckBox checkBox = new CheckBox(context);
        checkBox.setText(checkboxText);
        checkBox.setButtonDrawable(R.drawable.radio);
        checkBox.setChecked(checked);
        layout.addView(checkBox);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;
            }
        });

        if(Constants.resign){
            checkBox.setClickable(false);
        }

        if(Constants.zoomed){
            checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize.SUBTITEL.zoomedSize);

        }else{
            checkBox.setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize.SUBTITEL.normalSize);

        }
    }


    public void showAllComments(Context context, LinearLayout layout) {
        if(patientCommentar != null && !patientCommentar.isEmpty()){
            TextView patCom = new TextView(context);
            patCom.setText(this.patientCommentar);
            layout.addView(patCom);
        }else if(mtraCommentar != null && !mtraCommentar.isEmpty()){
            TextView mtraCom = new TextView(context);
            mtraCom.setText(this.mtraCommentar);
            layout.addView(mtraCom);
        }else if(doctorCommentar != null && !doctorCommentar.isEmpty()){
            TextView docCom = new TextView(context);
            docCom.setText(this.doctorCommentar);
            layout.addView(docCom);
        }
    }

    public String toXMLString(){
        String xmlString = new String();
        xmlString = xmlString + "<checkbox ";
        xmlString = xmlString + "text=\"" + this.checkboxText + "\" ";
        xmlString = xmlString + "checked=\"" + this.checked + "\" ";
        xmlString = xmlString + "comment\"" + this.patientCommentar +"\" ";
        xmlString = xmlString + "mtraComment\"" + this.mtraCommentar + "\" ";
        xmlString = xmlString + "docComment\"" + this.doctorCommentar + "\" ";
        xmlString = xmlString + "highlight=\"" + this.highlight + "\" ";
        xmlString = xmlString + "/>";

        return xmlString;
    }

    public String getCheckboxText() {
        return checkboxText;
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

        Checkbox checkbox = (Checkbox) o;

        if (checked != checkbox.checked) return false;
        if (checkboxText != null ? !checkboxText.equals(checkbox.checkboxText) : checkbox.checkboxText != null)
            return false;
        if (patientCommentar != null ? !patientCommentar.equals(checkbox.patientCommentar) : checkbox.patientCommentar != null)
            return false;
        if (mtraCommentar != null ? !mtraCommentar.equals(checkbox.mtraCommentar) : checkbox.mtraCommentar != null)
            return false;
        return !(doctorCommentar != null ? !doctorCommentar.equals(checkbox.doctorCommentar) : checkbox.doctorCommentar != null);

    }

    @Override
    public int hashCode() {
        int result = checkboxText != null ? checkboxText.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (patientCommentar != null ? patientCommentar.hashCode() : 0);
        result = 31 * result + (mtraCommentar != null ? mtraCommentar.hashCode() : 0);
        result = 31 * result + (doctorCommentar != null ? doctorCommentar.hashCode() : 0);
        return result;
    }
}