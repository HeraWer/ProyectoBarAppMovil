package com.example.barreinolds;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ListaCategoriasAdapter extends RecyclerView.Adapter<ListaCategoriasAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView categoriaName;
        public Context c;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoriaName = itemView.findViewById(R.id.nombre_categoria);
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

        TextView nameTextView = holder.categoriaName;
        nameTextView.setText(category.getnCategory());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
