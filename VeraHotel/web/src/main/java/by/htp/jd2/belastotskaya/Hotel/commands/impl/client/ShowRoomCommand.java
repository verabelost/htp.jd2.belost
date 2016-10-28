/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.client;

import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;
import by.htp.jd2.belastotskaya.Hotel.constants.MessageConstants;
import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.constants.Parameters;
import by.htp.jd2.belastotskaya.Hotel.constants.UserType;
import by.htp.jd2.belastotskaya.Hotel.entities.Room;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.managers.MessageManager;
import by.htp.jd2.belastotskaya.Hotel.services.impl.RoomServiceImpl;
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
public class ShowRoomCommand extends AbstractCommand{

            public String execute(HttpServletRequest request) {
                String page = null;
                HttpSession session = request.getSession();
                UserType userType = RequestParameterParser.getUserType(request);
                    try{
                        List<Room> list = RoomServiceImpl.getInstance().getAll();
                        session.setAttribute(Parameters.ROOM_LIST, list);
                        page = ConfigurationManager.getInstance().getProperty(PagePath.CLIENT_SHOW_ROOM_PAGE);
                    }
                    catch (ServiceException | SQLException e) {
                        page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
                        request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
                    }

                return page;
            }
        }

