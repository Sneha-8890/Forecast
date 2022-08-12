package com.sneha.forecast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sneha.forecast.cities.CityAdapter;
import com.sneha.forecast.cities.cityModel;
import java.util.ArrayList;

public class CitySelection extends AppCompatActivity {

    RecyclerView recyclerView;
    CityAdapter cityAdapter;
    ArrayList<cityModel> cityModels =new ArrayList<>();


    public static final String [] cities = {"LONDON", "PARIS", "NEW YORK","MOSCOW","DUBAI","TOKYO","SINGAPORE","LOS-ANGELES","BARCELONA","MADRID","ROME",
            "DOHA","CHICAGO","ABU DHABI","SAN FRANCISCO","AMSTERDAM","ST PETERSBURG","TORONTO","SYDNEY","BERLIN","LASVEGAS","WASHINGTON","ISTANBUL","VIENNA",
            "BEIJING","PRAGUE","MILAN","SAN DIEGO","HONGKONG","MELBOURNE","BOSTON","HOUSTON","DUBLIN","MIAMI","ZURICH","SEATTLE","BUDAPEST","SAO-PAULO","MUNICH",
            //BANGKOK,ORLANDO,SEOUL,ATLANTA,DALLAS,FRANKFURT,VANCOUVER,AUSTIN,MONTREAL,CALGARY,DELHI,LISBON,NAPLES,OSAKA,SAN-JOSE,RIYADH,DENVER,PHILADELPHIA,TEL-AVIV,COPENHAGEN,BRUSSELS,BRISBANE,VALENCIA,BUENOS-AIRES,TAIPEI,RIO-DE-JANEIRO,PORTLAND,HAMBURG,KUWAIT-CITY,WARSAW,ATHENS,PERTH,HELSINKI,MINNEAPOLIS,OSLO,SHANGHAI,PHOENIX,AUCKLAND,NEW-ORLEANS,JERUSALEM,MUSCAT,NASHVILLE,STOCKHOLM,SANTIAGO,OTTAWA,BALTIMORE,EDMONTON,LYON,MARSEILLE,ADELAIDE,GOTEBORG,BILBAO,MEXICO-CITY,SALT-LAKE-CITY,MUMBAI,SACRAMENTO,SAN-ANTONIO,TUCSON,SEVILLE,CHARLOTTE,"NANJING"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_selection);

        for(String item : cities){
            cityModels.add(new cityModel(item));
        }

        recyclerView= findViewById(R.id.city_recycler);
        cityAdapter= new CityAdapter(cityModels, this, new CityAdapter.clickedCity() {
            @Override
            public void cityClicked(int adapterPosition) {
                Intent intent = new Intent(CitySelection.this, MainActivity.class);
                Log.v("sjshaj", cityModels.get(adapterPosition).getCity());
                intent.putExtra("citynametosearch", cityModels.get(adapterPosition).getCity());
                startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(cityAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search_location);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.v("hey","hey");
                cityAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}