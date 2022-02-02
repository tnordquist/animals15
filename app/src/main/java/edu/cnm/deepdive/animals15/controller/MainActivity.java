package edu.cnm.deepdive.animals15.controller;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationBarView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import edu.cnm.deepdive.animals15.BuildConfig;
import edu.cnm.deepdive.animals15.R;
import edu.cnm.deepdive.animals15.model.Animal;
import edu.cnm.deepdive.animals15.service.WebServiceProxy;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner animalSelector;
    private ArrayAdapter<Animal> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animalSelector = findViewById(R.id.animal_selector);
        animalSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Animal animal = (Animal) adapterView.getItemAtPosition(position);
                if (animal.getImageUrl() != null) {
                    Picasso.get().load(String.format(BuildConfig.CONTENT_FORMAT, animal.getImageUrl()))
                            .into((ImageView) findViewById(R.id.image));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        new RetrieverTask().execute();
    }

    private class RetrieverTask extends AsyncTask<Void, Void, List<Animal>> {

        @Override
        protected List<Animal> doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            super.onPostExecute(animals);
            String url = animals.get(0).getImageUrl();
            adapter = new ArrayAdapter<>(
                    MainActivity.this, R.layout.item_animal_spinner, animals);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            if (url != null) {
                Picasso.get().load(String.format(BuildConfig.CONTENT_FORMAT, url))
                        .into((ImageView) findViewById(R.id.image));
            }
            animalSelector.setAdapter(adapter);
        }
    }
}
