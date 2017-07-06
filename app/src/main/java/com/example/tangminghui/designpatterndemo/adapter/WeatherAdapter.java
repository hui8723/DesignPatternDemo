package com.example.tangminghui.designpatterndemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;

import java.util.List;

/**
 * Created by tangminghui on 2017/6/29.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private Context context;
    private List<WeatherEntity> dates;

    public WeatherAdapter(Context context, List<WeatherEntity> dates){
        this.context = context;
        this.dates = dates;
    }


    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WeatherViewHolder holder = new WeatherViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.tvDate.setText(dates.get(position).getDate());
        holder.tvWeek.setText(dates.get(position).getWeek());
        holder.tvTemperature.setText(dates.get(position).getTemperature());
        holder.tvWindSpeed.setText(dates.get(position).getWind());
        holder.tvDay.setText(dates.get(position).getDayTime());
        holder.tvNight.setText(dates.get(position).getNight());
    }


    @Override
    public int getItemCount() {
        return dates.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate,tvWeek,tvTemperature, tvWindSpeed,tvDay,tvNight;

       public WeatherViewHolder(View view){
            super(view);
           tvDate = (TextView) view.findViewById(R.id.tv_date);
           tvWeek = (TextView) view.findViewById(R.id.tv_week);
           tvTemperature = (TextView) view.findViewById(R.id.tv_temperature);
           tvWindSpeed = (TextView) view.findViewById(R.id.tv_wind_speed);
            tvDay = (TextView) view.findViewById(R.id.tv_day);
           tvNight = (TextView) view.findViewById(R.id.tv_night);
        }

    }

}
