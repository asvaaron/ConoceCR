package com.example.walter.conocecr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Autores_Activity extends AppCompatActivity {

    private List<Autores> misObjetos = new ArrayList<Autores>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autores);
        LlenarListaObjetos();
        LlenarListView();
        //RegistrarClicks();
    }


    private void LlenarListaObjetos() {
        misObjetos.add(new Autores("Walter Araya", "A1-02", R.drawable.walter));
        misObjetos.add(new Autores("Jean Abarca", "116070969", R.drawable.jean));
        misObjetos.add(new Autores("Aarón Sibaja", "402230919", R.drawable.aaron));

    }

    private void LlenarListView() {
        ArrayAdapter<Autores> adapter = new MyListAdapter();
        ListView list = (ListView) findViewById(R.id.list_view_autores);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Autores> {
        public MyListAdapter() {
            super(Autores_Activity.this, R.layout.desplegar_objetos, misObjetos);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Make sure we have a view to work with (may have been given null)
            View itemView = convertView;
            if (itemView == null) {
                itemView = getLayoutInflater().inflate(R.layout.desplegar_objetos, parent, false);
            }
            Autores ObjetoActual = misObjetos.get(position);
            // Fill the view
            ImageView imageView = (ImageView) itemView.findViewById(R.id.ivdibujo);
            imageView.setImageResource(ObjetoActual.getId_imag());
            TextView elatributo01 = (TextView) itemView.findViewById(R.id.paraelatributo01);
            elatributo01.setText(ObjetoActual.getAtributo01());
            TextView elatributo02 = (TextView) itemView.findViewById(R.id.paraelatributo02);
            elatributo02.setText("" + ObjetoActual.getAtributo02());
            return itemView;
        }

    }
}
