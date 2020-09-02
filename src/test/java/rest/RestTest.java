package rest;

import io.restassured.response.Response;
import org.apache.log4j.Logger;

import java.util.HashMap;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

public class RestTest {

    static Logger LOG =  Logger.getLogger(RestTest.class);

    public static Response get(String uri){
        LOG.info("Getting uri: "+uri);
        Response res = when().get(uri);
        LOG.info("Response headers: "+ res.headers().toString());
        res.getBody().prettyPrint();
        return res;
    }

    public static void validateHeaders(HashMap<String, String> data, Response res){
        for (String s: data.keySet()){
            res.then().header(s, equalTo(data.get(s)));
        }
    }

    public static void validateBody(HashMap<String, Object> data, Response res){
        for (String s: data.keySet()){
            res.then().body(s, equalTo(data.get(s)));
        }
    }
}
