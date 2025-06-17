package com.control.data.interfaces

interface IControlDataSource {
    suspend fun TvPowerOff()
    suspend fun TvHome()
    suspend fun TvBack()
    suspend fun TvMicrophone()
    suspend fun TvChannelUp()
    suspend fun TvChannelDown()
    suspend fun TvVolumenUp()
    suspend fun TvVolumenDown()
}