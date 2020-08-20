package kz.zepterbot.command.implement;

import kz.zepterbot.command.Command;
import kz.zepterbot.entity.custom.SurveyAns;
import kz.zepterbot.entity.custom.SurveyQuest;
import kz.zepterbot.entity.standart.Button;
import kz.zepterbot.util.ButtonsLeaf;
import kz.zepterbot.util.Const;
import kz.zepterbot.util.type.WaitingType;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class id0012_Survey extends Command {

    List<SurveyQuest> surveyQuestsForOffice = surveyQuestionsDao.getQuestionListForOffice();
    List<SurveyQuest> surveyQuestsForManagers = surveyQuestionsDao.getQuestionListForManagers();


    SurveyAns surveyAnsOffice = new SurveyAns();
    SurveyAns surveyAns2Managers = new SurveyAns();
    String ans1 = "";
    String ans2 = "";
    @Override
    public boolean execute() throws SQLException, TelegramApiException {
        switch (waitingType) {
            case START:
                if (officeDao.checkUser(chatId)){
                    List<String> answers = getAnswers(surveyQuestsForOffice.get(0).getAnswers());
                    ButtonsLeaf buttonsLeaf = new ButtonsLeaf(answers);
                    sendMessageWithKeyboard(surveyQuestsForOffice.get(0).getText(),buttonsLeaf.getListButton());
                    waitingType = WaitingType.ANSWER_FOR_OFFICE;
                    return COMEBACK;
                }
                else if (salaesManagerUserDao.checkUser(chatId))
                {
                    List<String> answers = getAnswers(surveyQuestsForManagers.get(0).getAnswers());
                    ButtonsLeaf buttonsLeaf = new ButtonsLeaf(answers);
                    sendMessageWithKeyboard(surveyQuestsForManagers.get(0).getText(),buttonsLeaf.getListButton());
                    waitingType = WaitingType.ANSWER_FOR_MANAGERS;
                    return COMEBACK;
                }
            case ANSWER_FOR_OFFICE:
                if (!surveyQuestsForOffice.isEmpty()){
                    ans1 = ans1 + surveyQuestsForOffice.get(0).getId() + ":" + updateMessageText + ", ";
                    surveyAnsOffice.setFullName(officeDao.getFullNameByChatId(chatId));
                    surveyAnsOffice.setChat_id(chatId);
                    surveyQuestsForOffice.remove(0);
                    if (!surveyQuestsForOffice.isEmpty()) {
                        List<String> answers1 = getAnswers(surveyQuestsForOffice.get(0).getAnswers());
                        ButtonsLeaf buttonsLeaf1 = new ButtonsLeaf(answers1);
                        sendMessageWithKeyboard(surveyQuestsForOffice.get(0).getText(), buttonsLeaf1.getListButton());
                    }
                    else {
                        surveyAnsOffice.setAnswer(ans1);
                        officeSurveyAnswersDao.insert(surveyAnsOffice);
                        sendMessage(Const.END_SURVEY,chatId);
                        return EXIT;
                    }
                    waitingType = WaitingType.ANSWER_FOR_OFFICE;
                    return COMEBACK;
                }
            case ANSWER_FOR_MANAGERS:
                if (!surveyQuestsForManagers.isEmpty()){
                    ans2 = ans2 + surveyQuestsForManagers.get(0).getId() + ":" + updateMessageText + ", ";
                    surveyAns2Managers.setFullName(salaesManagerUserDao.getFullNameByChatId(chatId));
                    surveyAns2Managers.setChat_id(chatId);
                    surveyQuestsForManagers.remove(0);
                    if (!surveyQuestsForManagers.isEmpty()){
                        List<String> answers = getAnswers(surveyQuestsForManagers.get(0).getAnswers());
                        ButtonsLeaf buttonsLeaf = new ButtonsLeaf(answers);
                        sendMessageWithKeyboard(surveyQuestsForManagers.get(0).getText(),buttonsLeaf.getListButton());
                    }
                    else {
                        surveyAns2Managers.setAnswer(ans2);
                        managerSurveyAnswersDao.insert(surveyAns2Managers);
                        sendMessage(Const.END_SURVEY,chatId);
                        return EXIT;
                    }
                    waitingType = WaitingType.ANSWER_FOR_MANAGERS;
                    return COMEBACK;
                }
        }
        return EXIT;
    }

    private List<String> getAnswers(String answer){
        List<String> ansList = new ArrayList<>();
        String[] answers = answer.split(";");
        for(String ans: answers){
            ansList.add(ans);
        }

        for (String str:ansList){
            System.err.println(str);
        }

        return ansList;
    }


}
