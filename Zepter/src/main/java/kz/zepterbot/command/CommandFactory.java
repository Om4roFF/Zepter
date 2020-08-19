package kz.zepterbot.command;

import kz.zepterbot.command.implement.*;
import kz.zepterbot.exeption.NotRealizedMethodException;

public class CommandFactory {

    public static Command getCommand(long id) {
        Command result = getCommandWithoutReflection((int) id);
        if (result == null) throw new NotRealizedMethodException("Not realized for type: " + id);
        return result;
    }
    private static Command getCommandWithoutReflection(int id) {
        switch (id) {
            case 1:
                return new id001_ShowInfo();
            case 2:
                return new id002_SelectLang();
            case 4:
                return new id004_Office();
            case 6:
                return new id006_Customers();
            case 7:
                return new id007_ComplaintsAndSuggestions();
            case 8:
                return new id008_SumbitRequest();
            case 10:
                return new id024_RegistrationSellManager();

        }

        return null;
    }
}
