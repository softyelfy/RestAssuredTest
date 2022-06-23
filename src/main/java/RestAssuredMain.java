import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestAssuredMain {
   /// public static String bookingId;
    ///public static String token;
public static String uri = "https://restful-booker.herokuapp.com";
    public static String getToken() {
        return RestAssured.given()
                .baseUri(uri)
                .body("{\"username\" : \"admin\" ,\n \"password\" : \"password123\"}")
                .log()
                .all()
                .contentType(ContentType.JSON)
                .post("auth")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("token");

    }

    public static String createBooking(String body) {
        return RestAssured.given()
                .baseUri(uri)
                .body(body)
                .log()
                .all()
                .contentType(ContentType.JSON)
                .post("booking")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .extract()
                .response()
                .jsonPath()
                .getString("bookingid");


    }

    public static void deleteBooking(String token, String bookingId) {
        RestAssured.given()
                .header("Cookie", "token=" + token)
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .delete("booking/" + bookingId)

                .then()
                .log()
                .ifValidationFails()
                .statusCode(201)
                .extract()
                .response()
                .prettyPrint();
        System.out.println("Номер заказа ( " + bookingId + " ) удален");

    }
    public static void getBooking(int statusCode, String bookingId) {
        RestAssured.given()
                .baseUri(uri)
                .contentType(ContentType.JSON)
                .get("booking/" + bookingId)
                .then()
                .log()
                .ifValidationFails()
                .statusCode(statusCode)
                .extract()
                .response()
                .prettyPrint();
        if (statusCode == 200){
            System.out.println("Номер заказа " + bookingId + " найден");
        } else {
            System.out.println("Номер заказа " + bookingId + " не найден");
        }


    }
}
