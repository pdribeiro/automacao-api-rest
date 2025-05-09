import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BookingEndpoint {
    private final String BASE_URL = "https://restful-booker.herokuapp.com";




    public  String lerJson (String caminhoArquivo) throws IOException {
        return  new String(Files.readAllBytes(Paths.get(caminhoArquivo)));

    }


    public void getAllBookings(){

        RestAssured.baseURI = BASE_URL;

        given()
                .header("Accept", "*/*")
                .when()
                .get("/booking/")
                .then()
                .statusCode(200)
                .log().all();
    }

    public void testWithId(){

        RestAssured.baseURI = BASE_URL;

        given()

                .header("Accept", "application/json")
                .when()
                .get("/booking/69")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Josh"))
                .body("lastname", equalTo("Allen"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", is(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("super bowls"));


    }

    public void cadastrarReserva() throws IOException {

        String jsonBody = lerJson("src/test/resources/payloads/reserva.json");

        RestAssured.baseURI = BASE_URL;

        given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Jim"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", equalTo(111))
                .body("booking.depositpaid", is(true))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"));



    }

    public void alterarReserva(String token) throws IOException {

        String jsonBody = lerJson("src/test/resources/payloads/alterar_reserva.json");

        RestAssured.baseURI = BASE_URL;

        given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token) // usa o token recebido corretamente
                .body(jsonBody)
                .when()
                .put("/booking/1352")
                .then()
                .statusCode(200)
                .log().all();

    }


}
