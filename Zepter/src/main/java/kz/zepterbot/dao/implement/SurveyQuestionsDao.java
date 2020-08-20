package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.SurveyAns;
import kz.zepterbot.entity.custom.SurveyQuest;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SurveyQuestionsDao extends AbstractDao<SurveyQuest> {


    public void insert(SurveyQuest surveyQuest){
        sql="INSERT INTO "+ Const.TABLE_NAME+".SURVEY_QUESTIONS(TEXT,ANSWERS,PANEL)" +
                "VALUES(?,?,?)";
        getJdbcTemplate().update(sql,setParam(surveyQuest.getText(),surveyQuest.getAnswers()));
    }

    public List<SurveyQuest> getQuestionList(){
        sql="SELECT * FROM SURVEY_QUESTIONS";
        return getJdbcTemplate().query(sql,this::mapper);
    }

    public List<SurveyQuest> getQuestionListForOffice(){
        sql="SELECT * FROM SURVEY_QUESTIONS " +
                "WHERE PANEL = 1";
        return getJdbcTemplate().query(sql,this::mapper);
    }
    public List<SurveyQuest> getQuestionListForManagers(){
        sql="SELECT * FROM SURVEY_QUESTIONS " +
                "WHERE PANEL = 2";
        return getJdbcTemplate().query(sql,this::mapper);
    }

    @Override
    protected SurveyQuest mapper(ResultSet rs, int index) throws SQLException {
        SurveyQuest surveyQuest = new SurveyQuest();
        surveyQuest.setId(rs.getInt(1));
        surveyQuest.setText(rs.getString(2));
        surveyQuest.setAnswers(rs.getString(3));
        surveyQuest.setPanel(rs.getInt(4));
        return surveyQuest;
    }
}
