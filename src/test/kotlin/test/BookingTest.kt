package test

import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import service.BookingService
import kotlin.test.Test

class BookingTest {
    private val bookingService = BookingService()

    @Test
    fun testGetBooking() {
        bookingService.GetBooking()
            .then()
            .statusCode(200)
            .log().all()
    }

    @Test
    fun testGetBookingWithId() {
        bookingService.getBookingWithId("124")
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

    @Test
    fun testPostBooking() {
        bookingService.postBooking()
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