package com.e.heroesapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import heroesapi.HeroesAPI;
import model.Heroes;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import url.Url;

public class MainActivity extends AppCompatActivity {
    private ImageView imgPhoto;
    private EditText etName, etDesc;
    private Button btnSave;
    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPhoto = findViewById(R.id.imgPhoto);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        btnSave = findViewById(R.id.btnSave);
        tvData = findViewById(R.id.tvData);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });
    }

    private void Save() {
        String name = etName.getText().toString();
        String desc = etDesc.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HeroesAPI heroesAPI = retrofit.create(HeroesAPI.class);
        // for adding heroes
        Call<Void> heroesCall = heroesAPI.addhero(name, desc);


       // for getting list of heroes added
        Call<List<Heroes>> listCall = heroesAPI.getHeroes();

        // Adding heroes
        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Getting Heroes
        listCall.enqueue(new Callback<List<Heroes>>() {
            @Override
            public void onResponse(Call<List<Heroes>> call, Response<List<Heroes>> response) {
                if (!response.isSuccessful()) {
                    tvData.setText("Code: " + response.code());
                    return;
                }

                List<Heroes> heroesList = response.body();
                for (Heroes hero : heroesList) {
                    String content = "";
                    content += "Id" + hero.get_id() + "\n";
                    content += "Hero name" + hero.getName() + "\n";
                    content += "Hero description" + hero.getDesc();

                    tvData.append(content);
                }
            }

            // Getting Heroes Part
            @Override
            public void onFailure(Call<List<Heroes>> call, Throwable t) {
                tvData.setText("Error" + t.getMessage());
            }
        });

    }
}
