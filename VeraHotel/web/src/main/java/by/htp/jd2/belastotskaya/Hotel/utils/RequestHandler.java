package by.htp.jd2.belastotskaya.Hotel.utils;

import by.htp.jd2.belastotskaya.Hotel.commands.factory.CommandFactory;
import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.commands.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
public class RequestHandler {
    private RequestHandler() {
    }

    public static void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.getInstance();
        ICommand сommand = commandFactory.defineCommand(request);
        String page = сommand.execute(request);
        if (page != null) {
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
