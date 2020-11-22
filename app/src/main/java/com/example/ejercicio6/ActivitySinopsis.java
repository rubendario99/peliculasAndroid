package com.example.ejercicio6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.SQLOutput;

public class ActivitySinopsis extends AppCompatActivity {

    ImageView imageViewPortadaSinopsis;
    TextView textViewSinopsis;
    String linkYoutube;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinopsis);

        imageViewPortadaSinopsis = findViewById(R.id.imageViewCaratulaSinopsis);
        textViewSinopsis = findViewById(R.id.textViewSinopsis);

        Intent intent = getIntent();
        setTitle(intent.getStringExtra("Pelicula"));
        textViewSinopsis.setText(intent.getStringExtra("Sinopsis"));
        //Hacemos el textview scrolleable para leer el texto entero
        textViewSinopsis.setMovementMethod(new ScrollingMovementMethod());
        id=intent.getIntExtra("Caratula",0);
        imageViewPortadaSinopsis.setImageResource(id);
        linkYoutube = "https://www.youtube.com/watch?v="+intent.getStringExtra("Youtube");
    }

    public void abrirYoutube(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkYoutube));
        startActivity(intent);
    }

}