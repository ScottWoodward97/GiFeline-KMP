package uk.co.sw.gifeline.domain.breed

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.data.breed.CatBreedEntity

class CatBreedMapperTest {

    private lateinit var mapper: CatBreedMapper

    @Before
    fun setUp() {
        mapper = CatBreedMapper()
    }

    @Test
    fun `Given entity with alt names, when mapped, then return breed object`() {
        // Given
        val entity = CatBreedEntity(
            id = "id",
            name = "name",
            altNames = "alt1, alt2"
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.id).isEqualTo("id")
        assertThat(result.name).isEqualTo("name")
        assertThat(result.altNames).containsExactly("alt1", "alt2")
    }

    @Test
    fun `Given entity null alt names, when mapped, then return breed object`() {
        // Given
        val entity = CatBreedEntity(
            id = "id",
            name = "name",
            altNames = null
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.id).isEqualTo("id")
        assertThat(result.name).isEqualTo("name")
        assertThat(result.altNames).isEmpty()
    }

    @Test
    fun `Given entity empty alt names, when mapped, then return breed object`() {
        // Given
        val entity = CatBreedEntity(
            id = "id",
            name = "name",
            altNames = ""
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.id).isEqualTo("id")
        assertThat(result.name).isEqualTo("name")
        assertThat(result.altNames).isEmpty()
    }

}