/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.user;

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

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class LoginUserCommand extends AbstractCommand {
    private User user;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        System.out.println("Логинация");
        try {
            user = RequestParameterParser.getUser(request);
            if(UserServiceImpl.getInstance().checkUserAuthorization(user.getLogin(), user.getPassword())){
                user = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
                UserType userType = UserServiceImpl.getInstance().checkAccessLevel(user);
                session.setAttribute(Parameters.USER, user);
                session.setAttribute(Parameters.USERTYPE, userType);
                if(UserType.CLIENT.equals(userType)){
                    page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_PAGE_PATH);
                }
                else{
                    page = ConfigurationManager.getInstance().getProperty(PagePath.ADMIN_PAGE_PATH);
                }
            }
            else{
                page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_LOGIN_OR_PASSWORD, MessageManager.getInstance().getProperty(MessageConstants.WRONG_LOGIN_OR_PASSWORD));
            }
        }
        catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
        }
        return page;
    }
}
