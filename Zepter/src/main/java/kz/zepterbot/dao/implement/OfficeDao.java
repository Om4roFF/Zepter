package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.Office;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeDao extends AbstractDao<Office> {

    public void insert(Office office){
        sql = "INSERT INTO "+ Const.TABLE_NAME +".OFFICE_USERS(FULL_NAME,PHONE_NUMBER,CHAT_ID) " +
                "VALUES(?,?,?)";
        getJdbcTemplate().update(sql,setParam(office.getFullName(),office.getPhone(),office.getChatId()));
    }

    public String getFullNameByChatId(long chatId){
        sql = "SELECT FULL_NAME FROM "+Const.TABLE_NAME+".OFFICE_USERS " +
                "WHERE CHAT_ID = ? ";
        return getJdbcTemplate().queryForObject(sql,setParam(chatId),String.class);
    }

    public boolean checkUser(long chatId){
        sql="SELECT COUNT(*) FROM "+Const.TABLE_NAME+".OFFICE_USERS " +
                "WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql,setParam(chatId),Integer.class)>0;
    }

    public boolean isRegistered(long chatId) {
        sql = "SELECT count(*) FROM "+Const.TABLE_NAME+".OFFICE_USERS WHERE CHAT_ID = ?";
        return getJdbcTemplate().queryForObject(sql, setParam(chatId), Integer.class) > 0;
    }


    @Override
    protected Office mapper(ResultSet rs, int index) throws SQLException {
        Office office = new Office();
        office.setId(rs.getInt(1));
        office.setFullName(rs.getString(2));
        office.setPhone(rs.getString(3));

        return office;
    }
}
