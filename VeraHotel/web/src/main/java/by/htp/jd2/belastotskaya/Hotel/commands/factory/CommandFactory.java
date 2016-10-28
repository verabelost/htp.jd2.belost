/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.factory;

import javax.servlet.http.HttpServletRequest;

import by.htp.jd2.belastotskaya.Hotel.commands.impl.user.LoginUserCommand;
import by.htp.jd2.belastotskaya.Hotel.utils.RequestParameterParser;
import by.htp.jd2.belastotskaya.Hotel.commands.ICommand;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public class CommandFactory {
    private static CommandFactory instance;

    private CommandFactory(){}

    public static synchronized CommandFactory getInstance(){
        if(instance == null){
            instance = new CommandFactory();
        }
        return instance;
    }

    public ICommand defineCommand(HttpServletRequest request){
        ICommand current = null;
        try{
            CommandType type = RequestParameterParser.getCommandType(request);
            current = type.getCurrentCommand();
        }
        catch(IllegalArgumentException e){
            current = new LoginUserCommand();
        }
        return current;
    }
}
