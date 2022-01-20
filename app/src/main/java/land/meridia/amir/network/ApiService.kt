package land.meridia.amir.network

import land.meridia.amir.model.Features
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET(".json")
    suspend fun getFeature(): Response<Features>
}