package br.com.guilherme.githubreposlist.data

import br.com.guilherme.githubreposlist.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiGithub {

    companion object {

        private var retrofit: Retrofit? = null
        var apiGithub = connectionRetrofit()?.create(ApiGithubInterface::class.java)


        private fun connectionRetrofit(): Retrofit? {
            if (retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(mGson))
                    .client(mClient)
                    .build()

            return retrofit
        }

        private var mGson = GsonBuilder()
            .setLenient()
            .create()

        private fun getLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        private val mClient = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .connectTimeout(0, TimeUnit.SECONDS)
            .readTimeout(0, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .build()

    }
}