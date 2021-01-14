package br.com.guilherme.githubreposlist.data

import br.com.guilherme.githubreposlist.BuildConfig
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiGithub {

    companion object {

        private var retrofit: Retrofit? = null
        var apiGithub = connectionRetrofit()?.create(ApiGithubInterface::class.java)

        private fun connectionRetrofit(): Retrofit? {
            if (retrofit == null)
                retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(mGson))
                    .build()

            return retrofit
        }

        private var mGson = GsonBuilder()
            .setLenient()
            .create()

    }
}