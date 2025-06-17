package com.control.usecases

import com.control.data.repositories.ControlRespository
class Back (val controlRepository : ControlRespository)
{
    suspend fun invoke()
    {
        controlRepository.TvBack()
    }
}