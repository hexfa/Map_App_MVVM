package land.meridia.amir.ui.map

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import land.meridia.amir.model.Features
import land.meridia.amir.network.NetworkResult
import land.meridia.amir.repository.MapRepository
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository,
    application: Application
    ) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<Features>> = MutableLiveData()
    val response: LiveData<NetworkResult<Features>> = _response


    @InternalCoroutinesApi

    fun fetchResponse() = viewModelScope.launch {
        repository.getFeatures().collect{

            _response.value = it
        }
    }


}



