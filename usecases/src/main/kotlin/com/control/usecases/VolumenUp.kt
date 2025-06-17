package com.control.usecases

import com.control.data.repositories.ControlRespository
class VolumenUp (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvVolumenUp()
    }
}