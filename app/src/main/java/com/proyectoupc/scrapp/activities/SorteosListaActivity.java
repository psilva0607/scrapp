package com.proyectoupc.scrapp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.proyectoupc.scrapp.R;
import java.util.ArrayList;
import java.util.List;

public class SorteosListaActivity extends AppCompatActivity {

    private List<Sorteos> SorteosList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SorteosAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorteos_lista);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new SorteosAdapter(SorteosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setAdapter(mAdapter);

        prepareSorteosData();
    }

    private void prepareSorteosData() {
        Sorteos Sorteo = new Sorteos("2021-04-21 22:00","OVEJA NEGRA","Sorteo Juegos de Sellos Amor");
        SorteosList.add(Sorteo);

        Sorteo = new Sorteos("2021-04-29 21:00","BECKA ALLISON","Premio 2 Troqueladoras mini");
        SorteosList.add(Sorteo);

        Sorteo = new Sorteos("2021-04-30 20:00","SRA VIDALINA","Premio Juego Cartulinas");
        SorteosList.add(Sorteo);

        mAdapter.notifyDataSetChanged();
    }

}