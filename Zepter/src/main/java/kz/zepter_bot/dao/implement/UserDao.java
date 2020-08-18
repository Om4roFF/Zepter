package kz.zepter_bot.dao.implement;

import kz.zepter_bot.dao.AbstractDao;
import kz.zepter_bot.entity.standart.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao<User> {

    public void insert(User user){
        sql = "INSERT INTO PUBLIC.USER(CHAT_ID,FULL_NAME,USERNAME,COMPANY_ID) VALUES(?,?,?,?)";
        getJdbcTemplate().update(sql,setParam(user.getChatId(),user.getFullName(),user.getUserName(),
                user.getCompanyId()));
    }

    public boolean checkUser(Long chat_id){
        sql = "SELECT count(*) FROM PUBLIC.USER " +
                "WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql,setParam(chat_id),Integer.class) >0;
    }

    @Override
    protected User mapper(ResultSet rs, int index) throws SQLException {
        return null;
    }

    public User getUserByChatId(long chatId) {
        sql = "SELECT * FROM USERS WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql, setParam(chatId), this::mapper);
    }

}
