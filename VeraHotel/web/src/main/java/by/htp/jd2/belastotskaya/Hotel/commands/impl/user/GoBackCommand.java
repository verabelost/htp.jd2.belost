/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.impl.user;

import javax.servlet.http.HttpServletRequest;

import by.htp.jd2.belastotskaya.Hotel.constants.PagePath;
import by.htp.jd2.belastotskaya.Hotel.commands.AbstractCommand;
import by.htp.jd2.belastotskaya.Hotel.managers.ConfigurationManager;

/**
 *
 * @author khudnitsky
 * @version 1.0
 *
 */
public class GoBackCommand extends AbstractCommand {

    /**
     *
     * @param request - http request
     * @return index.jsp page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getInstance().getProperty(PagePath.INDEX_PAGE_PATH);
        return page;
    }
}
