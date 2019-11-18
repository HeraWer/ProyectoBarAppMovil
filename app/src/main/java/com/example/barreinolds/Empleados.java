package com.example.barreinolds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class Empleados extends AppCompatActivity {

    static String empleado;
    static ArrayList<String> listaEmpleados;
    EmpleadosAdapter adapter;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

        listaEmpleados = new ArrayList<String>();

        listaEmpleados.add("David");
        listaEmpleados.add("David");
        listaEmpleados.add("David");
        listaEmpleados.add("David");
        listaEmpleados.add("David");

        // Declaracion del adapter para la ListView
        /*ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listaEmpleados);*/

        listView = findViewById(R.id.lista_empleados);
        adapter = new EmpleadosAdapter(listaEmpleados);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(this));



        /*listView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //view.setBackgroundColor(Color.parseColor("#A3A921"));
                Intent intent = new Intent(Empleados.this, Mesas.class);
                empleado = listaEmpleados.get(position);
                startActivity(intent);
            }
        });*/
    }

    /*class CustomAdapter extends ArrayAdapter<String> {

        public CustomAdapter(@NonNull Context context, ArrayList<String> resource ){
            super(context, 0, resource);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.customlayout_empleados, parent, false);


            }
            return convertView;
        }
    }*/
}
