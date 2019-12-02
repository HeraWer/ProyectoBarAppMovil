package com.example.barreinolds;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class RegistroActivity extends AppCompatActivity {

    private Button buttonCancelarR, buttonAceptarR;
    private EditText nameEmployee, passwordEmployee, usuarioEmployee;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    //private ImageView prueba;
    static Camarero employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nameEmployee = findViewById(R.id.nameEmployee);
        usuarioEmployee = findViewById(R.id.userNameEmployee);
        passwordEmployee = findViewById(R.id.contraseñaEmployee);
        //prueba = findViewById(R.id.pruebaView);
        employee = new Camarero();

        buttonAceptarR = findViewById(R.id.buttonAceptRegister);
        buttonAceptarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Comprobamos que los campos de usuario y contraseña no estan vacios
                if(nameEmployee.getText().toString().isEmpty() && passwordEmployee.getText().toString().isEmpty() && usuarioEmployee.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Los campos usuario y contraseña estan vacios", Toast.LENGTH_LONG).show();
                }else if(nameEmployee.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo nombre esta vacio", Toast.LENGTH_LONG).show();
                }else if(usuarioEmployee.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El campo usuario esta vacio", Toast.LENGTH_LONG).show();
                }else if(passwordEmployee.getText().toString().isEmpty() || passwordEmployee.getText().length() > 6){
                    Toast.makeText(getApplicationContext(), "La contraseña esta vacia o es demasiado corta", Toast.LENGTH_LONG).show();
                }else{
                    employee.setNombre(nameEmployee.getText().toString());
                    employee.setUsername(usuarioEmployee.getText().toString());
                    employee.setPassword(passwordEmployee.getText().toString());
                    dispatchTakePictureIntent();
                }

            }
        });

        buttonCancelarR = findViewById(R.id.buttonCancelRegister);
        buttonCancelarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Empleados.class);
                startActivity(intent);
            }
        });

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            employee.setImageEmployee(imageBitmap);
            sendEmployee();
            Intent intent = new Intent(getApplicationContext(), Empleados.class);
            startActivity(intent);
            //prueba.setImageBitmap(employee.getImageEmployee());
        }
    }

    public static void sendEmployee(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Creamos el objeto que nos conecta al server
                ConnectionClass connection = null;
                try {
                    // Lo inicializamos
                    connection = new ConnectionClass();
                    connection.sendEmployee(RegistroActivity.employee);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

//new Camarero(null, employee.getNombre(), employee.getUsername(), employee.getPassword(), employee.getImageEmployee()
