package com.dariobabic.revoultdemo.domain.mapper

interface Mapper<E, D> {

    fun mapToEntity(response: E): D
}