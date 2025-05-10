package endpoints;

import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class LoginEndpoint {

    private String token;

    public  String lerJson (String caminhoArquivo) throws IOException {
        return  new String(Files.readAllBytes(Paths.get(caminhoArquivo)));

    }

    private final String BASE_URL = "https://restful-booker.herokuapp.com/";


    public String getToken() throws IOException {

        String jsonBody = lerJson("src/test/resources/payloads/login.json");

        RestAssured.baseURI = BASE_URL;

        token =   given()
                .header("Content-Type", "application/json") // <-- adicionado
                .header("Accept", "*/*")
                .body(jsonBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .path("token");

        return token;
    }

    }



