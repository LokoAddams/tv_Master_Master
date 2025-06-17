package com.control.usecases

import com.control.data.repositories.ControlRespository
class Home (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvHome()
    }
}