package endpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;



public class BookingEndpoint {
    private final String BASE_URL = "https://restful-booker.herokuapp.com";
    private Response response;  // Variável para armazenar a resposta



    public  String lerJson (String caminhoArquivo) throws IOException {
        return  new String(Files.readAllBytes(Paths.get(caminhoArquivo)));

    }


    public Response getAllBookings(){

        RestAssured.baseURI = BASE_URL;

        response = given()
                .header("Accept", "*/*")
                .when()
                .get("/booking/")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();  // Captura a resposta
        return response;
    }

    public void testWithId(){

        RestAssured.baseURI = BASE_URL;

        given()

                .header("Accept", "application/json")
                .when()
                .get("/booking/69")
                .then()
                .statusCode(200)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Smith"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", is(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));


    }

    public int  cadastrarReserva() throws IOException {

        String jsonBody = lerJson("src/test/resources/payloads/reserva.json");

        RestAssured.baseURI = BASE_URL;

        int id_reserva =  given()
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Cadastrada"))
                .body("booking.lastname", equalTo("Brown"))
                .body("booking.totalprice", equalTo(111))
                .body("booking.depositpaid", is(true))
                .body("booking.bookingdates.checkin", equalTo("2018-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2019-01-01"))
                .body("booking.additionalneeds", equalTo("Breakfast"))
                .extract()
                .path("bookingid");

        System.out.println("ID da reserva criada: " + id_reserva);

        return id_reserva;





    }

    public void alterarReserva(String token) throws IOException {

        String jsonBody = lerJson("src/test/resources/payloads/alterar_reserva.json");

        RestAssured.baseURI = BASE_URL;

        given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token) // usa o token recebido corretamente
                .body(jsonBody)
                .when()
                .put("/booking/785")
                .then()
                .statusCode(200)
                .log().all();

    }

    public void alterarReserva2(String token, int bookingId) throws IOException {

        String jsonBody = lerJson("src/test/resources/payloads/alterar_reserva.json");

        RestAssured.baseURI = BASE_URL;

        given()
                .header("Content-Type", "application/json")
                .header("Cookie", "token=" + token) // usa o token recebido corretamente
                .body(jsonBody)
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .log().all();

    }

    // Método para obter o status code da resposta
    public int getStatusCode() {
        if (response != null) {
            return response.getStatusCode();  // Retorna o código de status da resposta
        } else {
            throw new IllegalStateException("A resposta não foi inicializada corretamente");
        }
    }


}
