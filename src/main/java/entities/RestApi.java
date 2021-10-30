package entities;

import com.google.gson.Gson;
import config.BaseConfig;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import java.util.*;

public class RestApi extends AbstractVerticle {
    public RestApi() {
        super();
    }

    @Override
    public void start() {
        Router router = Router.router(vertx);

        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("x-request-with");
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");
        allowedHeaders.add("X-PINGARUNER");

        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.PUT);

        router.route().handler(CorsHandler.create("*").allowedHeaders(allowedHeaders).allowedMethods(allowedMethods));

        router.route().handler(BodyHandler.create());

        router.post("/insert").handler(this::handleInsert);
        router.get("/query").handler(this::getRecord);
        router.post("/modify").handler(this::handleModify);


        vertx.createHttpServer().requestHandler(router).listen(BaseConfig.getInstance().port);
    }

    private void handleInsert(RoutingContext routingContext) {
        System.out.println("enter handle insert");
        JsonObject body = routingContext.getBodyAsJson();

        DonateInfo info = new Gson().fromJson(String.valueOf(body), DonateInfo.class);
        System.out.println(new Gson().toJson(info));

        // code here

        HttpServerResponse response = routingContext.response();
        response.setStatusCode(200).end("Success");
    }

    private void getRecord(RoutingContext routingContext) {
        String result = "";
        // code here
        HttpServerResponse response = routingContext.response();
        response.setStatusCode(200).end(result);
    }

    private void handleModify(RoutingContext routingContext) {
        System.out.println("enter handle insert");
        io.vertx.core.json.JsonObject body = routingContext.getBodyAsJson();

        // code here

        HttpServerResponse response = routingContext.response();
        response.setStatusCode(200).end("Success");
    }
}
