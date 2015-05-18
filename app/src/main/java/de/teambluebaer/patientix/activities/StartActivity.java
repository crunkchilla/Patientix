package de.teambluebaer.patientix.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import de.teambluebaer.patientix.R;
import de.teambluebaer.patientix.helper.Flasher;
import de.teambluebaer.patientix.helper.RestfulHelper;
import static de.teambluebaer.patientix.helper.Constants.TABLET_ID;

/**
 * This class have the patient data...
 */

public class StartActivity extends Activity {

    private Button buttonStart;
    private Button buttonUpdate;
    private ArrayList<NameValuePair> parameterMap = new ArrayList<NameValuePair>();
    private int responseCode;

    /**
     * In this method is defined what happens on create of the Activity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //removes the titlebar in fullscreenmode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);

        buttonStart = (Button) findViewById(R.id.startbtn);
        buttonUpdate = (Button) findViewById(R.id.updatebutton);

    }

    /**
     * When the start button is clicked, then opens the FormActivity
     *
     * @param v
     */
    public void onClickStartButton(View v) {
        Flasher.flash(buttonStart, "1x3");
        Intent intent = new Intent(StartActivity.this, FormActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * @param v
     */
    public void onClickUpdateButton(View v) {
        Flasher.flash(buttonUpdate, "1x5");


        //TODO SEND "OLD" DATA TO SERVER

       // Form.getInstance().refresh();
        //MetaData.getInstance().refresh();

        //MetaandForm metaandform = JavaStrucBuilder.buildStruc();

        parameterMap.add(new BasicNameValuePair("tabletID", TABLET_ID));

        //send the request to server
        RestfulHelper restfulHelper = new RestfulHelper();
        responseCode = restfulHelper.executeRequest("formula", parameterMap);
        if (200 == responseCode) {
            try {

                File folders = new File (Environment.getExternalStorageDirectory().toString()+"/patientix");
                folders.mkdirs();
                String extStorageDirectory = folders.toString();
              //  deleteFile("form.xml");
                File myFile = new File(extStorageDirectory,"form.xml");
                myFile.createNewFile();
                FileOutputStream fOut = new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter =
                        new OutputStreamWriter(fOut);
                myOutWriter.append(restfulHelper.responseString);
                myOutWriter.close();
                fOut.close();
                Toast.makeText(getBaseContext(), "Fragebogen wurde gespeichert!", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Log.d("FileSaveExeption", e.toString());
                Toast.makeText(getBaseContext(), "Fehler beim Speichern des Fragebogens", Toast.LENGTH_LONG).show();
            }
        } else if(404 == responseCode) {
            Toast.makeText(StartActivity.this, "Keine Daten für dieses Tablet vorhanden", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(StartActivity.this, "Kein Verbindung zum Server! Fehlercode: " + responseCode, Toast.LENGTH_LONG).show();
        }


    }

    /**
     * This method defines what happens when you press on the hardkey back on the Tablet.
     * In this case the functionality of the button is disabled.
     */
    @Override
    public void onBackPressed() {

    }

}
