package hsvp.survey.hry.pkl.retrofitinterface;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import hsvp.survey.hry.pkl.utility.GlobalClass;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static retrofit2.Retrofit retrofit = null;

    public static ApiInterface getClient() {

        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor)
                    .connectTimeout(15, TimeUnit.SECONDS) // connect timeout
                    .writeTimeout(15, TimeUnit.SECONDS) // write timeout
                    .readTimeout(15, TimeUnit.SECONDS) // read timeout
                    .build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(GlobalClass.baseurl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

        }

        return retrofit.create(ApiInterface.class);
    }


}