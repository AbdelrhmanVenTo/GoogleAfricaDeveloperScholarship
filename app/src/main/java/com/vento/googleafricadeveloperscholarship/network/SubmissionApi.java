package com.vento.googleafricadeveloperscholarship.network;

import android.util.Log;

import com.vento.googleafricadeveloperscholarship.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionApi {
    private static Retrofit retrofitInstance;

    private static Retrofit getInstance(){

        if(retrofitInstance==null){
            HttpLoggingInterceptor logging =
                    new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override public void log(String message) {
                            Log.e("retrofit",message);
                        }
                    });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(Constants.SUBMISSION_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofitInstance;

    }

    public static APICalls getAPIs(){
        return getInstance().create(APICalls.class);
    }
}
