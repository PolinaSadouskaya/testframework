package rest.jsonplaceholder.posts;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import rest.RestTest;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class SingleGetTest extends RestTest {
    public String resource = "https://jsonplaceholder.typicode.com/";
    SoftAssert sa = new SoftAssert();

    @Test(testName = "get resource data by id (1)", enabled = true)
    public void getById(){
        Response res = get(resource+"posts/1");
        res.then().statusCode(200);
        res.then().contentType("application/json; charset=utf-8");
        res.then().header("Connection", equalTo("keep-alive"));
        res.then().body("userId", equalTo(1));
        res.then().body("id", equalTo(1));
        res.then().body("body", notNullValue());
        res.then().body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"));
        res.prettyPrint();
    }

    /** The same test as getById, but another realization */
    @Test(testName = "get resource by id (2)", enabled = true)
    public void getById2(){
        Response res = get(resource+"posts/2");
        HashMap<String, String> headersExpected = new HashMap<>();
        headersExpected.put("Content-Type", "application/json; charset=utf-8");
        headersExpected.put("Connection", "keep-alive");
        HashMap<String, Object> bodyExpected = new HashMap<>();
        bodyExpected.put("id", 2);
        bodyExpected.put("title", "qui est esse");
        res.then().statusCode(200);
        validateHeaders(headersExpected, res);
        validateBody(bodyExpected, res);
    }

    @Test(testName = "get resource with invalid id")
    public void getByInvalidId(){
        Response res = get(resource+"posts/-1");
        res.then().statusCode(404).body("$", Matchers.anEmptyMap());
    }

    @Test(testName = "get resource with not existing id")
    public void getByNotExistingId(){
        Response res = get(resource+"posts/123456789");
        res.then().statusCode(404).body("$", Matchers.anEmptyMap());
    }
}
