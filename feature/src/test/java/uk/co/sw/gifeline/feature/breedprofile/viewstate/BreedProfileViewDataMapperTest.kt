package uk.co.sw.gifeline.feature.breedprofile.viewstate

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState.Profile.Stat

class BreedProfileViewDataMapperTest {

    private lateinit var mapper: BreedProfileViewDataMapper

    @Before
    fun setUp() {
        mapper = BreedProfileViewDataMapper()
    }

    @Test
    fun `Given breed and images, When mapped, Then return view data`() {
        // Given
        val mockImage: CatImage = mockk { every { url } returns "imageUrl" }
        val mockBreed = CatBreed(
            id = "id",
            name = "name",
            altNames = listOf("altName"),
            origin = "origin",
            description = "description",
            lifeSpan = "life span",
            weight = "weight range",
            stats = CatBreed.Stats(energyLevel = 1, intelligence = 2, vocalisation = 3),
            wikiUrl = "wikiUrl"
        )

        // When
        val result = mapper.map(mockBreed, listOf(mockImage))

        // Then
        assertThat(result.id).isEqualTo("id")
        assertThat(result.imageUrls).containsExactly("imageUrl")
        assertThat(result.name).isEqualTo("name")
        assertThat(result.altNames).containsExactly("altName")
        assertThat(result.origin).isEqualTo("origin")
        assertThat(result.description).isEqualTo("description")
        assertThat(result.lifeSpan).isEqualTo("lifespan")
        assertThat(result.weight).isEqualTo("weightrange")
        with(result.stats){
            fun assertStat(stat: Stat, expectedName: String, expectedScore: Int){
                assertThat(stat.name).isEqualTo(expectedName)
                assertThat(stat.score).isEqualTo(expectedScore)
                assertThat(stat.max).isEqualTo(5)
            }
            assertThat(this).hasSize(3)
            assertStat(get(0), "Energy", 1)
            assertStat(get(1), "Intelligence", 2)
            assertStat(get(2), "Vocalisation", 3)
        }
        assertThat(result.wikiUrl).isEqualTo("wikiUrl")

    }

}