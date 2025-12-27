# 📌 Rest Assured API Automation (Kotlin)

This project is an **API test automation framework** built with **Kotlin**, **Rest Assured**, **JUnit 5**, and **Allure Reports**, with automated execution via **GitHub Actions** and report publication using **GitHub Pages**.

---

## 🧰 Technologies Used

- Kotlin  
- Rest Assured  
- JUnit 5  
- Hamcrest  
- Maven  
- Allure Reports  
- GitHub Actions  
- GitHub Pages  

---

## 🗂 Project Structure


```text
src
├── main
│   └── kotlin
│       ├── config
│       │   └── BaseApi.kt
│       └── service
│           ├── BaseService.kt
│           └── BookingService.kt
│
├── test
│   └── kotlin
│       └── test
│           └── BookingTest.kt
│
└── test
    └── resources
        └── payloads
            └── booking.json
```


---

## ⚙️ Architecture and Design Patterns

The project is structured following **clean architecture principles** and **design patterns**, focusing on reusability, readability, and maintainability.

### 🔹 Singleton (BaseApi)

The API configuration is centralized using a **Singleton**, ensuring a single base URI configuration shared across all tests.

```kotlin
object BaseApi {
    init {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com"
    }
}
```
### 🔹 Base Class / Template Pattern (BaseService)

A base class responsible for initializing shared configurations for all service classes, avoiding duplicated setup logic.
```kotlin
open class BaseService {
    init {
        BaseApi
    }
}
```
### 🔹 Service Layer (BookingService)

A service layer that encapsulates all HTTP interactions with the Booking API, keeping test logic separate from API communication logic.


Available methods:

- `GetBooking()` → GET `/booking`
- `getBookingWithId(id)` → GET `/booking/{id}`
- `postBooking()` → POST `/booking`


```kotlin
fun postBooking(): Response =
    given()
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .body(Utils.readJsonFromResources("payloads/booking.json"))
        .`when`()
        .post("/booking")
```
### 🔹 Utils

Utility class responsible for reading external JSON files from src/test/resources, keeping tests clean and reusable.
```kotlin
fun readJsonFromResources(fileName: String): String {
    return object {}.javaClass.classLoader
        .getResource(fileName)
        ?.readText()
        ?: throw IllegalArgumentException("File not found: $fileName")
}
```
### 🧪 Automated Tests

Tests are located in:
```kotlin
src/test/kotlin/test
```
Example test:

```kotlin
@Test
fun testPostBooking() {
    bookingService.postBooking()
        .then()
        .statusCode(200)
        .body("bookingid", notNullValue())
        .body("booking.firstname", equalTo("Jim"))
}
```
✔ Validations Performed

- HTTP status codes
- JSON structure and content
- Mandatory response fields
- Basic business rules

### ▶️ Running Tests Locally

Clean and run tests (recommended)
```bash
mvn clean test
```

### 📊 Allure Reports
```bash
mvn allure:report
```
View report locally
```bash
mvn allure:serve
```

### 🌍 Allure Report via CI (GitHub Pages)

The project includes GitHub Actions CI, which:

- Runs tests automatically
- Generates the Allure report
- Publishes the report to GitHub Pages


🔗 **Allure Report:** [Click here](https://ronimacedo.github.io/rest-assured-automation/)



The report is automatically updated on every push to the master branch.

### ⚙️ CI Pipeline (GitHub Actions)

Main workflow steps:

```yaml
- name: Run tests
  run: mvn clean test

- name: Generate Allure report
  run: mvn allure:report

- name: Deploy to GitHub Pages
  uses: peaceiris/actions-gh-pages@v4
```

### 📌 Best Practices Applied

- Clear separation of responsibilities (config / service / test)
- Usage of design patterns (Singleton, Service Layer, Base Class)
- Code reuse
- Externalized payloads
- Independent and maintainable tests
- Automated reporting
- CI/CD integration
- Generated files excluded from version control
