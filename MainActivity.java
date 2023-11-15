package com.froilanhuar.listaapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(MainActivity.this, viendoLista.class);
                //startActivity(intent);

                // Call the myviewlist() program
                //myviewlist();
                //VerLista(View view);

               // new DatabaseHelper(this);
                startActivity(new Intent(MainActivity.this, viendoLista.class));


            }
        });
    }





}