package com.example.barreinolds;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static com.example.barreinolds.Mesas.listaMesas;
import static com.example.barreinolds.Mesas.numMesa;
import static com.example.barreinolds.Mesas.tickets;

@TargetApi(23)
public class MesasAdapter extends RecyclerView.Adapter<MesasAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tableName;
        public Context c;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tableName = itemView.findViewById(R.id.nombre_mesa);
        }
    }

    static ArrayList<String> mesasArrayList ;

    public MesasAdapter(ArrayList<String> mesa){
        this.mesasArrayList = mesa;
    }

    @NonNull
    @Override
    public MesasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View mesaView = inflater.inflate(R.layout.customlayout__mesas, parent, false);

        ViewHolder viewHolder = new ViewHolder(mesaView);
        viewHolder.c = context;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MesasAdapter.ViewHolder holder, final int position) {

        final String mesas = mesasArrayList.get(position);




        TextView nameTextView = holder.tableName;
        nameTextView.setText(mesas);

        if(Search.getTicket(Integer.parseInt(mesas)) != null) {
            nameTextView.setBackgroundColor(holder.c.getColor(R.color.busyTable));
        }else{
            nameTextView.setBackgroundColor(holder.c.getColor(R.color.actionBar));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListaCategorias.class);
                numMesa = Integer.parseInt(mesas);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mesasArrayList.size();
    }


}
