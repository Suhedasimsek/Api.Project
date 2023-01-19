package Day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class getAllUser {
    String exlabUrl = "https://www.krafttechexlab.com/sw/api/v1";

    @Test //Bütün kullanıcıları getir
    public void Test1() {
        Response response =  RestAssured.given()//RestAssures import kısmında statik yapıp sonuna * koyarsan
                // sayfada artık kullanmana gerek kalmadan yazabilirsim
                .accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .and()//optional
                .when()// optional ama kullanımı yaygın
                .get(exlabUrl+"/allusers/alluser");//and point olduğu için enson
        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();


    }
}
