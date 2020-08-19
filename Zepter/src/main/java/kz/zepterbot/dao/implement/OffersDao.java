package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.Offers;
import kz.zepterbot.entity.custom.Office;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OffersDao extends AbstractDao<Offers> {

    public void insert(Offers offers){
        sql = "INSERT INTO "+ Const.TABLE_NAME +".OFFERS(TEXT) " +
                "VALUES(?)";
        getJdbcTemplate().update(sql,setParam(offers.getText()));
    }

    public int getLastId(){
        sql = "SELECT MAX(ID) FROM "+Const.TABLE_NAME+".OFFERS";
        return getJdbcTemplate().queryForObject(sql,Integer.class);
    }


    @Override
    protected Offers mapper(ResultSet rs, int index) throws SQLException {
        return null;
    }
}
