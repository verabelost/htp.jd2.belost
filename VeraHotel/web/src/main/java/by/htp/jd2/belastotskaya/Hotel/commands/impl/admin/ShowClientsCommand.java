/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.admin;

import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;
import by.htp.jd2.belastotskaya.Hotel.constants.MessageConstants;
import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.constants.Parameters;
import by.htp.jd2.belastotskaya.Hotel.constants.UserType;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.managers.MessageManager;
import by.htp.jd2.belastotskaya.Hotel.services.impl.UserServiceImpl;
import by.htp.jd2.belastotskaya.Hotel.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class ShowClientsCommand extends AbstractCommand{

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        System.out.println("Клиентская страница");
        if(userType == UserType.ADMINISTRATOR){
            try{
                List<User> list = UserServiceImpl.getInstance().getAll();
                session.setAttribute(Parameters.USER_LIST, list);
                page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_SHOW_CLIENTS_PAGE);
            }
            catch (ServiceException | SQLException e) {
                page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
            }
        }
        // TODO ПРОВверить, возможно отработает фильтр
        else{
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            session.invalidate();
        }
        return page;
    }
}
