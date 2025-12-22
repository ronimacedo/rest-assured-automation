package service


import io.restassured.RestAssured.given
import io.restassured.RestAssured.`when`
import io.restassured.response.Response
import utils.Utils

class BookingService: BaseService() {


    fun GetBooking(): Response =
        given()
            .header("Accept", "*/*")
            .`when`()
            .get("/booking")


    fun getBookingWithId(id: String) : Response =
        `when`()
            .get("/booking/${id}")


    fun postBooking() : Response =
        given()
            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .body(Utils.readJsonFromResources("payloads/booking.json"))
            .`when`()
            .post("/booking")

}