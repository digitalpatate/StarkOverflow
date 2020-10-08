package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.jdbc;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataSourceFactory {
    public static DataSource getPGDataSource() {
        Properties props = new Properties();
        FileInputStream fis = null;
        PGSimpleDataSource ds = null;
        try {
            fis = new FileInputStream("db.properties");
            props.load(fis);
            ds = new PGSimpleDataSource();
            ds.setURL(props.getProperty("PG_DB_URL"));
            ds.setUser(props.getProperty("PG_DB_USERNAME"));
            ds.setPassword(props.getProperty("PG_DB_PASSWORD"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ds;
    }
}
