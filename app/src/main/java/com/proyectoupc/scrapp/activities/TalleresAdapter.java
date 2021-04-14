package com.proyectoupc.scrapp.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.proyectoupc.scrapp.R;

import java.util.List;

public class TalleresAdapter extends RecyclerView.Adapter<TalleresAdapter.MyViewHolder> {

    private List<Talleres> TalleresList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fechaHoraTaller, nombreTaller, descripcionTaller;

        public MyViewHolder(View view) {
            super(view);
            fechaHoraTaller = (TextView) view.findViewById(R.id.fechaHoraTaller);
            nombreTaller = (TextView) view.findViewById(R.id.nombreTaller);
            descripcionTaller = (TextView) view.findViewById(R.id.descripcionTaller);
        }
    }


    public TalleresAdapter(List<Talleres> TalleresList) {
        this.TalleresList = TalleresList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.talleres_fila, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Talleres Taller = TalleresList.get(position);
        holder.fechaHoraTaller.setText(Taller.getfechaHoraTaller());
        holder.nombreTaller.setText(Taller.getnombreTaller());
        holder.descripcionTaller.setText(Taller.getdescripcionTaller());
    }

    @Override
    public int getItemCount() {
        return TalleresList.size();
    }
}