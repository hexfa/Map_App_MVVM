package land.meridia.amir.repository

import land.meridia.amir.network.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getFeature() =
        apiService.getFeature()
}