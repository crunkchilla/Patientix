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
 * Copyright 2015 By Authors
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * Authors:
 * Simon Sauerzapf
 * Maren Dietrich
 * Chris Harsch
 *
 * Represents a radiobutton that could insert into a <code>Row</code>
 *
 * @see Row
 * @see Element
 */
public class Radio implements Element,Editable {
    private String radioText;
    private boolean checked;
    private boolean highlight;
    private int counter;


    /**
     * constructor
     *
     * @param radioText represents the showen Answer to this Radio Button
     */
    public Radio(String radioText, String checked, String highlight) {

        if(checked != null && !checked.isEmpty()){
            this.checked = (checked.equals("1")|checked.equals("true"));
        } else {
            this.checked = false;
        }
        if(radioText != null && !radioText.isEmpty()){
            this.radioText = radioText;
        } else {
            this.radioText = "";
        }
        if(highlight != null && !highlight.isEmpty()){
            this.highlight = (highlight.equals("1")|highlight.equals("true"));
        } else {
            this.highlight = false;
        }

        if(radioText.length()<=6){
            counter = 3;
        }else if(6 < radioText.length() & radioText.length() <= 10){
            counter = 5;
        }else{
            counter = 10;
        }
    }

    @Override
    public void addToView(Context context, LinearLayout layout) {
        RadioButton radio = new RadioButton(context);
        radio.setText(radioText);
        radio.setChecked(checked);
        layout.addView(radio);
    }

    /**
     * Costem addToView Methode that should be Used to add Radiobutton to Views
     * @param context Context of the Activite the Radiobutton should be added
     * @param radiogroup could contains severel Radiobutton
     */
    public void addToView(Context context, RadioGroup radiogroup) {
        RadioButton radio = new RadioButton(context);
        radio.setText(radioText);
        radio.setButtonDrawable(R.drawable.radio);
        radiogroup.addView(radio);
        radio.setChecked(checked);


        radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                checked = isChecked;
            }
        });

        if(Constants.RESIGN){
            radio.setClickable(false);
        }

        if(Constants.ZOOMED){
            radio.setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize.SUBTITEL.zoomedSize);

        }else{
            radio.setTextSize(TypedValue.COMPLEX_UNIT_PX, TextSize.SUBTITEL.normalSize);
        }
    }


    public String toXMLString() {
        String xmlString = new String();
        xmlString = xmlString + "<radiobutton ";
        xmlString = xmlString + "text=\"" + this.radioText + "\" ";
        if(checked){
            xmlString = xmlString + "checked=\"" + 1 + "\" ";
        }else {
            xmlString = xmlString + "checked=\"" + 0 + "\" ";
        }
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

    public boolean isHighlight() {
        return highlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Radio radio = (Radio) o;

        if (checked != radio.checked) return false;
        if (highlight != radio.highlight) return false;
        return !(radioText != null ? !radioText.equals(radio.radioText) : radio.radioText != null);

    }

    @Override
    public int hashCode() {
        int result = radioText != null ? radioText.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (highlight ? 1 : 0);
        return result;
    }

    @Override
    public int getCounter() {
        return counter;
    }
}