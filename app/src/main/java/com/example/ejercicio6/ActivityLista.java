package com.example.ejercicio6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    ArrayList<Pelicula> peliculas;
    RecyclerView rv;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        setTitle("Listado");
        peliculas = new ArrayList<>();
        peliculas = MainActivity.rellenaPeliculas();
        intent = getIntent();

        if (intent != null) {
            System.out.println("fecha antes: " + peliculas.get(1).getFecha());
            peliculas = intent.getParcelableArrayListExtra("PELIS");
            System.out.println("fecha después: " + peliculas.get(1).getFecha());
        }

        AdaptadorLista adaptadorLista = new AdaptadorLista(peliculas);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = rv.getChildAdapterPosition(view);
                adaptadorLista.setSelectedPos(pos);
                if (adaptadorLista.getSelectedPos() >= 0) { // Si marco una pasición
                    adaptadorLista.setSelectedPos(-1);
                    Intent intent = new Intent(ActivityLista.this, ActivitySinopsis.class);
                    intent.putExtra("Pelicula", peliculas.get(pos).getTitulo());
                    intent.putExtra("Caratula", peliculas.get(pos).getPortada());
                    intent.putExtra("Sinopsis", peliculas.get(pos).getSinopsis());
                    intent.putExtra("Youtube", peliculas.get(pos).getIdYoutube());
                    startActivity(intent);
                }
            }
        };

        adaptadorLista.setOnClickListener(listener); // Se establece el escuchado del RecycleView
        rv = findViewById(R.id.recyclerViewLista);

        RecyclerView.LayoutManager miLayoutManager;
        miLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(miLayoutManager);
        rv.setAdapter(adaptadorLista);

        intent = new Intent();
        intent.putExtra("PELIS", peliculas);
        setResult(RESULT_OK, intent);
    }
}