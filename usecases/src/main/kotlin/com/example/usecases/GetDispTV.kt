package com.example.usecases

import com.example.data.DispGuardadosRepository
import com.example.domain.DispTV

class GetDispTV(
    private val repository: DispGuardadosRepository)
{
    suspend operator fun invoke(): List<DispTV> = repository.getDispsGuardados()
}