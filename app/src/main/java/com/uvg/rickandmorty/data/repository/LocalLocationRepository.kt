package com.uvg.rickandmorty.data.repository

import com.uvg.rickandmorty.data.model.Location
import com.uvg.rickandmorty.data.source.LocationDb
import com.uvg.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.delay

class LocalLocationRepository: LocationRepository {
    private val locationDb = LocationDb()

    override suspend fun getLocations(): List<Location> {
        delay(4000)
        return locationDb.getAllLocations()
    }

    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDb.getLocationById(id)
    }
}