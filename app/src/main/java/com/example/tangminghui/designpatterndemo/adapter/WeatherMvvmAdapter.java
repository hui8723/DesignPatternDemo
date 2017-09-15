package com.example.tangminghui.designpatterndemo.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
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
        ItemWeatherBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_weather,parent,false);
        WeatherViewHolder holder = new WeatherViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
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
        }

        public void setBinding(ItemWeatherBinding binding) {
            this.binding = binding;
        }

        public void bind(@NonNull WeatherMvvm weatherMvvm){
            binding.setWeather(weatherMvvm);

        }

    }
}
