/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.client;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.factory.CommandType;
import by.htp.jd2.belastotskaya.Hotel.constants.MessageConstants;
import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.constants.Parameters;
import by.htp.jd2.belastotskaya.Hotel.constants.UserType;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.managers.MessageManager;
import by.htp.jd2.belastotskaya.Hotel.utils.RequestParameterParser;
import by.htp.jd2.belastotskaya.Hotel.entities.User;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.services.impl.AccountServiceImpl;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class AddFundsCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession();
        UserType userType = RequestParameterParser.getUserType(request);
        if(userType == UserType.CLIENT) {
            User user = RequestParameterParser.getRecordUser(request);
            try {
                if (!AccountServiceImpl.getInstance().checkAccountStatus(user.getAccountId())) {
                    double amount = RequestParameterParser.getAmountFromFunds(request);
                    if (amount > 0) {
                        CommandType type = RequestParameterParser.getCommandType(request);
                        String description = type.getValue();
                        AccountServiceImpl.getInstance().addFunds(user, description, amount);
                        request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
                        page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_ADDFUNDS_PAGE_PATH);
                    } else {
                        request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.NEGATIVE_ARGUMENT));
                        page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_ADDFUNDS_PAGE_PATH);
                    }
                } else {
                    page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_BLOCK_PAGE_PATH);
                }
            }
            catch (ServiceException | SQLException e) {
                page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
            } catch (NumberFormatException e) {
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
                page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_ADDFUNDS_PAGE_PATH);

            }
        }
        else{
            page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            session.invalidate();
        }
        return page;
    }
}
