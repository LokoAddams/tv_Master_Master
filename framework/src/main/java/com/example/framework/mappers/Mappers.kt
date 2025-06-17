package com.example.framework.mappers

import com.example.domain.DispTV
import com.example.framework.persistence.DispGuardado

fun DispTV.toEntity() : DispGuardado {
    return DispGuardado(dispNombre, dispToken)
}