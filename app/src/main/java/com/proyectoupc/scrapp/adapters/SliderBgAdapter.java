package com.proyectoupc.scrapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.proyectoupc.scrapp.R;
import com.proyectoupc.scrapp.models.SliderBg;

import java.util.List;

public class SliderBgAdapter extends RecyclerView.Adapter<SliderBgAdapter.SliderBgViewHolder> {

    private List<SliderBg> sliderBgList;
    private ViewPager2 viewPager2;

    public SliderBgAdapter(List<SliderBg> sliderBgList, ViewPager2 viewPager2) {
        this.sliderBgList = sliderBgList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderBgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderBgViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.background_login,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderBgViewHolder holder, int position) {
        holder.setImage(sliderBgList.get(position));
        if (position == sliderBgList.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderBgList.size();
    }

    static class SliderBgViewHolder extends RecyclerView.ViewHolder {
        private ImageView bgImage;

        public SliderBgViewHolder(@NonNull View itemView) {
            super(itemView);
            bgImage = itemView.findViewById(R.id.bgImage);
        }

        void setImage(SliderBg sliderBg) {
            bgImage.setImageResource(sliderBg.getImage());
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            sliderBgList.addAll(sliderBgList);
            notifyDataSetChanged();
        }
    };
}
