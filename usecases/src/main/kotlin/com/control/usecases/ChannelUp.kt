package com.control.usecases

import com.control.data.repositories.ControlRespository
class ChannelUp (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvChannelUp()
    }
}