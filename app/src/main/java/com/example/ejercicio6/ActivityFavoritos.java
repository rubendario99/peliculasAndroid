package com.example.ejercicio6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityFavoritos extends AppCompatActivity {

    ArrayList<Pelicula> peliculas;
    final ArrayList<String> lista = new ArrayList<String>(); // Fuente de datos
    ArrayAdapter<String> adapter; // Adaptador para el ListView
    Intent intent;
    Intent intent2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        setTitle("Favoritas");
        peliculas = MainActivity.rellenaPeliculas();
        ListView lv = findViewById(R.id.listViewFavoritos);

        intent = getIntent();

        if (intent != null) {
            peliculas = intent.getParcelableArrayListExtra("PELIS");
        }

        for (int i = 0; i < peliculas.size(); i++) {
            lista.add(peliculas.get(i).getTitulo());
        }
        // Se define el adaptador usando el contexto, el tipo de listView y los datos.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, lista);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(adapter); // se asigna el adaptador al ListView


        for (int i = 0; i < peliculas.size(); i++) {
            if (peliculas.get(i).getFavorita()) {
                lv.setItemChecked(i, true);
            }
        }

        // Se gestiona la pulsación de un elemento del ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posición, long id) {

                if (peliculas.get(posición).getFavorita()) {
                    peliculas.get(posición).setFavorita(false);
                } else {
                    peliculas.get(posición).setFavorita(true);
                }
            }
        });
        intent2 = new Intent();
        intent2.putExtra("PELIS", peliculas);
        setResult(RESULT_OK, intent);
    }
}