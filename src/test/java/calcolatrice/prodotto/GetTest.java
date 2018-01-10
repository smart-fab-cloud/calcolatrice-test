package calcolatrice.prodotto;

import calcolatrice.differenza.*;
import calcolatrice.somma.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import jdk.net.SocketFlow;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

public class GetTest {
    
    private WebTarget calc;
    private int n;
    private int m;
    
    public GetTest() { 
        // Creazione del client e connessione al servizio
        Client cli = ClientBuilder.newClient();
        calc = cli.target("http://localhost:50003/calcolatrice/prodotto");
        // Inizializzazione variabili test
        n = 10;
        m = 3;
    }    
    
    @Test
    public void testGetOk() throws ParseException {
        Response rGet = calc.queryParam("n", n)
                            .queryParam("m", m)
                            .request()
                            .get();
        
        // Verifica che la risposta sia "200 OK"
        assertEquals(Status.OK.getStatusCode(), rGet.getStatus());
        // Verifica che quanto ritornato sia quanto atteso
        JSONParser p = new JSONParser();
        JSONObject risp = (JSONObject) p.parse(rGet.readEntity(String.class));
        int rN = ((Long) risp.get("n")).intValue(); 
        assertEquals(n, rN);
        int rM = ((Long) risp.get("m")).intValue(); 
        assertEquals(m, rM);
        int rProd = ((Long) risp.get("prodotto")).intValue();
        assertEquals(n*m, rProd);
        
    }
    
    @Test
    public void testGetBadRequest() {
        // Richiesta senza m
        Response rGet1 = calc.queryParam("n", n)
                            .request()
                            .get();
        // Richiesta senza n
        Response rGet2 = calc.queryParam("m", m)
                            .request()
                            .get();
        
        // Verifica che la risposta sia "400 Bad Request"
        assertEquals(Status.BAD_REQUEST.getStatusCode(), rGet1.getStatus());
        assertEquals(Status.BAD_REQUEST.getStatusCode(), rGet2.getStatus());
    }
}
