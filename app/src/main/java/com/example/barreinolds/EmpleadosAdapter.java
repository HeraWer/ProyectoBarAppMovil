package com.example.barreinolds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.view.View.*;
import static com.example.barreinolds.Empleados.listaEmpleados;

/*
Esta es la clase que hace como Adapter de la RecyclerView
que se visualiza en el Activity Empleados
 */
public class EmpleadosAdapter extends RecyclerView.Adapter<EmpleadosAdapter.ViewHolder> {

    /*
    Necesitamos crear una clase ViewHolder para que el adapter funcione.
    Esta se encarga de recoger los datos de la Activity Empleados
    para hacerlos funcionar en el Adapter
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /*
        Objetos visuales del layout del adapter
         */
        public LinearLayout linLayoutEmpleados;
        public TextView employeeName;
        public ImageView employeeImg;

        // Contexto de la activity (Empleados)
        public Context c;

        /*
        Constructor donde se inicializan los objetos visuales del
        layout y se les encuentra por el id
         */
        public ViewHolder(View itemView) {
            super(itemView);
            linLayoutEmpleados = itemView.findViewById(R.id.linLayoutEmpleados);
            employeeName = itemView.findViewById(R.id.nombre_empleados);
            employeeImg = itemView.findViewById(R.id.imgEmployeeAdapter);
        }
    }

    // Atributo del adapter. El arrayList de donde sacará los elementos a mostrar
    static ArrayList<Camarero> empleadoAdapterArray;

    /*
    Constructor donde se iguala el ArrayList del adapter al que se le pase
    por parametro en la clase Empleados
     */
    public EmpleadosAdapter(ArrayList<Camarero> employee) {
        this.empleadoAdapterArray = employee;
    }

    /*
    Metodo onCreateViewHolder donde 'inflamos' el layout del adapter.
    Este layout es el que utilizara cada elemento del ArrayList
     */
    public EmpleadosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View employeeView = inflater.inflate(R.layout.customlayout_empleados, parent, false);

        ViewHolder viewHolder = new ViewHolder(employeeView);

        // Aquí le asignamos el contexto de la aplicación al objeto Context publico del ViewHolder
        viewHolder.c = context;
        return viewHolder;
    }

    /*
    Metodo onBindViewHolder, que conecta cada elemento con la viewHolder y ejecuta
    y configura como se mostrarán en la activity dentro del RecyclerView
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        // Cogemos el Camarero de cada elemento para mostrar sus datos
        Camarero empleados = empleadoAdapterArray.get(position);
        final Vibrator vb = (Vibrator) holder.c.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(holder.c, R.raw.bclick);
        // Seteamos el texto del TextView al nombre del empleado
        TextView nameTextView = holder.employeeName;
        nameTextView.setText(empleados.getNombre());
        ImageView imgEmployee = holder.employeeImg;
        byte[] img = empleados.getImageEmployee();
        if(img != null) {
            Bitmap prodPhoto = BitmapFactory.decodeByteArray(empleados.getImageEmployee(), 0, img.length);
            imgEmployee.setImageBitmap(prodPhoto);
        }else{
            int imageResource = holder.c.getResources().getIdentifier("imgalhambra", "drawable", holder.c.getPackageName());
            Drawable imagenDra = ContextCompat.getDrawable(holder.c, imageResource);
            imgEmployee.setImageDrawable(imagenDra);
        }

        // Le añadimos un onClickListener a cada elemento de la lista
        holder.itemView.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // En el onClick le decimos que cambie al Intent de mesas al clickar un empleado
                Intent intent = new Intent(v.getContext(), Mesas.class);
                vb.vibrate(70);
                mp.start();
                Empleados.camarero = listaEmpleados.get(position);
                v.getContext().startActivity(intent);
            }

        });

    }


    @Override
    public int getItemCount() {
        return empleadoAdapterArray.size();
    }
}
