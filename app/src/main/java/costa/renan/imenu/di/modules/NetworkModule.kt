package costa.renan.imenu.di.modules

import com.google.gson.Gson
import costa.renan.imenu.data.remote.services.IMenuListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideGson() = Gson()

    @Provides
    @Singleton
    internal fun provideRetrofit(
        client: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            // Had to change url because original is down
            .baseUrl("https://ffm-api.onrender.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            // api can be extremely slow so we increase the timeout
            // this timeout is excessively long because the api takes about this time to report the error
        .connectTimeout(500, TimeUnit.SECONDS)
        .writeTimeout(500, TimeUnit.SECONDS)
        .readTimeout(500, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideIMenuListService(retrofit: Retrofit) = retrofit.create(IMenuListService::class.java)

}