
import io.restassured.RestAssured.*
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.`is`
import kotlin.test.Test

class TesteBasico {
    @Test
    fun testGetBooking() {
        // Configura a URL base para as requisições da API
        baseURI = "https://restful-booker.herokuapp.com"

        // Configura e executa a requisição GET para o endpoint "/booking/"
        given() // Define as configurações da requisição (headers, parâmetros, etc.)
            .header("Accept", "*/*")
        .`when`()
            .get("/booking/")
        .then()
            .statusCode(200)
            .log().all()
    }

    @Test
    fun testGetBookingWithId() {
        baseURI = "https://restful-booker.herokuapp.com"

        `when`()
            .get("/booking/102")
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Josh"))
            .body("lastname", equalTo("Allen"))
            .body("totalprice", equalTo(111))
            .body("depositpaid", `is`(true))
            .body("bookingdates.checkin", equalTo("2018-01-01"))
            .body("bookingdates.checkout", equalTo("2019-01-01"))
            .body("additionalneeds", equalTo("super bowls"))
    }
}