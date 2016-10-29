package com.example.mariano.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnGuardar, btnBuscar, btnUpdate, btnDelete;
    EditText nameField, emailField, passwordField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuscar = (Button)findViewById(R.id.btnBuscar);
        btnDelete = (Button)findViewById(R.id.btnBorrar);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnUpdate = (Button)findViewById(R.id.btnAtualizar);

        nameField = (EditText) findViewById(R.id.nameField);
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        btnBuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String [] selection = {nameField.getText().toString()};
                String [] projection = {DBHelper.DatosTabla.COLUMN_NAME, DBHelper.DatosTabla.COLUMN_EMAIL};
                Cursor c = db.query(DBHelper.DatosTabla.TABLE_NAME, projection, DBHelper.DatosTabla.COLUMN_NAME+"=?", selection, null, null, null);

                c.moveToFirst();
                nameField.setText(c.getString(0));
                emailField.setText(c.getString(1));

            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues valores = new ContentValues();
                valores.put(DBHelper.DatosTabla.COLUMN_NAME, nameField.getText().toString());
                valores.put(DBHelper.DatosTabla.COLUMN_EMAIL, emailField.getText().toString());
                valores.put(DBHelper.DatosTabla.COLUMN_PASSWORD, passwordField.getText().toString());


                Long savedName = db.insert(DBHelper.DatosTabla.TABLE_NAME, DBHelper.DatosTabla.COLUMN_NAME, valores);

                Toast.makeText(getApplicationContext(), "foi colocado no banco " + savedName, Toast.LENGTH_LONG).show();
            }
        });

    }




}
