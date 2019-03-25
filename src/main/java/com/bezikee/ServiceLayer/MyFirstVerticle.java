package com.bezikee.ServiceLayer;

import com.bezikee.Common.DateOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;


public class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {
        //http://localhost:8090/?name=andres&lastName=rubio&email=andresfra92@gmail.com&username=provemed&password=11111111&birthDate=1992-11-26&sex=m
        vertx
                .createHttpServer()
                .requestHandler(
                        request -> {
                            UserBean user = new UserBean(
                                    request.getParam("name"),
                                    request.getParam("lastName"),
                                    request.getParam("email"),
                                    request.getParam("username"),
                                    request.getParam("password"),
                                    request.getParam("birthDate"),
                                    request.getParam("sex")

                            );
                            IUserDao dao = DaoFactory.instantiateUserDao();

                            HttpServerResponse response = request.response();
                            response.putHeader("Content-Type", "text/plain");
                            response.putHeader("Content-Length", String.valueOf(20));



                            if(dao.create(user)){
                                request.response().write("Ok!");
                            }else {
                                request.response().write("Failed...");
                            }

                            response.end();
                        }
                )
                .listen(8090, result -> {
                    if (result.succeeded()) {
                        fut.complete();
                    } else {
                        fut.fail(result.cause());
                    }
                });
    }
}