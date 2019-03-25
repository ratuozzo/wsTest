package com.bezikee;

import com.bezikee.Common.LoggerOps;
import com.bezikee.ServiceLayer.*;
import io.vertx.core.*;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.maven.MavenVerticleFactory;

import java.util.HashSet;
import java.util.Set;

public class App extends AbstractVerticle
{

    public static Vertx vertx = Vertx.vertx();
    public static Router router = Router.router(vertx);

    public void start(Future<Void> fut) {
       LoggerOps.debug("Starting vertx.");


        Set<String> allowedHeaders = new HashSet<>();
        allowedHeaders.add("Access-Control-Allow-Origin");
        allowedHeaders.add("origin");
        allowedHeaders.add("Content-Type");
        allowedHeaders.add("accept");

        Set<HttpMethod> allowedMethods = new HashSet<>();
        allowedMethods.add(HttpMethod.GET);
        allowedMethods.add(HttpMethod.POST);
        allowedMethods.add(HttpMethod.OPTIONS);
        allowedMethods.add(HttpMethod.DELETE);
        allowedMethods.add(HttpMethod.PATCH);
        allowedMethods.add(HttpMethod.PUT);

        router.route().handler(CorsHandler.create("*").allowedMethods(allowedMethods).allowedHeaders(allowedHeaders));



       DeploymentOptions options = new DeploymentOptions().setWorker(true);



        DonationVerticle donation= new DonationVerticle();
        vertx.deployVerticle(donation,options);

        CalendarVerticle calendar = new CalendarVerticle();
        vertx.deployVerticle(calendar,options);

        NewVerticle news = new NewVerticle();
        vertx.deployVerticle(news,options);

        ServiceVerticle service = new ServiceVerticle();
        vertx.deployVerticle(service,options);

        PaymentVerticle payment = new PaymentVerticle();
        vertx.deployVerticle(payment,options);

        ResourceVerticle resource = new ResourceVerticle();
        vertx.deployVerticle(resource,options);


   }

}
