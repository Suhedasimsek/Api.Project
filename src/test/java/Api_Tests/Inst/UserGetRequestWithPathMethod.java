package Api_Tests.Inst;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class UserGetRequestWithPathMethod {
    @BeforeClass
    public void beforeClass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void test1() {
        Response response=given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when().log().all()//request bodynin body dahil ayrıntılarını veriyor
                .get("/allusers/getbyid/{id}");

        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        System.out.println("response.path(\"id\").toString() = " + response.path("job").toString());

        int id=response.path("id[0]");
        String name=response.path("name[0]");
        String job=response.path("job[0]");

        Assert.assertEquals(id,111);
        Assert.assertEquals(name,"Thomas Eduson");
        Assert.assertEquals(job,"Developer");
    }
}
