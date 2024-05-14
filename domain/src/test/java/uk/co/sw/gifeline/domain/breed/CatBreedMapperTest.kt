package uk.co.sw.gifeline.domain.breed

import io.mockk.every
import io.mockk.mockk
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
            altNames = "alt1, alt2",
            origin = "origin",
            description = "description",
            lifeSpan = "12 - 15",
            weight = mockk { every { metric } returns "weight" },
            energyLevel = 1,
            intelligence = 4,
            vocalisation = 3,
            wikiUrl = "url"
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.id).isEqualTo("id")
        assertThat(result.name).isEqualTo("name")
        assertThat(result.altNames).containsExactly("alt1", "alt2")
        assertThat(result.origin).isEqualTo("origin")
        assertThat(result.description).isEqualTo("description")
        assertThat(result.lifeSpan).isEqualTo("12 - 15")
        assertThat(result.weight).isEqualTo("weight")
        assertThat(result.stats.energyLevel).isEqualTo(1)
        assertThat(result.stats.intelligence).isEqualTo(4)
        assertThat(result.stats.vocalisation).isEqualTo(3)
        assertThat(result.wikiUrl).isEqualTo("url")
    }

    @Test
    fun `Given entity null alt names, when mapped, then return breed object`() {
        // Given
        val entity = CatBreedEntity(
            id = "id",
            name = "name",
            altNames = null,
            origin = "origin",
            description = "description",
            lifeSpan = "1 - 2",
            weight = mockk { every { metric } returns "weight" },
            energyLevel = 5,
            intelligence = 5,
            vocalisation = 5,
            wikiUrl = "url"
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.altNames).isEmpty()
    }

    @Test
    fun `Given entity empty alt names, when mapped, then return breed object`() {
        // Given
        val entity = CatBreedEntity(
            id = "id",
            name = "name",
            altNames = "",
            origin = "origin",
            description = "description",
            lifeSpan = "12 - 15",
            weight = mockk { every { metric } returns "weight" },
            energyLevel = 1,
            intelligence = 4,
            vocalisation = 3,
            wikiUrl = "url"
        )

        // When
        val result = mapper.map(entity)

        // Then
        assertThat(result.altNames).isEmpty()
    }

}