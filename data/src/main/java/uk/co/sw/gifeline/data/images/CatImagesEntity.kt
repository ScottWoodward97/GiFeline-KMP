package uk.co.sw.gifeline.data.images

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatImagesEntity(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
)