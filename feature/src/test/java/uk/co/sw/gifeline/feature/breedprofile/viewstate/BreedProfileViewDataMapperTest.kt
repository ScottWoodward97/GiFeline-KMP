package uk.co.sw.gifeline.feature.breedprofile.viewstate

import gifeline.feature.generated.resources.Res
import gifeline.feature.generated.resources.breed_profile_state_energy_title
import gifeline.feature.generated.resources.breed_profile_state_intelligence_title
import gifeline.feature.generated.resources.breed_profile_state_vocalisation_title
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import uk.co.sw.gifeline.domain.breed.CatBreed
import uk.co.sw.gifeline.domain.images.CatImage
import uk.co.sw.gifeline.feature.breedprofile.viewstate.BreedProfileViewState.Profile.Stat
import uk.co.sw.gifeline.feature.common.ResourceStringProvider

class BreedProfileViewDataMapperTest {

    private val mockResources: ResourceStringProvider = mockk()

    private lateinit var mapper: BreedProfileViewDataMapper

    @Before
    fun setUp() {
        mapper = BreedProfileViewDataMapper(mockResources)
    }

    @After
    fun tearDown() {
        confirmVerified(mockResources)
    }

    @Test
    fun `Given breed and images, When mapped, Then return view data`() = runTest {
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
        coEvery {
            mockResources.getResourceString(Res.string.breed_profile_state_energy_title)
        } returns "Energy"
        coEvery {
            mockResources.getResourceString(Res.string.breed_profile_state_intelligence_title)
        } returns "Intelligence"
        coEvery {
            mockResources.getResourceString(Res.string.breed_profile_state_vocalisation_title)
        } returns "Vocalisation"

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
        with(result.stats) {
            fun assertStat(stat: Stat, expectedName: String, expectedScore: Int) {
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
        coVerify { mockResources.getResourceString(Res.string.breed_profile_state_energy_title) }
        coVerify { mockResources.getResourceString(Res.string.breed_profile_state_intelligence_title) }
        coVerify { mockResources.getResourceString(Res.string.breed_profile_state_vocalisation_title) }
    }

}