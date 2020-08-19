package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.standart.SalesManagerUser;
import kz.zepterbot.entity.standart.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalaesManagerUserDao extends AbstractDao<SalesManagerUser> {

    public void insert(SalesManagerUser salesManagerUser){
        sql = "INSERT INTO PUBLIC.SALES_MANAGER(CHAT_ID,FULL_NAME,CONTACT_NUMBER) VALUES(?,?,?)";
        getJdbcTemplate().update(sql,setParam(salesManagerUser.getChatId(),salesManagerUser.getFullName(),salesManagerUser.getPhone()));
    }

    public boolean checkUser(long chat_id){
        sql = "SELECT count(*) FROM PUBLIC.SALES_MANAGER WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql,setParam(chat_id),Integer.class) >0;
    }

    public boolean isRegistered(long chatId) {
        sql = "SELECT count(*) FROM PUBLIC.SALES_MANAGER WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql, setParam(chatId), Integer.class) > 0;
    }

    public SalesManagerUser getUserByChatId(long chatId) {
        sql = "SELECT * FROM PUBLIC.SALES_MANAGER WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql, setParam(chatId), this::mapper);
    }


    @Override
    protected SalesManagerUser mapper(ResultSet rs, int index) throws SQLException {
        SalesManagerUser salesManagerUser = new SalesManagerUser();
        salesManagerUser.setId(rs.getInt(1));
        salesManagerUser.setChatId(rs.getLong(2));
        salesManagerUser.setPhone(rs.getString(3));
        salesManagerUser.setFullName(rs.getString(4));
        return salesManagerUser;
    }
}
