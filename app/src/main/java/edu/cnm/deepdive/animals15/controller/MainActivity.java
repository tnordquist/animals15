package edu.cnm.deepdive.animals15.controller;

import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.animals15.R;
import edu.cnm.deepdive.animals15.model.Animal;
import edu.cnm.deepdive.animals15.service.WebServiceProxy;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Retriever().start();
    }

    private class Retriever extends Thread {

        @Override
        public void run() {
            try {
                Response<List<Animal>> response = WebServiceProxy.getInstance()
                        .getAnimals()
                        .execute();
                if (response.isSuccessful()) {
                    Log.d(getClass().getName(), response.body().toString());
                } else {
                    Log.e(getClass().getName(), response.message());
                }
            } catch (IOException e) {
                Log.e(getClass().getName(), e.getMessage(), e);
            }
        }
    }
}