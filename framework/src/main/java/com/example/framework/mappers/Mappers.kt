package com.example.framework.mappers

import com.example.domain.DispTV
import com.example.framework.persistence.DispGuardado

fun DispTV.toEntity(): DispGuardado {
    return DispGuardado(
        id = 0,
        friendlyName = this.friendlyName
    )
}

// Convierte entidad a dominio (para usar en app)
fun DispGuardado.toDomain(): DispTV {
    return DispTV(
        friendlyName = this.friendlyName
    )
}