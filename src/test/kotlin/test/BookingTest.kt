package test

import service.BookingService
import kotlin.test.Test

class BookingTest {
    private val bookingService = BookingService()

    @Test
    fun testGetBooking() {
        bookingService.GetBooking()
    }

    @Test
    fun testGetBookingWithId() {
        bookingService.getBookingWithId("124")
    }

    @Test
    fun testPostBooking() {
        bookingService.postBooking()
    }
}