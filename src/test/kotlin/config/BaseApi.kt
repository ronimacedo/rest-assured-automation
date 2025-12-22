package config

import io.restassured.RestAssured

object BaseApi {
    init {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com"
    }
}