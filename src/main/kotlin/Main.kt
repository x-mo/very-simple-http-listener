import org.json.JSONException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import org.json.JSONObject

const val URL = "https://mocki.io/v1/7c599c30-e83d-4f9e-affa-6f5734573bd0"

fun main() {

    val client = HttpClient.newBuilder().build()
    val request = HttpRequest.newBuilder()
        .uri(URI.create(URL))
        .build()

    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    if (response.statusCode() != 200) return

    try {
        val jsonString = response.body().toString()
        val jsonObject = JSONObject(jsonString)
        val id = jsonObject["Id"]
        val msg = jsonObject["Msg"]

        println("ID\t|\tMessage")
        println("$id\t|\t$msg")

    } catch (e: JSONException) {
        println("Error: ${e.message}")
    }
}