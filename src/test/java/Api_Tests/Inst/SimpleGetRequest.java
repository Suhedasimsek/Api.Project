package Api_Tests.Inst;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String petStoreUrl="https://petstore.swagger.io/v2";
    @Test
    public void Test1(){
        Response response = RestAssured.get(petStoreUrl+"/store/inventory");
        System.out.println("response.statusCode() = " + response.statusCode());//ststus kod yazdır
        response.prettyPrint();//body yazdır

    }

    @Test
    public void test2() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreUrl + "/store/inventory");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();
        Assert.assertEquals(response.contentType(),"application/json");

    }

    @Test
    public void test3() {
        RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreUrl + "/store/inventory")
                .then()
                .assertThat().statusCode(200)
                .and()
                .contentType("application/json");

    }

    @Test
    public void test4() {
        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when().get(petStoreUrl + "/store/inventory");
        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.body().asString() = " + response.body().asString());
        Assert.assertTrue(response.body().asString().contains("2"));//2 value idi key "guarding" da olurdu

    }
}
