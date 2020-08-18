package kz.zepter_bot.exeption;

public class CommandNotFoundException extends Exception {

    public CommandNotFoundException(Exception e) {
        super(e);
    }
}
