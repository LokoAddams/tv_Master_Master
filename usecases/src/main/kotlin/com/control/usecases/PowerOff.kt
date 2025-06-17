package com.control.usecases

import com.control.data.repositories.ControlRespository
class PowerOff (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvPowerOff()
    }
}