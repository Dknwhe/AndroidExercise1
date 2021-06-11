package se.ecutb.cai.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.buttonClick);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_LONG).show();
                textView.setText("Next activity");
                nextActivity();
            }
        });

    }

    public void nextActivity() {
        Intent i = new Intent(this,MainActivity2.class);
        startActivity(i);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d("ACC", "onSensorChanged: ");
        // kolla om en Acl finns.
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            double Xval = event.values[0];
            textView.setText(String.valueOf(Xval));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}