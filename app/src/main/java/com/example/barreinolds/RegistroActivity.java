package com.example.barreinolds;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiresApi(24)
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

        final Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bclick);

        buttonAceptarR = findViewById(R.id.buttonAceptRegister);
        buttonAceptarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vb.vibrate(70);
                mp.start();
                // Comprobamos que los campos de usuario y contraseña no estan vacios
                if (nameEmployee.getText().toString().isEmpty() && passwordEmployee.getText().toString().isEmpty() && usuarioEmployee.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Los campos usuario y contraseña estan vacios", Toast.LENGTH_LONG).show();
                } else if (nameEmployee.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo nombre esta vacio", Toast.LENGTH_LONG).show();
                } else if (usuarioEmployee.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "El campo usuario esta vacio", Toast.LENGTH_LONG).show();
                } else if (passwordEmployee.getText().toString().isEmpty() || passwordEmployee.getText().length() < 6) {
                    Toast.makeText(getApplicationContext(), "La contraseña esta vacia o es demasiado corta", Toast.LENGTH_LONG).show();
                } else {
                    employee.setNombre(nameEmployee.getText().toString());
                    employee.setUsername(usuarioEmployee.getText().toString());
                    employee.setPassword(passwordEmployee.getText().toString());
                    nameEmployee.setText("");
                    usuarioEmployee.setText("");
                    passwordEmployee.setText("");
                    dispatchTakePictureIntent();
                }

            }
        });

        buttonCancelarR = findViewById(R.id.buttonCancelRegister);
        buttonCancelarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vb.vibrate(70);
                mp.start();
                nameEmployee.setText("");
                usuarioEmployee.setText("");
                passwordEmployee.setText("");
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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            InputStream is = compressBitmap(imageBitmap);
            saveToInternalStorage(imageBitmap);
            try {
                employee.setImageEmployee(getByteFromInput(is));
            } catch (IOException e) {
                e.printStackTrace();
            }
            sendEmployee();
            Intent intent = new Intent(getApplicationContext(), Empleados.class);
            startActivity(intent);
            //prueba.setImageBitmap(employee.getImageEmployee());
        }
    }

    // Pasamos el bipmap a byte para el InputStream a la hora de enviar al servidor
    private InputStream compressBitmap(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bmpData = baos.toByteArray();
        InputStream is = new ByteArrayInputStream(bmpData);

        return is;
    }

    // Este metodo llama al metodo que envia el objeto camarero al servidor
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

    public static byte[] getByteFromInput(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        return baos.toByteArray();
    }

    // Este metodo almacena la foto en la memoria interna del telefono.
    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        // Create imageDir
        File mypath=new File(directory,employee.getNombre() + employee.getUsername() + ".JPG");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}

//new Camarero(null, employee.getNombre(), employee.getUsername(), employee.getPassword(), employee.getImageEmployee()
