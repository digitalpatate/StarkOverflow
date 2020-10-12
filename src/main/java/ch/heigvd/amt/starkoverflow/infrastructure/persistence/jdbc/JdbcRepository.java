package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class JdbcRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;

    public void remove(String tableName, String idFieldName, Id id) {
        String query = String.format("DELETE FROM %s WHERE %s=?;", tableName, idFieldName);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = dataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1, id.asString());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Collection<IEntity> findAll(String tableName) {
        String query = String.format("SELECT * FROM %s", tableName);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = dataSource.getConnection().prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return entitiesFound(preparedStatement);
    }

    public Optional<IEntity> find(String tableName, String fieldName, String value) {
        String query = String.format("SELECT * FROM %s WHERE %s=?", tableName, fieldName);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = dataSource.getConnection().prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return entitiesFound(preparedStatement).stream().findFirst();
    }

    private Collection<IEntity> entitiesFound(PreparedStatement statement) {
        Collection<IEntity> entitiesFound = new ArrayList<>();

        try {
            ResultSet res = statement.executeQuery();
            while (res.next()){
                IEntity entity = resultSetToEntity(res);
                entitiesFound.add(entity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return entitiesFound;
    }

    public abstract IEntity resultSetToEntity(ResultSet resultSet) throws SQLException ;

}
