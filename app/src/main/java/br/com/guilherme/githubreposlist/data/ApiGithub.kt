package br.com.guilherme.githubreposlist.data

import br.com.guilherme.githubreposlist.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiGithub {

    companion object {

        private val mClient = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .connectTimeout(0, TimeUnit.SECONDS)
            .readTimeout(0, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .build()

        private val moshiAdapter = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()


        var apiService = connectionRetrofit().create(ApiGithubInterface::class.java)

        private fun connectionRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshiAdapter))
                .client(mClient)
                .build()

        }

        private fun getLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        }

    }
}