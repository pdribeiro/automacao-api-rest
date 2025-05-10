package testesBase;

import endpoints.BookingEndpoint;
import endpoints.LoginEndpoint;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TesteBookingPGO {

    BookingEndpoint bookingEndpoint = new BookingEndpoint();
    LoginEndpoint loginEndpoint = new LoginEndpoint();

    @Test
    public void getAllBookings(){

        bookingEndpoint.getAllBookings();
    }

    @Test
    public void testWithId(){

        bookingEndpoint.testWithId();

    }

    @Test
    public void cadastrarReserva() throws IOException {

        bookingEndpoint.cadastrarReserva();
    }

    @Test
    public void getToken() throws IOException {
        loginEndpoint.getToken();
    }

    @Test
    public void alterarReserva() throws IOException {

        String token = loginEndpoint.getToken();
        System.out.println("Token recebido: " + token);
        bookingEndpoint.alterarReserva(token);
    }

    @Test
    public void criarEAlterarReserva() throws IOException {

        String token = loginEndpoint.getToken();
        System.out.println("Token recebido: " + token);

        int bookingId = bookingEndpoint.cadastrarReserva();
        bookingEndpoint.alterarReserva2(token,bookingId);




    }
}
