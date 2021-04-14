package com.proyectoupc.scrapp.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proyectoupc.scrapp.R;

import java.util.List;

public class SorteosAdapter extends RecyclerView.Adapter<SorteosAdapter.MyViewHolder> {

    private List<Sorteos> SorteosList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fechaHoraSorteo, nombreSorteo, descripcionSorteo;

        public MyViewHolder(View view) {
            super(view);
            fechaHoraSorteo = (TextView) view.findViewById(R.id.fechaHoraSorteo);
            nombreSorteo = (TextView) view.findViewById(R.id.nombreSorteo);
            descripcionSorteo = (TextView) view.findViewById(R.id.descripcionSorteo);
        }
    }


    public SorteosAdapter(List<Sorteos> SorteosList) {
        this.SorteosList = SorteosList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sorteos_fila, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Sorteos Sorteo = SorteosList.get(position);
        holder.fechaHoraSorteo.setText(Sorteo.getfechaHoraSorteo());
        holder.nombreSorteo.setText(Sorteo.getnombreSorteo());
        holder.descripcionSorteo.setText(Sorteo.getdescripcionSorteo());
    }

    @Override
    public int getItemCount() {
        return SorteosList.size();
    }
}
