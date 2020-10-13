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

    public void insert(String tableName, List<String> fields, List<String> values) {
        String queryFields = "";
        String queryPrepared = "";

        Iterator<String> it = fields.iterator();

        while (it.hasNext()) {
            queryFields += it.next();
            queryPrepared += "?";

            if(it.hasNext()) {
                queryFields += ", ";
                queryPrepared += ", ";
            }
        }

        String query = "INSERT INTO " + tableName + "(" + queryFields + ") VALUES(" + queryPrepared + ")";
        executeQuery(query, values);
    }

    public void remove(String tableName, String idFieldName, Id id) {
        String query = String.format("DELETE FROM %s WHERE %s=?", tableName, idFieldName);
        executeQuery(query, Arrays.asList(id.asString()));
    }

    public Collection<IEntity> findAll(String tableName) {
        String query = String.format("SELECT * FROM %s", tableName);
        PreparedStatement preparedStatement = executeQuery(query);

        return entitiesFound(preparedStatement);
    }

    public PreparedStatement selectWhere(String tableName, String fieldName, String value) {
        String query = String.format("SELECT * FROM %s WHERE %s=?", tableName, fieldName);
        return executeQuery(query, Arrays.asList(value));
    }

    public Optional<IEntity> find(String tableName, String fieldName, String value) {
        PreparedStatement preparedStatement = selectWhere(tableName, fieldName, value);
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

    protected PreparedStatement executeQuery(String query) {
        PreparedStatement preparedStatement = createPreparedStatement(query, null);

        try {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return preparedStatement;
    }

    protected PreparedStatement executeQuery(String query, List<String> values) {
        PreparedStatement preparedStatement = createPreparedStatement(query, values);

        try {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return preparedStatement;
    }

    protected PreparedStatement createPreparedStatement(String query, List<String> values) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = dataSource.getConnection().prepareStatement(query);

            if(values != null) {
                int i = 1;
                for(Object value : values) {
                   preparedStatement.setString(i, (String) value);
                   i++;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return preparedStatement;
    }

    public abstract IEntity resultSetToEntity(ResultSet resultSet) throws SQLException ;

}
