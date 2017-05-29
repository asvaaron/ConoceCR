package com.example.walter.conocecr;

import android.widget.TextView;

/**
 * Created by Jean Abarca on 21-May-17.
 */

public class Puntaje {
     TextView resultado;
     String urlconexion = "http://localhost/CCR_ws/guardarPuntaje.php";
     ObtenerWebService hiloconexion = new ObtenerWebService(urlconexion);


    // otro ejemplo:  http://www.elpais.com/rss/feed.html?feedId=1022
    TextView textView;
    //ObtenerWebService hiloconexion;


    public void metodoMandatRequest(){

        this.hiloconexion.execute();

    //hiloconexion = new ObtenerWebService;

    //hiloconexion.execute("haha","hoho");
        //
        //
        }

}
