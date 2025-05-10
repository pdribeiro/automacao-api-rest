package steps;

import endpoints.BookingEndpoint;
import endpoints.LoginEndpoint;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingSteps {

    BookingEndpoint bookingEndpoint = new BookingEndpoint();
    LoginEndpoint loginEndpoint = new LoginEndpoint();
    String token;
    int bookingId;

    @Given("que eu tenha um token válido")
    public void obterToken() throws IOException {
        token = loginEndpoint.getToken();
        System.out.println("Token gerado: " + token);
    }

    @When("eu crio uma nova reserva")
    public void criarReserva() throws IOException {
        bookingId = bookingEndpoint.cadastrarReserva();
        System.out.println("Reserva criada: ID = " + bookingId);
    }

    @And("eu altero essa reserva")
    public void alterarReserva() throws IOException {
        bookingEndpoint.alterarReserva2(token, bookingId);
    }

    @Then("a reserva deve ser atualizada com sucesso")
    public void verificarAtualizacao() {
        System.out.println("Reserva alterada com sucesso!");
    }

    // Cenário 2: Obter todas as reservas
    @Given("que a API de reservas está disponível")
    public void apiDeReservasDisponivel() {
        // Realizando uma requisição GET para verificar se a API está ativa
        try {
            Response response = bookingEndpoint.getAllBookings(); // Chama o método que retorna as reservas
            int statusCode = response.getStatusCode(); // Obtém o status code da resposta

            // Verifica se o status code é 200, o que significa que a API está funcionando
            if (statusCode == 200) {
                System.out.println("API de reservas está disponível.");
            } else {
                throw new IllegalStateException("A API de reservas retornou um status inesperado: " + statusCode);
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao conectar com a API de reservas: " + e.getMessage(), e);
        }
    }


    @When("eu solicito todas as reservas")
    public void solicitarTodasAsReservas() {
        bookingEndpoint.getAllBookings();
    }

    @Then("a resposta deve ter status code 200")
    public void verificarStatusCode200() {
        assertEquals(200, bookingEndpoint.getStatusCode());
    }

}
