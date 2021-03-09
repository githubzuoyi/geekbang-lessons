package org.geektimes.projects.user.web.controller;

import org.geektimes.web.mvc.controller.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;

@Path("/user")
public class RestfulController implements RestController {

    @Path("/register")
    public String restapi(HttpServletRequest request, HttpServletResponse response) {
        return "user.jsp";
    }


}
