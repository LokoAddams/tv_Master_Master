package com.example.tvmaster.di

import android.content.Context
import com.dataStorePreferences.UserPreferencesRepository
import com.example.data.DispGuardadosRepository
import com.example.data.ILocalDataSource
import com.example.framework.DataStorePref.DataStoreManager
import com.example.framework.dispGuardados.DispGuardadosLocalDataSource
import com.example.usecases.GetDispTV
import com.example.usecases.SaveDispTV
import com.example.usecases.existeDispTV
import com.themes.ObserveThemeUseCase
import com.themes.SetThemeUseCase
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

    //USECASES
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

    @Provides
    @Singleton
    fun provideExisteDispTV(
        repository: DispGuardadosRepository): existeDispTV {
        return existeDispTV(repository)
    }

    //THEMES
    @Provides
    @Singleton
    fun provideUserPreferencesRepository(
        @ApplicationContext context: Context
    ): UserPreferencesRepository {
        return DataStoreManager(context)
    }

    @Provides
    fun provideObserveThemeUseCase(
        repository: UserPreferencesRepository
    ): ObserveThemeUseCase = ObserveThemeUseCase(repository)

    @Provides
    fun provideSetThemeUseCase(
        repository: UserPreferencesRepository
    ): SetThemeUseCase = SetThemeUseCase(repository)
}