

import io.restassured.RestAssured.*
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.*
import kotlin.test.Test


class TesteBasico {

    fun readJsonFromResources(fileName: String): String {
        return object {}.javaClass.classLoader
            .getResource(fileName)
            ?.readText()
            ?: throw IllegalArgumentException("Arquivo não encontrado: $fileName")
    }

    @Test
    fun testGetBooking() {
        baseURI = "https://restful-booker.herokuapp.com"

        given()
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
            .get("/booking/124")
        .then()
            .statusCode(200)
            .body("firstname", equalTo("Josh"))
            .body("lastname", equalTo("Allen"))
            .body("totalprice", equalTo(111))
            .body("depositpaid", `is`(true))
            .body("bookingdates.checkin", equalTo("2018-01-01"))
            .body("bookingdates.checkout", equalTo("2019-01-01"))
            .body("additionalneeds", equalTo("superb owls"))
    }

    @Test
    fun testPostBooking() {
        baseURI = "https://restful-booker.herokuapp.com"

        given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(readJsonFromResources("payloads/booking.json"))
        .`when`()
            .post("/booking")
        .then()
            .statusCode(200)
            .body("bookingid", notNullValue())
            .body("bookingid", greaterThan(0))
            .body("booking.firstname", equalTo("Jim"))
            .body("booking.lastname", equalTo("Test"))
            .body("booking.totalprice", equalTo(111))
            .body("booking.depositpaid", `is`(true))
            .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
            .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
            .body("booking.additionalneeds", equalTo("Breakfast"))
    }
}