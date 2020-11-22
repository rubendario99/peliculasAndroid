package com.example.ejercicio6;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdaptadorLista extends RecyclerView.Adapter<AdaptadorLista.MyViewHolder> implements View.OnClickListener {

    ArrayList<Pelicula> peliculas; // puede ser cualquier estructura de datos
    private int selectedPos = RecyclerView.NO_POSITION;
    private int positionClick;

    public int getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(int selectedPos) {

        if (selectedPos == this.selectedPos) { // Si pulso sobre el elemento marcado
            notifyItemChanged(this.selectedPos); // Se avisa para que desmarque esa posición
            this.selectedPos = RecyclerView.NO_POSITION;
        } else { // El elemento pulsado no está marcado
            if (this.selectedPos >= 0)
                notifyItemChanged(this.selectedPos); // Si hay otra posición marcada se desmarca
            this.selectedPos = selectedPos;
            notifyItemChanged(this.selectedPos); // Se marca la nueva posición
        }
    }

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) { // el nombre del método es indiferente
        this.listener = listener;
    }

    // Constructor
    public AdaptadorLista(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) listener.onClick(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Elementos que queremos mostrar en el RecyclerView, normalmente se corresponderán
        // con los definidos en el layout
        private TextView titulo;
        private TextView director;
        private TextView fecha;
        private TextView duracion;
        private TextView sala;
        private ImageView portada;
        private ImageView clasi;
        private ImageView favorita;

        // Constructor: asocia cada atributo de la clase con su correspondiente en el layout definido en ViewElemento
        public MyViewHolder(View viewElemento) {
            super(viewElemento);
            this.titulo = viewElemento.findViewById(R.id.textViewTituloLista);
            this.director = viewElemento.findViewById(R.id.textViewDirectorLista);
            this.fecha = viewElemento.findViewById(R.id.textViewFechaLista);
            this.duracion = viewElemento.findViewById(R.id.textViewDuracionLista);
            this.sala = viewElemento.findViewById(R.id.textViewSalaLista);
            this.portada = viewElemento.findViewById(R.id.imageViewPortadaLista);
            this.clasi = viewElemento.findViewById(R.id.imageViewClasiLista);
            this.favorita = viewElemento.findViewById(R.id.imageViewFavoritaListado);
        }
    }

    // Crea nuevos elementos expandiendo el layout definido en el fichero R.layout.elemento_individual
    // que usamos para crear el Holder que devolveremos
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_individual_listado, parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento); // return new MyViewHolder(elemento);
        elemento.setOnClickListener(this);
        return mvh;
    }

    // Establece al objeto holder los valores de la colección de datos que están en la posicion position.
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pelicula pelicula = this.peliculas.get(position);
        positionClick = position;

        holder.titulo.setText(pelicula.getTitulo());
        holder.director.setText(pelicula.getDirector());

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

//        String date = simpleDateFormat.format(pelicula.getFecha());
        //       holder.fecha.setText(pelicula.getFecha().toString());

        System.out.println(pelicula.getFecha());
        System.out.println(pelicula.getDuracion());

        holder.duracion.setText(pelicula.getDuracion() + " minutos");
        holder.sala.setText(pelicula.getSala());
        holder.portada.setImageResource(pelicula.getPortada());
        holder.clasi.setImageResource(pelicula.getClasi());

        if (pelicula.getFavorita()) {
            holder.favorita.setColorFilter(Color.argb(255, 255, 0, 0));
        } else {
            holder.favorita.setColorFilter(Color.argb(255, 255, 255, 255));
        }
    }

    // Número de elementos en la colección de datos. Será el número de elemento que se mostrara en el RecycleView
    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}