/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;
import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class GoToRegistrationCommand extends AbstractCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(PagePath.REGISTRATION_PAGE_PATH);
        return page;
    }
}
