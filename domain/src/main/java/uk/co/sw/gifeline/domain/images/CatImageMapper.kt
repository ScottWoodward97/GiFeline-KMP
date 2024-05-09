package uk.co.sw.gifeline.domain.images

import uk.co.sw.gifeline.data.images.CatImagesEntity
import javax.inject.Inject

class CatImageMapper @Inject constructor() {

    fun map(entity: CatImagesEntity): CatImage {
        return CatImage(url = entity.url)
    }

}