package com.example.pagingsample.di

import android.content.Context
import androidx.room.Room
import com.example.pagingsample.api.PlayerApi
import com.example.pagingsample.data.db.PlayerDatabase
import com.example.pagingsample.utils.CONSTANTS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(CONSTANTS.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getPlayerService(retrofit: Retrofit):PlayerApi{
        return retrofit.create(PlayerApi::class.java)
    }

    @Singleton
    @Provides
    fun getDatabaseInstance(@ApplicationContext context: Context):PlayerDatabase{
        return Room.databaseBuilder(context,PlayerDatabase::class.java,"PlayerDB").build()
    }
}