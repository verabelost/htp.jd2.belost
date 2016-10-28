/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.commands.factory;

import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.GoBackAdminCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.GoToAddRoom;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.GoToUnblockCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.ShowClientsCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.ShowOperationsCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.client.*;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.user.GoBackCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.user.LoginUserCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.user.LogoutUserCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.ICommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.UnblockCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.user.GoToRegistrationCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.user.RegistrationCommand;
import by.htp.jd2.belastotskaya.Hotel.commands.impl.admin.AddRoomCommand;

/**
 * @author khudnitsky
 * @version 1.0
 *
 */
public enum CommandType {
    //user commands
    LOGIN, LOGOUT, REGISTRATION, GOTOREGISTRATION, BACK, GOTOADDROOM,

    // client commands
    PAYMENT, GOTOPAYMENT, BALANCE, ADDFUNDS, GOTOADDFUNDS, BLOCK, BACKCLIENT, SHOWROOM,

    // admin commands
    CLIENTS, OPERATIONS, UNBLOCK, GOTOUNBLOCK, ADDROOM, BACKADMIN;

    public ICommand getCurrentCommand() throws EnumConstantNotPresentException{
        switch(this){
            case LOGIN:
                return new LoginUserCommand();
            case GOTOADDROOM:
                return new GoToAddRoom();

            case ADDROOM:
                return new AddRoomCommand();

            case SHOWROOM:
                return new ShowRoomCommand();

            case LOGOUT:
                return new LogoutUserCommand();

            case REGISTRATION:
                return new RegistrationCommand();

            case GOTOREGISTRATION:
                return new GoToRegistrationCommand();

            case BACK:
                return new GoBackCommand();

            case PAYMENT:
                return new PaymentCommand();

            case GOTOPAYMENT:
                return new GoToPaymentCommand();

            case BALANCE:
                return new BalanceCommand();

            case ADDFUNDS:
                return new AddFundsCommand();

            case GOTOADDFUNDS:
                return new GoToAddFundsCommand();

            case BLOCK:
                return new BlockCommand();

            case BACKCLIENT:
                return new GoBackClientCommand();

            case CLIENTS:
                return new ShowClientsCommand();

            case OPERATIONS:
                return new ShowOperationsCommand();

            case UNBLOCK:
                return new UnblockCommand();

            case GOTOUNBLOCK:
                return new GoToUnblockCommand();

            case BACKADMIN:
                return new GoBackAdminCommand();

            default:
                return new LoginUserCommand();
                //throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }

    public String getValue(){
        switch(this){
            case PAYMENT:
                return "Платеж";

            case BLOCK:
                return "Блокировка счета";

            case UNBLOCK:
                return "Разблокировка счета";

            case ADDFUNDS:
                return "Пополнение счета";

            default:
                throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
        }
    }
}
