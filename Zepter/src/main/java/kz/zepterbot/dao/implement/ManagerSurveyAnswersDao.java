package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.SurveyAns;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerSurveyAnswersDao extends AbstractDao<SurveyAns> {

    public void insert(SurveyAns surveyAns){
        sql="INSERT INTO "+ Const.TABLE_NAME+".MANAGER_OFFICE_SURVEY_ANSWERS(CHAT_ID,ANSWER,FULL_NAME)" +
                "VALUES(?,?,?)";
        getJdbcTemplate().update(sql,setParam(surveyAns.getChat_id(),surveyAns.getAnswer(),
                surveyAns.getFullName()));
    }
    


    @Override
    protected SurveyAns mapper(ResultSet rs, int index) throws SQLException {
        SurveyAns surveyAns = new SurveyAns();
        surveyAns.setId(rs.getInt(1));
        surveyAns.setChat_id(rs.getLong(2));
        surveyAns.setAnswer(rs.getString(3));
        surveyAns.setFullName(rs.getString(4));
        return surveyAns;
    }
}
