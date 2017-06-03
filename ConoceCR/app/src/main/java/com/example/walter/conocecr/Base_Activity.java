package com.example.walter.conocecr;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Base_Activity extends AppCompatActivity {
    public static String usr;
    public static String idusr;
    public static int preguntas_seleccion=3; //en este momento hay 5 de cada una
    public static int preguntas_mapa = 2;   //pero pongo la mitad para evitar que en el aleatorio se repitan
    public static int puntaje_porPregunta=20; // [100/(preguntas_seleccion+preguntas_mapa)]
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Mensaje("Primero");
                break;
            case R.id.item2:

                break;
            case R.id.item_autores:

                Uri uri = Uri.parse("https://github.com/asvaaron/ConoceCR");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                Mensaje("No clasificado");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void Mensaje(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onBackPressed() {

        Intent intento = new Intent(getApplicationContext(), menuPrincipal.class);
        startActivity(intento);
        return;
    }


}
