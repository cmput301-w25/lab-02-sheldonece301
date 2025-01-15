package com.example.listycity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        Button addCityButton = findViewById(R.id.add_city);
        Button deleteCityButton = findViewById(R.id.delete_city);
        Button confirmCityAdd = findViewById(R.id.confirm_city);

        TextInputEditText cityInput = findViewById(R.id.enter_city);

        String []cities = {"Edmonton", "Calgary"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        addCityButton.setOnClickListener(v -> {
            cityInput.setVisibility(ListView.VISIBLE);
            confirmCityAdd.setVisibility(ListView.VISIBLE);
        });

        confirmCityAdd.setOnClickListener(v -> {
            String nameOfCity = cityInput.getText().toString();
            if (!nameOfCity.isEmpty()) {
                dataList.add(nameOfCity);
                cityAdapter.notifyDataSetChanged();
                cityInput.setText("");
            }
        });

        deleteCityButton.setOnClickListener(v -> {
            dataList.remove(1);
            cityAdapter.notifyDataSetChanged();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}