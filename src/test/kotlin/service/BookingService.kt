package service

import test.BookingTest
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.RestAssured.`when`
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue

class BookingService {
    private val BASE_URL = "https://restful-booker.herokuapp.com"

    fun readJsonFromResources(fileName: String): String {
        return object {}.javaClass.classLoader
            .getResource(fileName)
            ?.readText()
            ?: throw IllegalArgumentException("Arquivo não encontrado: $fileName")
    }

    fun GetBooking() {
        RestAssured.baseURI = BASE_URL
        given()
            .header("Accept", "*/*")
            .`when`()
            .get("/booking/")
            .then()
            .statusCode(200)
            .log().all()
    }

    fun getBookingWithId(id: String) {
        RestAssured.baseURI = BASE_URL
        `when`()
            .get("/booking/${id}")
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

    fun postBooking() {
        RestAssured.baseURI = BASE_URL
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