package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.Office;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfficeDao extends AbstractDao<Office> {

    public void insert(Office office){
        sql = "INSERT INTO OFFICE_USERS(FULL_NAME,PHONE_NUMBER) " +
                "VALUES(?,?)";
        getJdbcTemplate().update(sql,setParam(office.getFullName(),office.getPhone()));
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
