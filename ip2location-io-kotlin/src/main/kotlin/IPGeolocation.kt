import com.google.gson.*
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.net.URI
import java.net.URLEncoder
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

/**
 * This class performs the lookup of geolocation data from an IP address by querying the IP2Location.io API.
 *
 *
 * Copyright (c) 2002-2023 IP2Location.com
 *
 * @author IP2Location.com
 * @version 1.0.0
 */
class IPGeolocation
/**
 * This constructor accepts the Configuration object and stores it.
 *
 * @param configuration The Configuration object.
 */(private val configuration: Configuration) {
    /**
     * This function to query IP2Location.io geolocation data.
     *
     * @param ip IP Address you wish to query
     * @return IP2Location.io geolocation data
     * @throws Exception If parameters are incorrect or API call failed.
     */
    @Throws(Exception::class)
    suspend fun lookup(ip: String?): JsonObject {
        return lookup(ip, "")
    }

    /**
     * This function to query IP2Location.io geolocation data.
     *
     * @param ip       IP Address you wish to query
     * @param language The translation language
     * @return IP2Location.io geolocation data
     * @throws Exception If parameters are incorrect or API call failed.
     */
    @Throws(Exception::class)
    suspend fun lookup(ip: String?, language: String?): JsonObject {
        val url =
            BASE_URL + "?format=" + FORMAT + "&source=" + SOURCE + "&source_version=" + Configuration.version + "&key=" + configuration.apiKey + "&ip=" + URLEncoder.encode(
                ip, "UTF-8"
            ) + "&lang=" + URLEncoder.encode(language, "UTF-8")
        val request: HttpRequest = HttpRequest.newBuilder().uri(URI(url)).GET().build()
        val client = HttpClient.newHttpClient()
        val response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).await()
        val statusCode = response.statusCode()
        if (statusCode == 200) {
            val rawJSON = response.body()
            return JsonParser.parseString(rawJSON).asJsonObject
        } else if (statusCode == 400 || statusCode == 401) {
            val rawJSON = response.body()
            if (rawJSON.contains("error_message")) {
                throw Exception(
                    JsonParser.parseString(rawJSON).asJsonObject.getAsJsonObject("error").get("error_message").asString
                )
            }
        }
        throw Exception(ERROR)
    }

    companion object {
        private const val BASE_URL = "https://api.ip2location.io/"
        private const val SOURCE = "sdk-kotlin-iplio"
        private const val FORMAT = "json"
        private const val ERROR = "IPGeolocation lookup error."
    }
}
