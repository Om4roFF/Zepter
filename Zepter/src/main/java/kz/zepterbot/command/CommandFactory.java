package kz.zepterbot.command;

import kz.zepterbot.command.implement.id001_ShowInfo;
import kz.zepterbot.command.implement.id002_SelectLang;
import kz.zepterbot.command.implement.id003_SelectDepartment;
import kz.zepterbot.command.implement.id024_RegistrationSellManager;
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
            case 3:
                return new id003_SelectDepartment();
            case 10:
                return new id024_RegistrationSellManager();

        }

        return null;
    }
}
