package com.control.data.repositories

import com.control.data.interfaces.IControlDataSource
class ControlRespository(private val control: IControlDataSource) {

    suspend fun TvPowerOff() {}
    suspend fun TvHome() {}
    suspend fun TvBack() {}
    suspend fun TvMicrophone() {}
    suspend fun TvChannelUp() {}
    suspend fun TvChannelDown() {}
    suspend fun TvVolumenUp() {}
    suspend fun TvVolumenDown() {}
}