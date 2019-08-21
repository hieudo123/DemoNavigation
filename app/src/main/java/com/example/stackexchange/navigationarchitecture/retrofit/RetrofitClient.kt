package com.example.lifestyle.hope.retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


class RetrofitClient private constructor(){

    companion object {
        var gson = GsonBuilder()
                .setLenient()
                .create()
        private var retrofit: Retrofit? = null
        fun getClient(baseUrl: String): Retrofit? {
            var build : OkHttpClient = OkHttpClient.Builder()
                    .readTimeout(5000,TimeUnit.MILLISECONDS)
                    .writeTimeout(5000,TimeUnit.MILLISECONDS)
                    .connectTimeout(10000,TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true).build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .client(build)
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
            }
            return retrofit
        }
    }

}