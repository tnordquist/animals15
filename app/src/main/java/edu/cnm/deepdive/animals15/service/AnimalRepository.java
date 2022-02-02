package edu.cnm.deepdive.animals15.service;

import android.content.Context;
import edu.cnm.deepdive.animals15.model.Animal;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.List;

public class AnimalRepository {

    private final Context context;

    public AnimalRepository(Context context) {
        this.context = context;
    }

    public Single<List<Animal>> getAnimals() {
        return WebServiceProxy
                .getInstance()
                .getAnimals()
                .subscribeOn(Schedulers.io());
    }

}
