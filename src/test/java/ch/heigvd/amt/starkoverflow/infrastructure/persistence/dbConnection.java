package ch.heigvd.amt.starkoverflow.infrastructure.persistence;

import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.InMemoryAnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


public class dbConnection {
    private static Connection connection;
    private static final String url = "jdbc:postgresql://localhost:5432/stark_db";
    private static final String user = "admin"; //superuser
    private static final String password = "secret";

    @BeforeEach
    public void setup() throws SQLException{
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


        @Test
    public void oneShouldBeAbleToConnectTheDB() {
            assertNotNull(connection);
        }


    @Test
    public void oneShouldBeAbleToRetrieveMetadata() throws SQLException {
        int majorVesrion = connection.getMetaData().getDatabaseMajorVersion();
        int minorVesrion = connection.getMetaData().getDatabaseMinorVersion();

        assertEquals(majorVesrion, 13);
        assertEquals(minorVesrion, 0);
    }






}
