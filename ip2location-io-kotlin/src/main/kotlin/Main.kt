import com.google.gson.JsonObject
import kotlinx.coroutines.runBlocking

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            // Configures IP2Location.io API key
            val config = Configuration()
            val apiKey = "YOUR_API_KEY"
            config.apiKey = apiKey
            val ipl = IPGeolocation(config)

            // Lookup ip address geolocation data
            val myObj: JsonObject = runBlocking {
                ipl.lookup(
                    "8.8.8.8", "en"
                )
            } // the language parameter is only available for Plus and Security plans

            println(myObj)

            val whois = DomainWhois(config)

            // Lookup domain information
            val myObj2: JsonObject = runBlocking { whois.lookup("locaproxy.com") }
            println(myObj2)

            // Convert normal text to punycode
            println(whois.toPunycode("täst.de"))

            // Convert punycode to normal text
            println(whois.toNormalText("xn--tst-qla.de"))

            // Get domain name from URL
            println(whois.toDomainName("https://www.example.com/exe"))

            // Get domain extension (gTLD or ccTLD) from URL or domain name
            println(whois.toDomainExtension("example.com"))
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}