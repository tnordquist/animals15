package edu.cnm.deepdive.animals15.controller;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    private class RetrieverTask extends AsyncTask<Void, Void, List<Animal>> {

        @Override
        protected List<Animal> doInBackground(Void... voids) {
            try {
                Response<List<Animal>> response = WebServiceProxy.getInstance()
                        .getAnimals()
                        .execute();
                if (response.isSuccessful()) {
                    Log.d(getClass().getName(), response.body().toString());

                    return response.body();
                } else {
                    Log.e(getClass().getName(), response.message());
                }
            } catch (IOException e) {
                Log.e(getClass().getName(), e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Animal> animals) {
            super.onPostExecute(animals);
            adapter = new ArrayAdapter<>(
                    MainActivity.this, R.layout.item_animal_spinner, animals);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            animalSelector.setAdapter(adapter);
        }
    }
}
