package com.example.barreinolds;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TicketProductsAdaper extends RecyclerView.Adapter<TicketProductsAdaper.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView productImageTicket;
        public TextView productNameTicket;
        public TextView productQuantityTicket;
        public TextView productPriceTicket;
        public TextView productTotalTicket;
        public Context c;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            productImageTicket = itemView.findViewById(R.id.productImageTicket);
            productNameTicket = itemView.findViewById(R.id.productNameTicket);
            productQuantityTicket = itemView.findViewById(R.id.productQuantityTicket);
            productPriceTicket = itemView.findViewById(R.id.productPriceTicket);
            productTotalTicket = itemView.findViewById(R.id.productTotalTicket);
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

        // Set item views based on your views and data model
        ImageView imageImageView = holder.productImageTicket;
        String uri = product.getImage();
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

    }

    @Override
    public int getItemCount() {

        return productsAdapterArray.size();
    }

}
