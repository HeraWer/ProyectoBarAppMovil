package com.example.barreinolds;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TicketProductsAdaper extends RecyclerView.Adapter<TicketProductsAdaper.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView productImageTicket;
        public TextView productNameTicket;
        public TextView productQuantityTicket;
        public TextView productPriceTicket;
        public TextView productTotalTicket;
        public ImageButton deleteProduct;
        public Context c;

        public ViewHolder(View itemView) {

            super(itemView);

            productImageTicket = itemView.findViewById(R.id.productImageTicket);
            productNameTicket = itemView.findViewById(R.id.productNameTicket);
            productQuantityTicket = itemView.findViewById(R.id.productQuantityTicket);
            productPriceTicket = itemView.findViewById(R.id.productPriceTicket);
            productTotalTicket = itemView.findViewById(R.id.productTotalTicket);
            deleteProduct = itemView.findViewById(R.id.productDeleteTicket);
        }
    }

    static ArrayList<Product> productsAdapterArray;

    public TicketProductsAdaper(ArrayList<Product> products) {
        this.productsAdapterArray = products;
    }

    @NonNull
    @Override
    public TicketProductsAdaper.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View productTicketView = inflater.inflate(R.layout.custom_recyclerview_ticket, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(productTicketView);
        viewHolder.c = context;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productsAdapterArray.get(position);
        final ViewHolder holderForOnClick = holder;

        // Set item views based on your views and data model
        ImageView imageImageView = holder.productImageTicket;
        String uri = product.getImage_movil();
        int imageResource = holder.c.getResources().getIdentifier(uri, "drawable", holder.c.getPackageName());
        Drawable imagenDra = ContextCompat.getDrawable(holder.c, imageResource);

        imageImageView.setImageDrawable(imagenDra);

        TextView nameTextView = holder.productNameTicket;
        nameTextView.setText(product.getName());

        TextView quantityTextView = holder.productQuantityTicket;
        quantityTextView.setText(String.valueOf(product.getCantidad()));

        TextView priceTextView = holder.productPriceTicket;
        priceTextView.setText(product.getPrice());

        TextView totalTextView = holder.productTotalTicket;
        totalTextView.setText(String.valueOf(Float.parseFloat(product.getPrice()) * product.getCantidad()));

        ImageButton deleteImageButton = holder.deleteProduct;
        deleteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsAdapterArray.remove(holderForOnClick.getAdapterPosition());
                TicketProductsAdaper.this.notifyItemRemoved(holderForOnClick.getAdapterPosition());
                new EnviarTicket().execute(Search.getTicket(Mesas.numMesa));
            }
        });


    }

    @Override
    public int getItemCount() {
        return productsAdapterArray.size();
    }
}
