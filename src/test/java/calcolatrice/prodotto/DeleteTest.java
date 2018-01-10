package calcolatrice.prodotto;

import calcolatrice.differenza.*;
import calcolatrice.somma.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

public class DeleteTest {
    
    private WebTarget calc;
    
    public DeleteTest() { 
        // Creazione del client e connessione al servizio
        Client cli = ClientBuilder.newClient();
        calc = cli.target("http://localhost:50003/calcolatrice/prodotto");
    }    
    
    @Test
    public void testDeleteNotAllowed() {
        Response rDelete = calc.request().delete();
        assertEquals(405, rDelete.getStatus());
    }
}
