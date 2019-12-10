package com.example.barreinolds;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.barreinolds.Mesas.numMesa;

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

        final Vibrator vb = (Vibrator) holder.c.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(holder.c, R.raw.bclick);

        TextView nameTextView = holder.tableName;
        nameTextView.setText(mesas);

        if(Search.getTicket(Integer.parseInt(mesas)) != null) {
            nameTextView.setBackground(holder.c.getDrawable(R.drawable.served_product_bg));
        }else{
            nameTextView.setBackground(holder.c.getDrawable(R.drawable.borderradiusblue));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(70);
                mp.start();
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
