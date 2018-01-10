package calcolatrice.differenza;

import calcolatrice.somma.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

public class PutTest {
    
    private WebTarget calc;
    
    public PutTest() { 
        // Creazione del client e connessione al servizio
        Client cli = ClientBuilder.newClient();
        calc = cli.target("http://localhost:50003/calcolatrice/differenza");
    }    
    
    @Test
    public void testPutNotAllowed() {
        Response rPut = calc.request().put(Entity.entity("", MediaType.TEXT_PLAIN));
        assertEquals(405, rPut.getStatus());
    }
}
