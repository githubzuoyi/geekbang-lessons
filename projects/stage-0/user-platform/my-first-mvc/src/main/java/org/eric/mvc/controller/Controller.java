package org.eric.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller class
 *
 * @author 左飞
 * @date 2021/3/2
 */
public interface Controller {

    String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable;

}
