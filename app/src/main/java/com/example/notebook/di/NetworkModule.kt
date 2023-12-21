package com.example.notebook.di

import com.example.notebook.api.AuthInterceptor
import com.example.notebook.api.NotesAPI
import com.example.notebook.api.userAPI
import com.example.notebook.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder():Retrofit.Builder{
      return Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(BASE_URL)


    }
    @Singleton
    @Provides
    fun providesOkHttpClient(authInterceptor: AuthInterceptor) : OkHttpClient{
     return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder):userAPI{
        return retrofitBuilder.build().create(userAPI::class.java)
    }

    @Singleton
    @Provides
    fun providesNoteAPI(retrofitBuilder: Builder,okHttpClient: OkHttpClient):NotesAPI{
        return retrofitBuilder
            .client(okHttpClient)
            .build().create(NotesAPI::class.java)
    }
}