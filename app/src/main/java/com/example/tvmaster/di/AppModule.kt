package com.example.tvmaster.di

import android.content.Context
import com.example.data.DispGuardadosRepository
import com.example.data.ILocalDataSource
import com.example.framework.dispGuardados.DispGuardadosLocalDataSource
import com.example.usecases.GetDispTV
import com.example.usecases.SaveDispTV
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDispGuardadosLocalDataSource(@ApplicationContext context: Context): ILocalDataSource {
        return DispGuardadosLocalDataSource(context)
    }

    @Provides
    @Singleton
    fun provideDispGuardadosRepository(localDataSource: ILocalDataSource): DispGuardadosRepository {
        return DispGuardadosRepository(localDataSource)
    }

    @Provides
    @Singleton
    fun provideGetDispTV(repository: DispGuardadosRepository): GetDispTV {
        return GetDispTV(repository)
    }

    @Provides
    @Singleton
    fun provideSaveDispTV(repository: DispGuardadosRepository): SaveDispTV {
        return SaveDispTV(repository)
    }
}