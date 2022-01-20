package land.meridia.amir.model

import com.google.gson.annotations.SerializedName




data class Features(
    @SerializedName("features")
    val features: List<FeatureObject>
)

data class FeatureObject(
    @SerializedName("id")
    val id: Long = 0,
    @SerializedName( "points")
    val points: List<Point>
)
data class Point(
    @SerializedName( "accuracy")
    val accuracy: Double = 0.0,
    @SerializedName( "latitude")
    val latitude: Double = 0.0,
    @SerializedName( "longitude")
    val longitude: Double = 0.0
)
