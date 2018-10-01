package david.com.ageproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DropDetector extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager = null;
    private Sensor accelerometerSensor = null;
    private String phoneNumber;
    private EditText num;
    private Button call;


    @Override
    protected void onCreate(Bundle savedInstance) {
        try {
            super.onCreate(savedInstance);
            setContentView(R.layout.activity_drop_detector);
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
           sensorManager.registerListener(this, accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);

        } catch (Exception e) {
            Log.d("TAG", e.getMessage());
        }

        num = (EditText)findViewById(R.id.phoneNumberText);
        phoneNumber = num.getText().toString();
        call = (Button)findViewById(R.id.callButton);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(DropDetector.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel" + phoneNumber));
                    startActivity(callIntent);
                } else {
                    return;
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() != Sensor.TYPE_ACCELEROMETER) {
            return;
        }
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        Log.d("String", "X:  " + "Y:  " + "Z  " );
        /*

        Not working as of now. Accelerometer not engaging.
        Need to create a noise value based on X, Y, Z.
        If threshhold is broken, make a phone call.

         */
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}