package com.proyectoupc.scrapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.proyectoupc.scrapp.R;
import com.proyectoupc.scrapp.helpers.ConnectionSQLiteHelper;
import com.proyectoupc.scrapp.utils.Common;

public class MainActivity extends AppCompatActivity {
    TextView txtHiUser;
    CardView card_workshop;     //PANTALLA DE TALLERES
    CardView card_riffle;       //PANTALLA DE SORTEOS
    CardView card_catalogue;    //PANTALLA DE CATALOGO
    CardView card_cart;         //PANTALLA DE CARRITO
    CardView card_checkOrder;   //PANTALLA DE CONSULTAR PEDIDOS
    //SQLite
    ConnectionSQLiteHelper conn;

    //Firebase
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_workshop   = findViewById(R.id.card_workshop);
        card_riffle     = findViewById(R.id.card_riffle);
        card_catalogue  = findViewById(R.id.card_catalogue);
        card_cart       = findViewById(R.id.card_cart);
        card_checkOrder = findViewById(R.id.card_checkOrder);

        txtHiUser = findViewById(R.id.txt_hiUser);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        loadUserData();

        //EVENTO DE INVOCAR LA PANTALLA DE TALLERES
        card_workshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Bienvenidos Talleres", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), TalleresListaActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        //EVENTO DE INVOCAR LA PANTALLA DE SORTEOS
        card_riffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Bienvenidos a los SORTEOS", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent (v.getContext(), SorteosListaActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        //EVENTO DE INVOCAR LA PANTALLA DE CATALOGOS
        card_catalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Bienvenidos a CATALOGOS", Toast.LENGTH_SHORT).show();
            }
        });
        //EVENTO DE INVOCAR LA PANTALLA DE CARRITO
        card_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Bienvenidos a CARRITO DE COMPRAS", Toast.LENGTH_SHORT).show();
            }
        });
        //EVENTO DE INVOCAR LA PANTALLA DE CONSULTA PEDIDOS
        card_checkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Bienvenidos a  CONSULTAR PEDIDOS", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadUserData() {
        conn = new ConnectionSQLiteHelper(getApplicationContext(), Common.DB_NAME, null, 1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] userId = {mUser.getUid()};
        String[] params = {Common.U_NAME};

        try {
            Cursor cursor = db.query(Common.USER_TABLE, params,
                    Common.U_ID + "=?", userId,
                    null, null, null);
            cursor.moveToFirst();
            String name = cursor.getString(0);
            txtHiUser.setText(getString(R.string.text_hi, name));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}