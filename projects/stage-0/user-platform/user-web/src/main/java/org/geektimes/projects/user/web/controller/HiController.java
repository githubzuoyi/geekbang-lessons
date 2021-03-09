package org.geektimes.projects.user.web.controller;

import org.geektimes.web.mvc.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/user")
public class HiController implements Controller {

    @GET
    @Path("/hi")
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "index.jsp";
    }

}
