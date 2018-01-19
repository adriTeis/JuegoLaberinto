package com.example.adrianmontes.juegolaberinto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import java.sql.SQLOutput;

/**
 * Created by adrian.montes on 18/01/2018.
 */

public class Bola {
    private int x,y; //posicion de la bola
    int velx,vely;  //velocidad actual de la bola
    Bitmap bola;
    Context ctx;
    private final int velocidadMinima = -100 ;
    private final int velocidadMaxima = 100;
    int width;
    int height;

    public Bola(Context ctx) {
        this.ctx = ctx;
        bola =BitmapFactory.decodeResource(ctx.getResources(),R.raw.ball);
        x=y=100;

    }



    public void Draw(Canvas canvas){
        //ahora solo pintamos la bola en la pantalla
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bola,x,y,null);




    }

    public void DatosDecomunicacion(float[] data) {


        //son las nuevas posiciones que va a tener X y Y;
        int newx = x;
        int newy = y;

        //obtengo el tamaño de la pantalla
        Point tamañoPantalla = new Point();
        Display d = ((WindowManager) ctx.getSystemService(ctx.WINDOW_SERVICE)).getDefaultDisplay();
        d.getSize(tamañoPantalla);

        height = tamañoPantalla.x - bola.getHeight();
        width = tamañoPantalla.y - bola.getWidth();

        //Nuevas velocidades
        if (d.getRotation() == Surface.ROTATION_90 || d.getRotation() == Surface.ROTATION_270) {
            //ejes rotados, los datos se leen al reves
            velx += -data[1];
            vely += data[0];
        } else {
            velx += -data[0];
            vely += data[1];
        }
        //limitar la velocidad
        velx = velx > velocidadMaxima ? velocidadMaxima : velx;
        velx = velx < velocidadMinima ? velocidadMinima : velx;
        vely = vely > velocidadMaxima ? velocidadMaxima : vely;
        vely = vely < velocidadMinima ? velocidadMinima : vely;

        //actualizamos la posicion
        newx += velx;
        newy += vely;

        //comprobamos los limites de la pantalla en x
        if (newx > height) {

            velx = -velx; //rebota
            x = height;
        } else if (newx < 0) {

            velx = -velx;   //rebota
            x = 0;
        } else {

            x = newx;
        }

        //comprobamos los limites de la pantalla en y
        if (newy > width) {

            vely = -vely; //rebota
            y = width;
        } else if (newy < 0) {

            vely = -vely;   //rebota
            y = 0;
        } else {

            y = newy;
        }


    }



}

