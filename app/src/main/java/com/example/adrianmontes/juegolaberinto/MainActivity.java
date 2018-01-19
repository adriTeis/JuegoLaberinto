package com.example.adrianmontes.juegolaberinto;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

//para acceder a los sensores implementamos sensorEventListener
public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView texto;
    GameView gameview;
    SensorManager sensorManager;
    private Sensor acelerometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameview=new GameView(this);
        setContentView(gameview);
        texto=(TextView) findViewById(R.id.texto);
        //VOY A MOSTRAR LOS SENSORES DEL MOVIL en un campo de texto
//        List<Sensor> miLista=SensorManager.getSensorList(Sensor.TYPE_ALL);
//        for (int i=1; i <miLista.size();i++){
//            texto.append("\n"+miLista.get(i).getName()+"\n"+miLista.get(i).getVendor()+"\n"+miLista.get(i).getVersion()+"\n");
//
//        }


        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


    }

    //cada vez que aya un cambio de en el sensor recivimos los cambios
    @Override
    public void onSensorChanged(SensorEvent event) {
        gameview.comunicacionDelMovimiento(event.values.clone());


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(this, acelerometro,sensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
