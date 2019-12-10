package com.example.barreinolds;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class ListaCategoriasAdapter extends RecyclerView.Adapter<ListaCategoriasAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView categoriaName;
        public ImageView categoriaPhoto;
        public Context c;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoriaName = itemView.findViewById(R.id.nombre_categoria);
            categoriaPhoto = itemView.findViewById(R.id.imagenCategoriaCustom);
        }
    }

    static ArrayList<Category> categoryArrayAdapter;

    public ListaCategoriasAdapter(ArrayList<Category> category){
        this.categoryArrayAdapter = category;
    }

    @NonNull
    @Override
    public ListaCategoriasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View categoryView = inflater.inflate(R.layout.customlayout_categorias, parent, false);

        ViewHolder viewHolder = new ViewHolder(categoryView);
        viewHolder.c = context;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaCategoriasAdapter.ViewHolder holder, final int position) {
        final Category category = categoryArrayAdapter.get(position);

        final Vibrator vb = (Vibrator) holder.c.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(holder.c, R.raw.bclick);
        //Bitmap prodPhoto = BitmapFactory.decodeByteArray(category.getImgBlob(), 0, category.getImgBlob().length);
        ByteArrayInputStream bais = new ByteArrayInputStream(category.getImgBlob());
        TextView nameTextView = holder.categoriaName;
        ImageView photoImageView = holder.categoriaPhoto;
        nameTextView.setText(category.getnCategory());
        Drawable bg = Drawable.createFromStream(bais, category.getId() + category.getnCategory().toLowerCase());
        photoImageView.setImageDrawable(bg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(70);
                mp.start();
                ListaProductos.productos = category.getListProducts();
                Intent intent = new Intent(v.getContext(), ListaProductos.class);
                intent.putExtra("Categoria", category.getnCategory());
                v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryArrayAdapter.size();
    }
}
