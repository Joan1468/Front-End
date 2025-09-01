package com.udec.cajica.di

import com.udec.cajica.data.api.ApiService
import com.udec.cajica.data.api.RetrofitClient
import com.udec.cajica.data.repository.UsuarioRepository
import com.udec.cajica.data.repository.EquipoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://192.168.1.7:8080/"

    @Provides
    @Singleton
    fun provideBaseApi(): ApiService =
        RetrofitClient.createApi(BASE_URL, ApiService::class.java)

    @Provides
    @Singleton
    fun provideUsuarioRepository(api: ApiService): UsuarioRepository =
        UsuarioRepository(api)

    @Provides
    @Singleton
    fun provideEquipoRepository(api: ApiService): EquipoRepository =
        EquipoRepository(api)
}
