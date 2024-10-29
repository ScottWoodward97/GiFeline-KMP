package uk.co.sw.gifeline.feature.common

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

/**
 * Purely a wrapper around the getString method for the purposes of mocking in tests
 */
class ResourceStringProvider {

    suspend fun getResourceString(resource: StringResource): String = getString(resource)

}