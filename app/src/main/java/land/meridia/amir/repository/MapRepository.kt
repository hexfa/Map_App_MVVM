package land.meridia.amir.repository

import land.meridia.amir.model.Features
import land.meridia.amir.network.BaseApiResponse
import land.meridia.amir.network.NetworkResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ActivityRetainedScoped
class MapRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse() {


    suspend fun getFeatures(): Flow<NetworkResult<Features>> {
        return flow<NetworkResult<Features>> {
            emit(safeApiCall { remoteDataSource.getFeature() })
        }.flowOn(Dispatchers.IO)
    }

}