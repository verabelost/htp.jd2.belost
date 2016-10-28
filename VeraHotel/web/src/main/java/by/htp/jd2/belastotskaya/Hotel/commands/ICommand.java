/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands;

import javax.servlet.http.HttpServletRequest;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public interface ICommand {
    String execute(HttpServletRequest request);
}
