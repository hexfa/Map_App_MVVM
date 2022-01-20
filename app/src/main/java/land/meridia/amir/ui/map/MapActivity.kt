package land.meridia.amir.ui.map

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import land.meridia.amir.model.Point
import land.meridia.amir.network.NetworkResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import land.meridia.amir.R
import land.meridia.amir.databinding.ActivityMapBinding


@AndroidEntryPoint
class MapActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapBinding
    val zoomLevel = 16.0f //This goes up to 21

    private val mapViewModel by viewModels<MapViewModel>()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        fetchData()


    }

    fun drawPolygon(points: List<Point>) {
        val simpleLocation = LatLng(points.get(0).latitude,points.get(0).longitude)
        val opts = PolygonOptions()
        for (location in points) {
            opts.add(LatLng(location.latitude, location.longitude))
            when (location.accuracy) {
                in 0.0..1.5 -> {
                    mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)).position(LatLng(location.latitude, location.longitude)))
                }
                in 1.5..2.0 -> {
                    mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)).position(LatLng(location.latitude, location.longitude)))

                }else ->{
                mMap.addMarker(MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)).position(LatLng(location.latitude, location.longitude)))
                }

            }


        }
        mMap.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                simpleLocation
                , zoomLevel
            )
        );
        mMap.addPolygon(
            opts.strokeColor(Color.BLACK).strokeWidth(3f)
                .fillColor(Color.argb(20, 255, 0, 50))
        )


    }

    @InternalCoroutinesApi
    private fun fetchData() {
        mapViewModel.fetchResponse()
        mapViewModel.response.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    it.data?.features?.forEach {
                        drawPolygon(it.points)
                    }
                }
                is NetworkResult.Error -> {
                    // show error message
                }
                is NetworkResult.Loading -> {
                    // show a progress bar
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}