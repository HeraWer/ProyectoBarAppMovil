package com.example.barreinolds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import static android.view.View.*;
import static com.example.barreinolds.Empleados.listaEmpleados;

public class EmpleadosAdapter extends RecyclerView.Adapter<EmpleadosAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linLayoutEmpleados;
        public TextView employeeName;
        public Context c;

        public ViewHolder(View itemView) {
            super(itemView);
            linLayoutEmpleados = itemView.findViewById(R.id.linLayoutEmpleados);
            employeeName = itemView.findViewById(R.id.nombre_empleados);
        }
    }

    static ArrayList<Camarero> empleadoAdapterArray;

    public EmpleadosAdapter(ArrayList<Camarero> employee){
        this.empleadoAdapterArray = employee;
    }

    @NonNull
    @Override
    public EmpleadosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View employeeView = inflater.inflate(R.layout.customlayout_empleados, parent, false);

        ViewHolder viewHolder = new ViewHolder(employeeView);
        viewHolder.c = context;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final ViewHolder holderForOnClick = holder;
        Camarero empleados = empleadoAdapterArray.get(position);

        TextView nameTextView = holder.employeeName;
        nameTextView.setText(empleados.getNombre());

        LinearLayout linLayoutVH = holder.linLayoutEmpleados;
        holder.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //holderForOnClick.linLayoutEmpleados.setBackgroundColor(Color.parseColor("#A3A921"));
                Intent intent = new Intent(v.getContext(), Mesas.class);
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
