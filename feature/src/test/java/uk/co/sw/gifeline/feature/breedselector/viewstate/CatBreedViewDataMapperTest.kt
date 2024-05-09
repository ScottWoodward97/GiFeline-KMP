package uk.co.sw.gifeline.feature.breedselector.viewstate

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.domain.breed.CatBreed

class CatBreedViewDataMapperTest {

    private lateinit var mapper: CatBreedViewDataMapper

    @Before
    fun setUp() {
        mapper = CatBreedViewDataMapper()
    }

    @Test
    fun `Given cat breed, When mapped, Then return viewdata`() {
        // Given
        val breed = CatBreed(
            id = "id",
            name = "name",
            altNames = listOf("alt")
        )

        // When
        val result = mapper.map(breed)

        // Then
        assertThat(result.id).isEqualTo("id")
        assertThat(result.name).isEqualTo("name")
        assertThat(result.altNames).containsExactly("alt")
    }

}