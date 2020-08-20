package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.entity.custom.Clients;
import kz.zepterbot.entity.custom.Products;
import kz.zepterbot.util.ButtonsLeaf;
import kz.zepterbot.util.Const;
import kz.zepterbot.util.type.WaitingType;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.event.CaretListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class id008_SumbitRequest extends Command {
    List<Products> products = productsDao.getAllProducts();
    Clients clients = new Clients();
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        switch (waitingType){
            case START:
                List<String> productName =new ArrayList<>();
                products.forEach(product -> productName.add(product.getNameOfProduct()));
                ButtonsLeaf buttonsLeaf = new ButtonsLeaf(productName);
                sendMessageWithKeyboard(Const.SEND_REQUEST,buttonsLeaf.getListButton());
                waitingType = WaitingType.CHOOSE_OPTION;
                return COMEBACK;
            case CHOOSE_OPTION:
                Products prod =products.get(Integer.parseInt(updateMessageText));
                clients.setNameOfProduct(prod.getNameOfProduct());
                sendMessage(Const.SET_FULL_NAME,chatId);
                waitingType = WaitingType.SET_FULL_NAME;
                return COMEBACK;
            case SET_FULL_NAME:
                if (updateMessageText.length()<=30 && hasMessageText()){
                    clients.setFullName(updateMessageText);
                    sendMessage(Const.SET_MOBILE_PHONE_NUMBER,chatId);
                    waitingType = WaitingType.SET_MOBILE_PHONE_NUMBER;
                    return COMEBACK;
                }
            case SET_MOBILE_PHONE_NUMBER:
                if (update.getMessage().hasContact()){
                    clients.setPhoneNumber(updateMessageText);
                    clientsDao.insert(clients);
                    sendMessage(Const.REQUEST,chatId);
                    return EXIT;
                }
        }
        return EXIT;
    }
}