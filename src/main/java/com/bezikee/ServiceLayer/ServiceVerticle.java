package com.bezikee.ServiceLayer;


import com.bezikee.App;
import com.bezikee.Common.DateOps;
import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Service.ServiceBean;
import com.bezikee.DomainLogicLayer.Payment.GetPaymentByCategoryCommand;
import com.bezikee.DomainLogicLayer.Service.*;
import com.bezikee.DomainLogicLayer.CommandFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ServiceVerticle extends AbstractVerticle {

    @Override
    public void start() {
        LoggerOps.debug("Starting service Verticle.");

        

        App.router.route().handler(BodyHandler.create());
        App.router.get("/service/:id").handler(this::handleGet);
        App.router.put("/service").handler(this::handleCreate);
        App.router.get("/service").handler(this::handleGetAll);
        App.router.post("/service").handler(this::handleUpdate);
        App.router.delete("/service/:id").handler(this::handleDelete);
        App.router.get("/serviceByCategory/:category").handler(this::handleGetByCategory);

        vertx.createHttpServer().requestHandler(App.router::accept).listen(8080);
    }

    private void handleGet(RoutingContext routingContext) {
        LoggerOps.debug("STARTING - Handling Get Service.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersGet(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            int id = Integer.parseInt(routingContext.request().getParam("id"));

            GetServiceCommand cmd = (GetServiceCommand) CommandFactory.instantiateGetService(id);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Get Service.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }
    }

    private boolean validateParametersGet(HttpServerRequest request) {
        LoggerOps.debug("Validating Parameters Get Service.");
        //False = no errors
        //True = error

        if ( (request.getParam("id") == null) || !(request.getParam("id").matches("[0-9]+$"))) {
            LoggerOps.error("Wrong Id: " + request.getParam("id"));
            return true;
        }

        return false;
    }

    private void handleCreate(RoutingContext routingContext) {
        LoggerOps.debug("Handling Create Service.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersCreate(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            ServiceBean bean = new ServiceBean(
                    routingContext.request().getParam("name"),
                    routingContext.request().getParam("currency"),
                    Float.parseFloat(routingContext.request().getParam("basePrice")),
                    routingContext.request().getParam("category")
            );

            CreateServiceCommand cmd = (CreateServiceCommand) CommandFactory.instantiateCreateService(bean);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Create Service.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersCreate(HttpServerRequest request) {
        LoggerOps.debug("Validating Parameters Create Service.");
        //False = no errors
        //True = error


        if ( (request.getParam("name") == null) || !(request.getParam("name").matches("[a-zA-Z ]+$"))) {
            LoggerOps.error("Wrong name: " + request.getParam("name"));
            return true;
        }

        if ( (request.getParam("currency") == null) || !(request.getParam("currency").matches("[a-zA-Z ]+$"))) {
            LoggerOps.error("Wrong currency: " + request.getParam("currency"));
            return true;
        }

        if ((request.getParam("basePrice") == null) || !(request.getParam("basePrice").matches("[0-9.,]+$"))) {
            LoggerOps.error("Wrong basePrice: " + request.getParam("basePrice"));
            return true;
        }


        return false;
    }

    private void handleGetAll(RoutingContext routingContext) {
        LoggerOps.debug("STARTING - Handling Get All Service.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        GetAllServiceCommand cmd = (GetAllServiceCommand) CommandFactory.instantiateGetAllService();

        cmd.execute();

        LoggerOps.debug("ENDING - Responding Get All Service.");
        response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());

    }

    private void handleUpdate(RoutingContext routingContext) {
        LoggerOps.debug("Handling Update Service.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersUpdate(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            ServiceBean bean = new ServiceBean(
                    Integer.parseInt(routingContext.request().getParam("id")),
                    routingContext.request().getParam("name"),
                    routingContext.request().getParam("currency"),
                    Float.parseFloat(routingContext.request().getParam("basePrice")),
                    routingContext.request().getParam("category")
            );


            UpdateServiceCommand cmd = (UpdateServiceCommand) CommandFactory.instantiateUpdateService(bean);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Update Service.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersUpdate(HttpServerRequest request) {

        return validateParametersGet(request) || validateParametersCreate(request);
    }

    private void handleDelete(RoutingContext routingContext) {
        LoggerOps.debug("Handling Delete Service.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersDelete(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            int id = Integer.parseInt(routingContext.request().getParam("id"));


            DeleteServiceCommand cmd = (DeleteServiceCommand) CommandFactory.instantiateDeleteService(id);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Delete Service.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersDelete(HttpServerRequest request) {

        return validateParametersGet(request);
    }

    private void handleGetByCategory(RoutingContext routingContext) {
        LoggerOps.debug("Handling Get Payment by Category.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersGetByCategory(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            String category= routingContext.request().getParam("category");


            GetServiceByCategoryCommand cmd = (GetServiceByCategoryCommand) CommandFactory.instantiateGetServiceByCategory(category);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Get Payment by Category.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }
    }

    private boolean validateParametersGetByCategory(HttpServerRequest request) {

        if ( (request.getParam("category") == null) || !(request.getParam("category").matches("[a-zA-Z ]+$"))) {
            LoggerOps.error("Wrong category: " + request.getParam("name"));
            return true;
        }

        return false;
    }

}