package Api_Tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class POST_NewUserAndVerify {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUserAndVerify() {
        String name="Seyda4";
        String email="seyda4@krafttechexlab.com";
        String password="seyda4";
        String about="About Me";
        String terms="5";

        Map<String, Object> requestMap = new HashMap<>();

        requestMap.put("name", name);
        requestMap.put("email", email);
        requestMap.put("password", password);
        requestMap.put("about", about);
        requestMap.put("terms",terms);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap) //serialization
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
        String token=response.path("token");
       //String userID=response.path("id");

        Map<String, Object> educationBody = new HashMap<>();

        educationBody.put("school", "Sakarya");
        educationBody.put("degree", "Master");
        educationBody.put("study", "SDET");
        educationBody.put("fromdate", "2020-01-01");
        educationBody.put("todate", "2020-01-01");
        educationBody.put("current", "false");
        educationBody.put("description", "SDET");

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody) //serialization
                .queryParam("token",token)
                .when()
                .post("/education/add");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        //token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjE0Iiwic3RhcnQiOjE2NzQ3NTgyOTksImVuZHMiOjE2NzUzNjMwOTl9.-ZS2WN8NM8dZ373V3lmzN5HSkGqpCCgfpE340ZHSORBAJtR_653Fy0X-rm4Ap2l66nGaUePtuDELUoqEnFmg8w
        //verify
        double id=response.path("id");
        response=given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .pathParam("id",id)
                .when().get("/education/getbyid/{id}");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        //verify
        //System.out.println("userID = " + userID);
        System.out.println("education id = " + id);




    }
}
