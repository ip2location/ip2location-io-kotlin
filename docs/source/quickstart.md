# Quickstart

## Requirements
Intellij IDEA: <https://www.jetbrains.com/idea/>

## Sample Codes

### Lookup IP Address Geolocation Data
```kotlin
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
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```

### Lookup Domain Information
```kotlin
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
            val whois = DomainWhois(config)

            // Lookup domain information
            val myObj: JsonObject = runBlocking { whois.lookup("locaproxy.com") }
            println(myObj)
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```

### Convert Normal Text to Punycode
```kotlin
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val whois = DomainWhois(Configuration())

            // Convert normal text to punycode
            println(whois.toPunycode("täst.de"))
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```

### Convert Punycode to Normal Text
```kotlin
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val whois = DomainWhois(Configuration())

            // Convert punycode to normal text
            println(whois.toNormalText("xn--tst-qla.de"))
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```

### Get Domain Name
```kotlin
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val whois = DomainWhois(Configuration())

            // Get domain name from URL
            println(whois.toDomainName("https://www.example.com/exe"))
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```

### Get Domain Extension
```kotlin
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val whois = DomainWhois(Configuration())

            // Get domain extension (gTLD or ccTLD) from URL or domain name
            println(whois.toDomainExtension("example.com"))
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```

### Lookup IP Address Hosted Domains Data
```kotlin
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
            val hd = HostedDomain(config)

            // Lookup ip address hosted domains data
            val myObj: JsonObject = runBlocking {
                hd.lookup(
                    "8.8.8.8", 1
                )
            }

            println(myObj)
        } catch (e: Exception) {
            println(e)
            //e.printStackTrace(System.out)
            throw e
        }
    }
}
```
