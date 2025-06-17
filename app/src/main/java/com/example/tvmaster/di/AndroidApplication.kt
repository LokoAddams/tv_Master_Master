package com.example.tvmaster.di

import android.app.Application
import com.connectsdk.discovery.DiscoveryManager
import com.connectsdk.service.DIALService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class  AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // ESTE ES EL LUGAR CORRECTO Y ÚNICO PARA INICIALIZAR
        DIALService.registerApp("Levak")
        DiscoveryManager.init(applicationContext)

        // Configura el nivel de emparejamiento
        DiscoveryManager.getInstance().setPairingLevel(DiscoveryManager.PairingLevel.ON)

        // Registra los tipos de dispositivos por defecto que el SDK puede descubrir
        DiscoveryManager.getInstance().registerDefaultDeviceTypes()

        // Inicia el proceso de descubrimiento
        DiscoveryManager.getInstance().start()
    }
}