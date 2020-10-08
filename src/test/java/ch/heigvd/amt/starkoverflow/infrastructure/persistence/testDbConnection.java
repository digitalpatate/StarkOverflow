package ch.heigvd.amt.starkoverflow.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.Connection;
import java.sql.SQLException;



import static org.junit.jupiter.api.Assertions.*;

public class testDbConnection {



    private Connection connection;
    private PGSimpleDataSource ds;


    @BeforeEach
    public void setup() throws SQLException{
        this.ds = new PGSimpleDataSource() ;
        this.ds.setDatabaseName( "stark_db" );
        this.ds.setUser( "admin" );
        this.ds.setPassword( "secret" );
        this.connection = this.ds.getConnection();
    }


        @Test
    public void oneShouldBeAbleToConnectTheDB() throws SQLException {
                assertNotNull(this.connection);
        }


    @Test
    public void oneShouldBeAbleToRetrieveMetadata() throws SQLException {
        int majorVesrion = this.connection.getMetaData().getDatabaseMajorVersion();
        int minorVesrion = this.connection.getMetaData().getDatabaseMinorVersion();

        assertEquals(majorVesrion, 13);
        assertEquals(minorVesrion, 0);
    }






}
