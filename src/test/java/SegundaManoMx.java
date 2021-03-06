import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Base64;
import static org.junit.Assert.*;
import static io.restassured.RestAssured.*;

public class SegundaManoMx {

    @Test
    public void Get_Token(){
        String email ="lp.lulu77@gmail.com";
        String pass ="PugB3rt0...!_";
        String ToEncode = email + ":" +pass;
        String Basic_encoded = Base64.getEncoder().encodeToString(ToEncode.getBytes());
        RestAssured.baseURI = String.format("https://webapi.segundamano.mx/nga/api/v1.1/private/accounts");
        Response response =given().queryParam("lang","es").log().all()
                .header("Authorization", "Basic "+ Basic_encoded)
                .post();
        String body = response.getBody().asString();
        System.out.println("Response body: "+response.getBody().asString());
        System.out.println("Validar estatus sea 200: " + response.getStatusCode());
        assertEquals(200,response.getStatusCode());
        System.out.println("Validar body contiene Jalisco: " );
        assertTrue(body.contains("Jalisco"));
        System.out.println("Validar tiempo respuesta sea menor 200 :" + response.getTime());
        assertEquals(200,response.getTime());
        assertNotNull(body);
    }

    @Test
    public void VerCategorias(){
        RestAssured.baseURI = String.format("https://webapi.segundamano.mx/nga/api/v1/public/categories/insert?lang=es");
        Response response = given().log().all().header("Accept","application/json").get();
        //Aquí se obtiene como la variable de jsonData como en postman
        String body = response.getBody().asString();
        System.out.println("Validar estatus sea 200: " + response.getStatusCode());
        assertEquals(200, response.getStatusCode());
        System.out.println("Validar body contenga categorías: " +response.getBody().asString());
        assertTrue(body.contains("categories"));
        //System.out.println("Validar application/json Header Accept esta presente: " + response.getHeader("application/json"));
        //assertEquals("application/json",response.getHeader("application/json"));
        //System.out.println("Validar ContentType esta presente : " + response.contentType());
        //assertEquals("Content-Type", response.contentType());
        assertNotNull(body);
    }



}
