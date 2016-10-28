package by.htp.jd2.belastotskaya.Hotel.commands.impl.admin;

import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;
import by.htp.jd2.belastotskaya.Hotel.constants.MessageConstants;
import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.constants.Parameters;
import by.htp.jd2.belastotskaya.Hotel.entities.Room;
import by.htp.jd2.belastotskaya.Hotel.exceptions.ServiceException;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.managers.MessageManager;
import by.htp.jd2.belastotskaya.Hotel.services.impl.RoomServiceImpl;
import by.htp.jd2.belastotskaya.Hotel.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by diby on 23.10.2016.
 */
public class AddRoomCommand extends AbstractCommand {
    private Room room;

        @Override
        public String execute(HttpServletRequest request) {
            String page = null;
            try{
                room = RequestParameterParser.getRoom(request);
                if(areFieldsFullStocked()){
                    if(RoomServiceImpl.getInstance().checkIsNewRoom(room)){
                        RoomServiceImpl.getInstance().add(room);
                        page = ConfigurationManager.getInstance().getProperty(PagePath.ADD_ROOM_PATH);
                        request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
                    }
                    else{
                        page = ConfigurationManager.getInstance().getProperty(PagePath.ADD_ROOM_PATH);
                        request.setAttribute(Parameters.ERROR_USER_EXISTS, MessageManager.getInstance().getProperty(MessageConstants.USER_EXISTS));
                    }
                }
                else{
                    request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_FIELDS));
                    page = ConfigurationManager.getInstance().getProperty(PagePath.ADD_ROOM_PATH);
                }
            }
            catch (ServiceException | SQLException e) {
                page = ConfigurationManager.getInstance().getProperty(PagePath.ERROR_PAGE_PATH);
                request.setAttribute(Parameters.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
            }
            catch (NumberFormatException e) {
                request.setAttribute(Parameters.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
                page = ConfigurationManager.getInstance().getProperty(PagePath.ADD_ROOM_PATH);
            }
            // TODO исправить
            catch(NullPointerException e){
                page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
            }
            return page;
        }

        // TODO javascript???
        private boolean areFieldsFullStocked(){

            return true;
        }
    }


    ///


