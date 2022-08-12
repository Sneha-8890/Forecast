package com.sneha.forecast.cities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneha.forecast.R;

import java.util.ArrayList;
import java.util.Collection;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> implements Filterable {

    ArrayList<cityModel> cities;
    Context context;
    clickedCity clickedCity;
    ArrayList<String> cityListAll = new ArrayList<>();
    ArrayList<String> cityList = new ArrayList<>();




    public CityAdapter(ArrayList<cityModel> cities, Context context, clickedCity clickedCity) {
        this.cities = cities;
        this.context = context;
        this.clickedCity = clickedCity;

        for(cityModel cityModel : cities){
            Log.v("hey", cityModel.getCity());
            cityListAll.add(cityModel.getCity());
        }
        cityList = cityListAll;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View view = LayoutInflater.from(context).inflate(R.layout.city_name, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.city_name.setText(cities.get(position).getCity());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }


    @Override
    public Filter getFilter() {
        Log.v("hey","hey");
        return new Filter() {

            //background thread
            @Override
            protected Filter.FilterResults performFiltering(CharSequence constraint) {

                Log.v("hey", constraint.toString());
                ArrayList<String> filteredList = new ArrayList<>();

                if(constraint.toString().isEmpty()){
                    filteredList.addAll(cityListAll);

                }else {
                    for(String city : cityListAll){
                        if(city.toLowerCase().contains(constraint.toString().toLowerCase())){
                            filteredList.add(city);
                        }
                    }
                }

                for(String city: filteredList)
                    Log.v("hey",city);

                Filter.FilterResults filterResults = new Filter.FilterResults();
                filterResults.values = filteredList;

                return filterResults;
            }

            //ui thread
            @Override
            protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
                cityList.clear();
                //for(String city: (Collection<? extends String>) results.values)
                  //  Log.v("hey",city);
                cityList.addAll((Collection<? extends String>) results.values);
                cities.clear();
                for(String city : cityList){
                    cities.add(new cityModel(city));
                }
                notifyDataSetChanged();
            }
        };
    }



    class CityViewHolder extends RecyclerView.ViewHolder {

        public TextView city_name;

        public CityViewHolder(@NonNull View itemView) {
            super(itemView);
            city_name = itemView.findViewById(R.id.city_name_from_list);
            itemView.setOnClickListener(v -> {
                clickedCity.cityClicked(getAdapterPosition());
            });

        }
    }

    public interface clickedCity {
        void cityClicked(int adapterPosition);
    }
}