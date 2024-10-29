package uk.co.sw.gifeline.domain.images

import uk.co.sw.gifeline.data.images.CatImagesEntity

class CatImageMapper {

    fun map(entity: CatImagesEntity): CatImage {
        return CatImage(url = entity.url)
    }

}