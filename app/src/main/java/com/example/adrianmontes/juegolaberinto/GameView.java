package com.example.adrianmontes.juegolaberinto;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by adrian.montes on 18/01/2018.
 */

public class GameView extends SurfaceView{
//lo primero es crear la clase donde se cargara la vista del juego que extiende de SurfaceView

    private SurfaceHolder holder;
    Bola bola;

    public GameView(Context context) {
        super(context);

        //cada vez que haya un cambio en el cambas va al metodo surface change que es un metodo de la clase holder
        //estos metodos son el metodo canvas que pinta las imagenes en la pantalla
        holder=getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                bola=new Bola(getContext());
                Canvas c = holder.lockCanvas(null);
                onDraw(c);
                holder.unlockCanvasAndPost(c);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {



            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }


    //aqui pintamos la bola
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         bola.Draw(canvas);
    }

    //este es el metodo que cada vez que el acelerometro tiene un cambio nos llama a la clase gameView
    public void comunicacionDelMovimiento(float[] data) {

        bola.DatosDecomunicacion(data);
        Canvas c = holder.lockCanvas(null);
        onDraw(c);
        holder.unlockCanvasAndPost(c);






    }
}
