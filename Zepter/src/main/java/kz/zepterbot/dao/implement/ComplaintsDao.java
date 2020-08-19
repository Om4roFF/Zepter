package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.Complaints;
import kz.zepterbot.entity.custom.Office;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplaintsDao extends AbstractDao<Complaints> {

    public void insert(Complaints complaints){
        sql = "INSERT INTO "+ Const.TABLE_NAME +".COMPLAINTS(TEXT) " +
                "VALUES(?)";
        getJdbcTemplate().update(sql,setParam(complaints.getText()));
    }

    public int getLastId(){
        sql = "SELECT MAX(ID) FROM "+Const.TABLE_NAME+".COMPLAINTS";
        return getJdbcTemplate().queryForObject(sql,Integer.class);
    }

    @Override
    protected Complaints mapper(ResultSet rs, int index) throws SQLException {
        return null;
    }
}
