package com.example.tangminghui.designpatterndemo.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.databinding.ItemWeatherBinding;
import com.example.tangminghui.designpatterndemo.entity.WeatherMvvm;

import java.util.List;

/**
 * Created by tangminghui on 2017/7/5.
 */

public class WeatherMvvmAdapter extends RecyclerView.Adapter<WeatherMvvmAdapter.WeatherViewHolder> {

    @NonNull
    private List<WeatherMvvm> weatherMvvms;

    public WeatherMvvmAdapter(List<WeatherMvvm> weatherMvvmList){
        this.weatherMvvms = weatherMvvmList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather,parent,false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bind(weatherMvvms.get(position));
    }

    @Override
    public int getItemCount() {
        return weatherMvvms.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        private ItemWeatherBinding binding;

        public WeatherViewHolder(View view){
            super(view);
            binding = DataBindingUtil.bind(view);
        }

        public void bind(@NonNull WeatherMvvm weatherMvvm){
            binding.setWeather(weatherMvvm);
        }

    }
}
