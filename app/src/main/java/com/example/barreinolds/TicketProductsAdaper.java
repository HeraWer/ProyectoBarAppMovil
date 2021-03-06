package com.example.barreinolds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.core.content.ContextCompat;

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
        public LinearLayout layoutTicket;
        public Context c;

        public ViewHolder(View itemView) {

            super(itemView);

            productImageTicket = itemView.findViewById(R.id.productImageTicket);
            productNameTicket = itemView.findViewById(R.id.productNameTicket);
            productQuantityTicket = itemView.findViewById(R.id.productQuantityTicket);
            productPriceTicket = itemView.findViewById(R.id.productPriceTicket);
            productTotalTicket = itemView.findViewById(R.id.productTotalTicket);
            deleteProduct = itemView.findViewById(R.id.productDeleteTicket);
            layoutTicket = itemView.findViewById(R.id.linLayout);
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

        if(product.isServed())
            holder.layoutTicket.setBackground(holder.c.getDrawable(R.drawable.served_products_background));
        else
            holder.layoutTicket.setBackground(holder.c.getDrawable(R.drawable.not_served_products_background));

        // Set item views based on your views and data model
        ImageView imageImageView = holder.productImageTicket;
        Bitmap prodPhoto = BitmapFactory.decodeByteArray(product.getImgBlob(), 0, product.getImgBlob().length);
//        String uri = product.getImage_movil();
//        int imageResource = holder.c.getResources().getIdentifier(uri, "drawable", holder.c.getPackageName());
//        Drawable imagenDra = ContextCompat.getDrawable(holder.c, imageResource);

        imageImageView.setImageBitmap(prodPhoto);

        TextView nameTextView = holder.productNameTicket;
        nameTextView.setText(product.getName());

        TextView quantityTextView = holder.productQuantityTicket;
        quantityTextView.setText(String.valueOf(product.getCantidad()));

        TextView priceTextView = holder.productPriceTicket;
        priceTextView.setText(product.getPrice());

        TextView totalTextView = holder.productTotalTicket;
        totalTextView.setText(String.format("%.2f", NumberFormat.round(Float.parseFloat(product.getPrice()) * product.getCantidad())));

        final Vibrator vb = (Vibrator) holder.c.getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(holder.c, R.raw.restar);

        ImageButton deleteImageButton = holder.deleteProduct;
        deleteImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vb.vibrate(70);
                        mp.start();
                        productsAdapterArray.remove(holderForOnClick.getAdapterPosition());
                        TicketActivity.calcularTotal();
                        TicketProductsAdaper.this.notifyItemRemoved(holderForOnClick.getAdapterPosition());
                        TicketActivity.sendTicket();
                //new EnviarTicket().execute(Search.getTicket(Mesas.numMesa));
            }
        });




    }

    @Override
    public int getItemCount() {
        return productsAdapterArray.size();
    }
}
