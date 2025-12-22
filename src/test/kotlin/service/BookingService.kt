package service


import io.restassured.RestAssured.given
import io.restassured.RestAssured.`when`
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import utils.Utils

class BookingService: BaseService() {
    private val utils = Utils()

    fun GetBooking() {
        given()
            .header("Accept", "*/*")
            .`when`()
            .get("/booking/")
            .then()
            .statusCode(200)
            .log().all()
    }

    fun getBookingWithId(id: String) {
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
        given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(utils.readJsonFromResources("payloads/booking.json"))
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