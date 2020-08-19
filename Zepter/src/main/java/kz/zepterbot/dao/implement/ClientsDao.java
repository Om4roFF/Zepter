package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.Clients;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientsDao extends AbstractDao<Clients> {

    public void insert(Clients clients){
        sql="INSERT INTO "+ Const.TABLE_NAME+".CLIENTS(NAME_OF_PRODUCT," +
                "FULL_NAME,PHONE_NUMBER VAlUES(?,?,?)";
        getJdbcTemplate().update(sql,setParam(clients.getNameOfProduct(),
                clients.getFullName(),clients.getPhoneNumber()));
    }



    @Override
    protected Clients mapper(ResultSet rs, int index) throws SQLException {
        Clients clients = new Clients();
        clients.setId(rs.getInt(1));
        clients.setNameOfProduct(rs.getString(2));
        clients.setFullName(rs.getString(3));
        clients.setPhoneNumber(rs.getString(4));
        return clients;
    }
}
