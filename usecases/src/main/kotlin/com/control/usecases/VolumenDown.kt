package com.control.usecases

import com.control.data.repositories.ControlRespository
class VolumenDown (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvVolumenDown()
    }
}