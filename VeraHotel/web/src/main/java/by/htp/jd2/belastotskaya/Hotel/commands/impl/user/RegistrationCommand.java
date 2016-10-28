/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.user;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import by.htp.jd2.belastotskaya.Hotel.constants.MessageConstants;
import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.constants.Parameters;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.managers.MessageManager;
import by.htp.jd2.belastotskaya.Hotel.utils.RequestParameterParser;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.entities.Account;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.services.impl.UserServiceImpl;
import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class RegistrationCommand extends AbstractCommand {
    private User user;
    private Account account;
    private String accountIdString;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        try{
            user = RequestParameterParser.getUser(request);
            accountIdString = request.getParameter(Parameters.ACCOUNT_ID);
            if(areFieldsFullStocked()){
                account = RequestParameterParser.getAccount(request);
                if(UserServiceImpl.getInstance().checkIsNewUser(user)){
                    UserServiceImpl.getInstance().registrateUser(user, account);
                    page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
                    request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
                }
                else{
                    page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
                    request.setAttribute(Parameters.ERROR_USER_EXISTS, MessageManager.getInstance().getProperty(MessageConstants.USER_EXISTS));
                }
            }
            else{
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_FIELDS));
                page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
            }
        }
        catch (ServiceException | SQLException e) {
            page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
            request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
        }
        catch (NumberFormatException e) {
            request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
            page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
        }
        // TODO исправить
        catch(NullPointerException e){
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
        }
        return page;
    }

    // TODO javascript???
    private boolean areFieldsFullStocked(){

        boolean isFullStocked = false;
        if(!user.getFirstName().isEmpty()
                & !user.getLastName().isEmpty()
                & !user.getLogin().isEmpty()
                & !user.getPassword().isEmpty()
                & !accountIdString.isEmpty()){
            isFullStocked = true;
        }
        return isFullStocked;
    }
}
