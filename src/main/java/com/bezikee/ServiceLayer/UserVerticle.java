package com.bezikee.ServiceLayer;


import com.bezikee.Common.DateOps;
import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.CommandFactory;
import com.bezikee.DomainLogicLayer.User.*;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class UserVerticle extends AbstractVerticle {

    @Override
    public void start() {
        LoggerOps.debug("Starting user Verticle.");

        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/user/:userId").handler(this::handleGetUser);
        router.put("/user").handler(this::handleCreateUser);
        router.get("/user").handler(this::handleGetAllUsers);
        router.post("/user").handler(this::handleUpdateUser);
        router.delete("/user/:userId").handler(this::handleDeleteUser);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);
    }

    private void handleGetUser(RoutingContext routingContext) {
        LoggerOps.debug("STARTING - Handling Get User.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersGet(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            int userId = Integer.parseInt(routingContext.request().getParam("userId"));


            //Comando Agregar user
            GetUserCommand cmd = (GetUserCommand) CommandFactory.instantiateGetUser(userId);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Get User.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }
    }

    private boolean validateParametersGet(HttpServerRequest request) {
        LoggerOps.debug("Validating Parameters Get User.");
        //False = no errors
        //True = error

        if ( (request.getParam("userId") == null) || !(request.getParam("userId").matches("[0-9]+$"))) {
            LoggerOps.error("Wrong Id: " + request.getParam("userId"));
            return true;
        }

        return false;
    }

    private void handleCreateUser(RoutingContext routingContext) {
        LoggerOps.debug("Handling Create User.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersCreate(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            UserBean user = new UserBean(
                    routingContext.request().getParam("name"),
                    routingContext.request().getParam("lastName"),
                    routingContext.request().getParam("email"),
                    routingContext.request().getParam("username"),
                    routingContext.request().getParam("password"),
                    routingContext.request().getParam("birthDate"),
                    routingContext.request().getParam("sex")
            );


            //Comando Agregar user
            CreateUserCommand cmd = (CreateUserCommand) CommandFactory.instantiateCreateUser(user);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Create User.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersCreate(HttpServerRequest request) {
        LoggerOps.debug("Validating Parameters Create User.");
        //False = no errors
        //True = error

        if ( (request.getParam("name") == null) || !(request.getParam("name").matches("[a-zA-Z ]+$"))) {
            LoggerOps.error("Wrong name: " + request.getParam("name"));
            return true;
        }
        if ((request.getParam("lastName") == null) || !(request.getParam("lastName").matches("[a-zA-Z ]+$"))) {
            LoggerOps.error("Wrong lastName: " + request.getParam("lastName"));
            return true;
        }
        if ((request.getParam("email") == null) || !(request.getParam("email").matches("^[-a-z0-9~!$%^&*_=+}{\\'?]+(\\.[-a-z0-9~!$%^&*_=+}{\\'?]+)" +
                "*@([a-z0-9_][-a-z0-9_]*(\\.[-a-z0-9_]+)*\\.(aero|arpa|biz|com|coop|edu|gov|info|int|mil|museum|name" +
                "|net|org|pro|travel|mobi|[a-z][a-z])|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]" +
                "{1,5})?$"))) {
            LoggerOps.error("Wrong email: " + request.getParam("email"));
            return true;
        }
        if ((request.getParam("username") == null) || !(request.getParam("username").matches("[a-zA-Z0-9]+$"))) {
            LoggerOps.error("Wrong username: " + request.getParam("username"));
            return true;
        }
        if ((request.getParam("password") == null) || !(request.getParam("password").matches("[a-zA-Z0-9]{7,100}+$"))) {
            LoggerOps.error("Wrong password: " + request.getParam("password"));
            return true;
        }
        if ((request.getParam("birthDate") == null) || !(request.getParam("birthDate").matches("^[0-9]{4}-(((0[13578]|(10|12))-(0[1-9]|[1-2][0-9]|3[0-1]" +
                "))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)-(0[1-9]|[1-2][0-9]|30)))$"))) {
            LoggerOps.error("Wrong birthDate: " + request.getParam("birthDate"));
            return true;
        }
        if ((request.getParam("sex") == null) || !(request.getParam("sex").matches("[m|f]$"))) {
            LoggerOps.error("Wrong sex: " + request.getParam("sex"));
            return true;
        }

        return false;
    }

    private void handleGetAllUsers(RoutingContext routingContext) {
        LoggerOps.debug("STARTING - Handling Get All User.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        //Comando Agregar user
        GetAllUserCommand cmd = (GetAllUserCommand) CommandFactory.instantiateGetAllUser();

        cmd.execute();

        LoggerOps.debug("ENDING - Responding Get All User.");
        response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());

    }

    private void handleUpdateUser(RoutingContext routingContext) {
        LoggerOps.debug("Handling Update User.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersUpdate(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            UserBean user = new UserBean(
                    Integer.parseInt(routingContext.request().getParam("userId")),
                    routingContext.request().getParam("name"),
                    routingContext.request().getParam("lastName"),
                    routingContext.request().getParam("email"),
                    routingContext.request().getParam("username"),
                    routingContext.request().getParam("password"),
                    routingContext.request().getParam("birthDate"),
                    routingContext.request().getParam("sex")
            );


            //Comando Agregar user
            UpdateUserCommand cmd = (UpdateUserCommand) CommandFactory.instantiateUpdateUser(user);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Update User.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersUpdate(HttpServerRequest request) {

        return validateParametersGet(request) || validateParametersCreate(request);
    }

    private void handleDeleteUser(RoutingContext routingContext) {
        LoggerOps.debug("Handling Delete User.");

        HttpServerResponse response = routingContext.response();

        response.putHeader("Content-Type", "application/json");

        if(validateParametersDelete(routingContext.request())) {
            response.setStatusCode(400).end(GsonOps.toJson("Parameter Error"));
        } else {

            int userId = Integer.parseInt(routingContext.request().getParam("userId"));



            //Comando Agregar user
            DeleteUserCommand cmd = (DeleteUserCommand) CommandFactory.instantiateDeleteUser(userId);

            cmd.execute();

            LoggerOps.debug("ENDING - Responding Delete User.");
            response.setStatusCode(cmd.getStatus() ? 200 : 400).end(cmd.getMessage());
        }

    }

    private boolean validateParametersDelete(HttpServerRequest request) {

        return validateParametersGet(request);
    }

}