package com.control.usecases

import com.control.data.repositories.ControlRespository
class Microphone (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvMicrophone()
    }
}