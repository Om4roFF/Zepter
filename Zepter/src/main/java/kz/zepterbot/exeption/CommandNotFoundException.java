package kz.zepterbot.exeption;

public class CommandNotFoundException extends Exception {

    public CommandNotFoundException(Exception e) {
        super(e);
    }
}
