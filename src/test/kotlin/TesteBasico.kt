
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import kotlin.test.Test

class TesteBasico {
    @Test
    fun testGetBooking() {
        // Configura a URL base para as requisições da API
        RestAssured.baseURI = "https://restful-booker.herokuapp.com"

        // Configura e executa a requisição GET para o endpoint "/booking/"
        given() // Define as configurações da requisição (headers, parâmetros, etc.)
            .header("Accept", "*/*")
        .`when`()
            .get("/booking/")
        .then()
            .statusCode(200)
            .log().all()
    }
}