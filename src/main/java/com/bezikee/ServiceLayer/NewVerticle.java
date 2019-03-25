package com.bezikee.ServiceLayer;


import com.bezikee.App;
import com.bezikee.Common.DateOps;
import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.New.NewBean;
import com.bezikee.DomainLogicLayer.New.*;
import com.bezikee.DomainLogicLayer.CommandFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class NewVerticle extends AbstractVerticle {

    @Override
    public void start() {
        LoggerOps.debug("Starting new Verticle.");

       

        App.router.route().handler(BodyHandler.create());
        App.router.get("/new/:id").handler(this::handleGet);
        App.router.put("/new").handler(this::handleCreate);
        App.router.get("/new").handler(this::handleGetAll);
        App.router.post("/new").handler(this::handleUpdate);
        App.router.delete("/new/:id").handler(this::handleDelete);

        vertx.createHttpServer().requestHandler(App.router::accept).listen(8080);
    }

    private void handleGet(RoutingContext routingContext) {
        LoggerOps.debug("STARTING - Handling Get New.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersGet(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            int id = Integer.parseInt(routingContext.request().getParam("id"));

            GetNewCommand cmd = (GetNewCommand) CommandFactory.instantiateGetNew(id);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Get New.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }
    }

    private boolean validateParametersGet(HttpServerRequest request) {
        LoggerOps.debug("Validating Parameters Get New.");
        //False = no errors
        //True = error

        if ( (request.getParam("id") == null) || !(request.getParam("id").matches("[0-9]+$"))) {
            LoggerOps.error("Wrong Id: " + request.getParam("id"));
            return true;
        }

        return false;
    }

    private void handleCreate(RoutingContext routingContext) {
        LoggerOps.debug("Handling Create New.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersCreate(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            NewBean bean = new NewBean(
                    routingContext.request().getParam("title"),
                    routingContext.request().getParam("content"),
                    routingContext.request().getParam("image"),
                    routingContext.request().getParam("video"),
                    routingContext.request().getParam("date")
            );

            CreateNewCommand cmd = (CreateNewCommand) CommandFactory.instantiateCreateNew(bean);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Create New.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersCreate(HttpServerRequest request) {
        LoggerOps.debug("Validating Parameters Create New.");
        //False = no errors
        //True = error

        if ( (request.getParam("title") == null) || !(request.getParam("title").matches("[a-zA-Z ]+$"))) {
            LoggerOps.error("Wrong title: " + request.getParam("title"));
            return true;
        }

        return false;
    }

    private void handleGetAll(RoutingContext routingContext) {
        LoggerOps.debug("STARTING - Handling Get All New.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        GetAllNewCommand cmd = (GetAllNewCommand) CommandFactory.instantiateGetAllNew();

        cmd.execute();

        LoggerOps.debug("ENDING - Responding Get All New.");
        response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());

    }

    private void handleUpdate(RoutingContext routingContext) {
        LoggerOps.debug("Handling Update New.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersUpdate(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            NewBean bean = new NewBean(
                    Integer.parseInt(routingContext.request().getParam("id")),
                    routingContext.request().getParam("title"),
                    routingContext.request().getParam("content"),
                    routingContext.request().getParam("image"),
                    routingContext.request().getParam("video"),
                    routingContext.request().getParam("date")
            );


            UpdateNewCommand cmd = (UpdateNewCommand) CommandFactory.instantiateUpdateNew(bean);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Update New.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersUpdate(HttpServerRequest request) {

        return validateParametersGet(request) || validateParametersCreate(request);
    }

    private void handleDelete(RoutingContext routingContext) {
        LoggerOps.debug("Handling Delete New.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersDelete(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            int id = Integer.parseInt(routingContext.request().getParam("id"));


            DeleteNewCommand cmd = (DeleteNewCommand) CommandFactory.instantiateDeleteNew(id);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Delete New.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersDelete(HttpServerRequest request) {

        return validateParametersGet(request);
    }

}