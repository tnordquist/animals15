package edu.cnm.deepdive.animals15.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import edu.cnm.deepdive.animals15.model.Animal;
import edu.cnm.deepdive.animals15.service.AnimalRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel implements LifecycleObserver {

    private final AnimalRepository repository;
    private final MutableLiveData<List<Animal>> animals;
    private final MutableLiveData<Throwable> throwable;
    private final CompositeDisposable pending;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new AnimalRepository(application);
        animals = new MutableLiveData<>();
        throwable = new MutableLiveData<>();
        pending = new CompositeDisposable();
    }

    public LiveData<List<Animal>> getAnimals() {
        return animals;
    }

    public LiveData<Throwable> getThrowable() {
        return throwable;
    }

    // TODO implement method to get animals
    private void load() {
    }

    // TODO better implementation needed
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void clearPending() {
        pending.clear();
    }

}
