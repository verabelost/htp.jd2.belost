/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jd2.belastotskaya.Hotel.utils.RequestHandler;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class Controller extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestHandler.processRequest(request, response);
    }
}
