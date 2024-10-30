package uk.co.sw.gifeline.domain.images

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.data.images.CatImagesEntity

class CatImageMapperTest {

    private lateinit var mapper: CatImageMapper

    @Before
    fun setUp() {
        mapper = CatImageMapper()
    }

    @Test
    fun `Given entity, When mapped, Then return cat image`() {
        // Given
        val entity = CatImagesEntity(
            id = "id",
            url = "url",
            width = 100,
            height = 100
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.url).isEqualTo("url")
    }

}