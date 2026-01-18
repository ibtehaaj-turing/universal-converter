package com.example.converter.di

import com.example.converter.data.repository.ConversionRepositoryImpl
import com.example.converter.domain.repository.ConversionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindConversionRepository(
        impl: ConversionRepositoryImpl
    ): ConversionRepository
}
