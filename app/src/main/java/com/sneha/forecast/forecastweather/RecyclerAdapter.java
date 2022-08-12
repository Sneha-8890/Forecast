package com.sneha.forecast.forecastweather;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneha.forecast.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private List<RecyclerData> list;
    private Context context;
    public static final String [] days = {"Sunday","Monday", "Tuesday", "Wednesday","Thursday","Friday", "Saturday"};

    Date day = new Date();

    public RecyclerAdapter(ArrayList<RecyclerData> recyclerDataArrayList, Context mcontext) {
        this.list = recyclerDataArrayList;
        this.context = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_view_holder, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.temp.setText(String.format("Temperature: %s℃", list.get(position).getTemperature()));
        holder.max.setText(String.format("Max Temperature: %s℃", list.get(position).getTemperature_max()));
        holder.min.setText(String.format("Min Temperature: %s℃", list.get(position).getTemperature_min()));
        Picasso.get()
                .load("https://openweathermap.org/img/wn/"+list.get(position).getIcon()+"@2x.png")
                .into(holder.img);

        if(position==0)
            holder.day.setText("Tomorrow");
        else
            holder.day.setText(days[(day.getDay() + (position+1))%7]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{

        private TextView temp, max, min, day;
        ImageView img;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            temp = itemView.findViewById(R.id.forecast_temp);
            max = itemView.findViewById(R.id.forecast_max);
            min = itemView.findViewById(R.id.forecast_min);
            img = itemView.findViewById(R.id.forecast_image);
            day = itemView.findViewById(R.id.day);
        }
    }
}
