package de.teambluebaer.patientix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.patientix.maren.patientix.R;

import de.teambluebaer.patientix.xmlParser.Form;
import de.teambluebaer.patientix.xmlParser.JavaStrucBuilder;
import de.teambluebaer.patientix.xmlParser.MetaData;
import de.teambluebaer.patientix.xmlParser.MetaandForm;


public class StartActivity extends Activity {

    private Button buttonStart;
    private Button buttonUpdate;


    /**
     * This method defines what happens when you press on the hardkey back on the Tablet.
     * In this case the funktionality of the button is disabled.
     */
    @Override
    public void onBackPressed() {

    }

    /**
     * In this method is defined what happens on create of the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        buttonStart = (Button) findViewById(R.id.startbtn);
        buttonUpdate = (Button) findViewById(R.id.updatebtn);

        //Titlebar removed
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);



        //Press buttons method -> Start-Button
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashButtonsmall(buttonStart);
                Intent intentFormActivity = new Intent(StartActivity.this, FormActivity.class);
                startActivity(intentFormActivity);
                finish();
            }
        });

        // Update-Button
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                flashButtonlarge(buttonUpdate);
                //TODO SEND "OLD" DATA TO SERVER


                Form.getInstance().refresh();
                MetaData.getInstance().refresh();

                MetaandForm metaandform = JavaStrucBuilder.buildStruc();

                Toast.makeText(StartActivity.this, "Das Formular ist bereit", Toast.LENGTH_SHORT).show();

            }
        });
    }


    // Change the view from the button to press
    public void flashButtonsmall(final Button myBtnToFlash) {
        myBtnToFlash.setBackgroundResource(R.drawable.button1x3aktiv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                myBtnToFlash.setBackgroundResource(R.drawable.button1x3normal);
            }
        }, 10);
    }

    public void flashButtonlarge(final Button myBtnToFlash) {
        myBtnToFlash.setBackgroundResource(R.drawable.button1x5aktiv);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                myBtnToFlash.setBackgroundResource(R.drawable.button1x5normal);
            }
        }, 10);

    }
}
