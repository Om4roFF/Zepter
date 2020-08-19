package kz.zepterbot.dao.implement;

import kz.zepterbot.dao.AbstractDao;
import kz.zepterbot.entity.custom.Products;
import kz.zepterbot.util.Const;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductsDao extends AbstractDao<Products> {

    public void insert(Products products){
        sql="INSERT INTO "+ Const.TABLE_NAME+".PRODUCT(NAME_OF_PRODUCT) VALUES(?)";
        getJdbcTemplate().update(sql,setParam(products.getNameOfProduct()));
    }

    public List<Products> getAllProducts(){
        sql = "SELECT * FROM "+Const.TABLE_NAME+".PRODUCT";
        return getJdbcTemplate().query(sql,this::mapper);
    }


    @Override
    protected Products mapper(ResultSet rs, int index) throws SQLException {
        Products products = new Products();
        products.setId(rs.getInt(1));
        products.setNameOfProduct(rs.getString(2));
        return products;
    }
}
