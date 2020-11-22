package com.example.ejercicio6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MiAdaptador extends RecyclerView.Adapter<MiAdaptador.MyViewHolder> implements View.OnClickListener {

    ArrayList<Pelicula> peliculas; // puede ser cualquier estructura de datos
    int selectedPos=RecyclerView.NO_POSITION;

    public int getSelectedPos() {
        return selectedPos;
    }
    public void setSelectedPos(int selectedPos) {
        if (selectedPos==this.selectedPos){ // Si pulso sobre el elemento marcado
            notifyItemChanged(this.selectedPos); // Se avisa para que desmarque esa posición
            this.selectedPos=RecyclerView.NO_POSITION;
        } else { // El elemento pulsado no está marcado
            if (this.selectedPos >=0 ) notifyItemChanged(this.selectedPos); // Si hay otra posición marcada se desmarca
            this.selectedPos = selectedPos;
            notifyItemChanged(this.selectedPos); // Se marca la nueva posición
        }
    }

    // Constructor
    public MiAdaptador(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    //Creamos atributo de instancia para contener el listener
    private View.OnClickListener listener;

    //Setter para el listener
    public void setOnClickListener(View.OnClickListener listener) { // el nombre del método es indiferente
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null) listener.onClick(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // Elementos que queremos mostrar en el RecyclerView, normalmente se corresponderán
        // con los definidos en el layout
        private TextView titulo;
        private TextView director;
        ImageView portada;
        ImageView clasi;

        // Constructor: asocia cada atributo de la clase con su correspondiente en el layout definido en ViewElemento
        public MyViewHolder(View viewElemento) {
            super(viewElemento);
            this.titulo = viewElemento.findViewById(R.id.textViewTitulo);
            this.director = viewElemento.findViewById(R.id.textViewDirector);
            this.portada = viewElemento.findViewById(R.id.imageViewPortada);
            this.clasi = viewElemento.findViewById(R.id.imageViewClasi);

        }
    }

    // Crea nuevos elementos expandiendo el layout definido en el fichero R.layout.elemento_individual
    // que usamos para crear el Holder que devolveremos
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elemento = LayoutInflater.from(parent.getContext()).inflate(R.layout.pelicula_individual, parent, false);
        MyViewHolder mvh = new MyViewHolder(elemento); // return new MyViewHolder(elemento);
        elemento.setOnClickListener(this);

        return mvh;
    }

    // Establece al objeto holder los valores de la colección de datos que están en la posicion position.
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pelicula pelicula = this.peliculas.get(position);
        holder.titulo.setText(pelicula.getTitulo());
        holder.director.setText(pelicula.getDirector());
        holder.portada.setImageResource(pelicula.getPortada());
        holder.clasi.setImageResource(pelicula.getClasi());


 //       if (selectedPos == position) holder.itemView.setBackgroundResource(R.color.seleccionado);
//        else holder.itemView.setBackgroundResource(R.color.elemento);
    }

    @Override
    public int getItemCount() {
        return this.peliculas.size();
    }
}
