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

public class TalleresListaActivity extends AppCompatActivity {

    private List<Talleres> TalleresList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TalleresAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talleres_lista);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TalleresAdapter(TalleresList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setAdapter(mAdapter);

        prepareTalleresData();
    }

    private void prepareTalleresData() {
        Talleres Taller = new Talleres("2021-04-21 18:00","Taller Oveja Negra","Manualidades de Agenda por el dia de la Madre");
        TalleresList.add(Taller);

        Taller = new Talleres("2021-04-22 19:00","Taller Becka Allison","Manualidades crear proyecto Flores");
        TalleresList.add(Taller);


        mAdapter.notifyDataSetChanged();
    }

}