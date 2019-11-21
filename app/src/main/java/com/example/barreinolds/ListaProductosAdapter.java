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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView lProductosImageView;
        public TextView lProductosName;
        public TextView lProductosPrice;
        public Button lProductosPlus1;
        public Button lProductosMinus1;
        public TextView lProductosQuantity;
        public Context c;

        public ViewHolder(View itemView) {
            super(itemView);

            lProductosImageView = itemView.findViewById(R.id.imagen_producto);
            lProductosName = itemView.findViewById(R.id.nombre_producto);
            lProductosPrice = itemView.findViewById(R.id.precio_producto);
            lProductosPlus1 = itemView.findViewById(R.id.boton_suma);
            lProductosMinus1 = itemView.findViewById(R.id.boton_resta);
            lProductosQuantity = itemView.findViewById(R.id.cantidad_producto);
        }
    }

    static ArrayList<Product> lProductsAdapterArray;
    Pedido p;

    public ListaProductosAdapter(ArrayList<Product> lProductsAdapterArray){
        this.lProductsAdapterArray = lProductsAdapterArray;
    }

    @NonNull
    @Override
    public ListaProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View lProductosView = inflater.inflate(R.layout.customlayout_productos, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(lProductosView);
        viewHolder.c = context;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = lProductsAdapterArray.get(position);
        final Product pLPForOnClick = product;
        final Context context = holder.c;

        ImageView itemImageView = holder.lProductosImageView;

        String uri = product.getImage_movil();
        int imageResource = holder.c.getResources().getIdentifier(uri, "drawable", holder.c.getPackageName());
        Drawable imagenDra = ContextCompat.getDrawable(holder.c, imageResource);

        itemImageView.setImageDrawable(imagenDra);

        TextView itemName = holder.lProductosName;
        itemName.setText(product.getName());

        TextView itemPrice = holder.lProductosPrice;
        itemPrice.setText(product.getPrice());

        final TextView itemQuantity = holder.lProductosQuantity;

        Ticket t = Search.getTicket(Mesas.numMesa);
        final Ticket tForOnClick = t;
        Product pTicket = Search.compareProducts(product, t.getProductosComanda());
        final Product pTicketForOnClick = pTicket;
        if(pTicket != null){
            itemQuantity.setText(String.valueOf(pTicket.getCantidad()));
        }else{
            itemQuantity.setText("0");
        }

        Button itemPlus1 = holder.lProductosPlus1;
        itemPlus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = Search.compareProducts(pLPForOnClick, tForOnClick.getProductosComanda());
                if(p == null)
                    p = new Product(pLPForOnClick.getId(), pLPForOnClick.getName(), pLPForOnClick.getDescription(), pLPForOnClick.getPrice(), 0, null, pLPForOnClick.getImage_movil());
                p.setCantidad(p.getCantidad() + 1);
                itemQuantity.setText(String.valueOf(p.getCantidad()));
                if (p.getCantidad() == 1) {
                    tForOnClick.getProductosComanda().add(p);
                }

//                        if (ticket != null) {
//                            new EnviarTicket().execute(ticket);
//                        }
               // p = new Pedido();
                //p.crearXML(getApplicationContext(), ticket.getProductosComanda());

            }
        });

        Button itemMinus1 = holder.lProductosMinus1;
        itemMinus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!itemQuantity.getText().equals("0")) {
                    pTicketForOnClick.setCantidad(pTicketForOnClick.getCantidad() - 1);
                    itemQuantity.setText(String.valueOf(pTicketForOnClick.getCantidad()));
                    if (pTicketForOnClick.getCantidad() <= 0) {
                        tForOnClick.getProductosComanda().remove(pTicketForOnClick);
                    }
                }
                p = new Pedido();
                p.crearXML(context, tForOnClick.getProductosComanda());
            }
        });



    }

    @Override
    public int getItemCount() {
        return lProductsAdapterArray.size();
    }






}
