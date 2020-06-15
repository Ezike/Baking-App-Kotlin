package com.example.eziketobenna.bakingapp.presentation.mapper

interface ModelMapper<M, D> {

    fun mapToModel(domain: D): M
    fun mapToDomain(model: M): D

    fun mapToModelList(domainList: List<D>): List<M> {
        return domainList.mapTo(mutableListOf(), ::mapToModel)
    }

    fun mapToDomainList(modelList: List<M>): List<D> {
        return modelList.mapTo(mutableListOf(), ::mapToDomain)
    }
}
