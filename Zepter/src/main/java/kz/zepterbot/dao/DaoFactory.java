package kz.zepterbot.dao;

import kz.zepterbot.dao.implement.*;
import kz.zepterbot.entity.standart.SalesManagerUser;
import kz.zepterbot.util.PropertiesUtil;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@NoArgsConstructor
public class DaoFactory {

    private static DataSource source;
    private static DaoFactory daoFactory = new DaoFactory();
    private static Logger logger = LoggerFactory.getLogger(DaoFactory.class);

    public static DataSource getDataSource() {
        if (source == null) {
            source = getDriverManagerDataSource();
        }
        return source;
    }
    public  static  DaoFactory getInstance() { return daoFactory; }

    private static DriverManagerDataSource getDriverManagerDataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        String driverName = PropertiesUtil.getProperty("jdbc.driverClassName");
        driver.setDriverClassName(driverName);
        String urlName = PropertiesUtil.getProperty("jdbc.url");
        driver.setUrl(urlName);
        driver.setUsername(PropertiesUtil.getProperty("jdbc.username"));
        driver.setPassword(PropertiesUtil.getProperty("jdbc.password"));
        logger.info("Created new driver manager data source{}", driver);
        logger.info("Database - {}, url - {}", driverName, urlName);
        return driver;
    }

    public static DaoFactory getFactory() {
        return daoFactory;
    }

    public LanguageUserDao getLanguageUserDao() { return new LanguageUserDao();}

    public MessageDao getMessageDao() {
        return new MessageDao();
    }

    public KeyboardMarkUpDao getKeyboardMarkUpDao() {
        return new KeyboardMarkUpDao();
    }

    public ButtonDao getButtonDao() {
        return new ButtonDao();
    }

    public PropertiesDao getPropertiesDao(){return new PropertiesDao();}

    public UserDao getUserDao(){
        return new UserDao();
    }

    public OfficeDao getOfficeDao(){return new OfficeDao();}

    public SalaesManagerUserDao getSalesManagerUserDao(){return new SalaesManagerUserDao();}

    public AdminDao getAdminDao(){return new AdminDao();}


    public OffersDao getOffersDao(){return new OffersDao();}

    public ComplaintsDao getComplaintsDao(){return new ComplaintsDao();}

    public ProductsDao getProductsDao(){return new ProductsDao();}

    public ClientsDao getClientsDao(){return new ClientsDao();}

}
