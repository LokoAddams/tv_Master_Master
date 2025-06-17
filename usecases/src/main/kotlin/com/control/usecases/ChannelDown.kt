package com.control.usecases

import com.control.data.repositories.ControlRespository
class ChannelDown (val controlRepository : ControlRespository){
    suspend fun invoke()
    {
        controlRepository.TvChannelDown()
    }
}