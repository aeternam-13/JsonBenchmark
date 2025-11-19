package com.aeternam.jsonbenchmark.di

import com.aeternam.jsonbenchmark.data.RequestDispatcherApi
import com.aeternam.jsonbenchmark.data.RequestDispatcherRepositoryImpl
import com.aeternam.jsonbenchmark.domain.repository.RequestDispatcherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RequestDispatcherApi {
        return retrofit.create(RequestDispatcherApi::class.java)
    }

    @Binds
    @Singleton
    fun provideRequestDispatcherRepository(apiService: RequestDispatcherApi): RequestDispatcherRepository {
        return RequestDispatcherRepositoryImpl(apiService)
    }

}