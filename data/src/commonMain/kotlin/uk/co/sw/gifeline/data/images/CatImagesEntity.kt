package uk.co.sw.gifeline.data.images

import kotlinx.serialization.Serializable

@Serializable
data class CatImagesEntity(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
)