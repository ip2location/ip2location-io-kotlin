package org.example

import com.google.gson.JsonObject
import com.ip2location.Configuration
import com.ip2location.IPGeolocation
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertTrue

class IPGeolocationTest {
    @Test
    fun testGeolocation() {
        // Configures IP2Location.io API key
        val config = Configuration()
        val apiKey = "YOUR_API_KEY"

        config.apiKey = apiKey
        val ipl = IPGeolocation(config)

        // Lookup ip address geolocation data
        val myObj: JsonObject = runBlocking {
            ipl.lookup(
                "8.8.8.8"
            )
        }
        assertTrue(myObj["country_code"].asString == "US")
    }
}
