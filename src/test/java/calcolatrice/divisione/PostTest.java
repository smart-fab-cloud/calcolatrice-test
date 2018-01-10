package calcolatrice.divisione;

import calcolatrice.differenza.*;
import calcolatrice.somma.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Test;
import static org.junit.Assert.*;

public class PostTest {
    
    private WebTarget calc;
    
    public PostTest() { 
        // Creazione del client e connessione al servizio
        Client cli = ClientBuilder.newClient();
        calc = cli.target("http://localhost:50003/calcolatrice/divisione");
    }    
    
    @Test
    public void testPostNotAllowed() {
        Response rPost = calc.request().post(Entity.entity("", MediaType.TEXT_PLAIN));
        assertEquals(405, rPost.getStatus());
    }
}
