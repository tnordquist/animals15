package edu.cnm.deepdive.animals15.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.animals15.BuildConfig;
import edu.cnm.deepdive.animals15.model.Animal;

import io.reactivex.rxjava3.core.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

import java.util.List;

public interface WebServiceProxy {

    @GET("images")
    Single<List<Animal>> getAnimals();

    static WebServiceProxy getInstance() {
        return InstanceHolder.INSTANCE;
    }

    class InstanceHolder {

        private static final WebServiceProxy INSTANCE;

        static {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            INSTANCE = retrofit.create(WebServiceProxy.class);
        }
    }
}
