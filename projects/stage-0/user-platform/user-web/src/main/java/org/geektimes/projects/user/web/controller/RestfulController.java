package org.geektimes.projects.user.web.controller;

import org.geektimes.web.mvc.controller.RestController;

import javax.ws.rs.Path;

@Path("/hi")
public class RestfulController implements RestController {

    @Path("/hi-rest")
    public String restapi(){
        return "login-form.jsp";
    }

}
