package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.Id;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc.utils.QueryBuilder;
import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class JdbcRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;

    public abstract IEntity resultSetToEntity(ResultSet resultSet) throws SQLException;

    public void insert(String tableName, List<String> fields, List<String> values) {
        String query = new QueryBuilder().insert(tableName,fields).build();
        safeExecuteUpdate(query, values);
    }

    public void remove(String tableName, String idFieldName, Id id) {
        String query = new QueryBuilder().delete(tableName).where(idFieldName,"=").build();
        safeExecuteUpdate(query, Arrays.asList(id.asString()));
    }

    @SneakyThrows
    public Collection<IEntity> findAll(String tableName) {

        String query = new QueryBuilder().select("*").from(tableName).build();

        ResultSet res = safeExecuteQuery(query,null);
        Collection<IEntity> found = new ArrayList<>();

        while (res.next()){

            found.add(this.resultSetToEntity(res));
        }

        return found;
    }

    public ResultSet selectWhere(String tableName, String fieldName, String value) {
        String query = new QueryBuilder().select("*").from(tableName).where(fieldName,"=").build();
        return safeExecuteQuery(query, Arrays.asList(value));


    }

    @SneakyThrows
    public Optional<IEntity> find(String tableName, String fieldName, String value) {
        ResultSet res = selectWhere(tableName, fieldName, value);
        Collection<IEntity> found = new ArrayList<>();

        while (res.next()){

            found.add(this.resultSetToEntity(res));
        }

        return found.stream().findFirst();

    }

    public ResultSet safeExecuteQuery(String query, List<String> values){
        PreparedStatement preparedStatement = prepareStatement(query, values);
        ResultSet result= null;
        try {
            //Only for select
            result= preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public int safeExecuteUpdate(String query, List<String> values){
        PreparedStatement preparedStatement = prepareStatement(query, values);
        int result =0;
        try {
            //Only for select
            result= preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    protected PreparedStatement prepareStatement(String query, List<String> values) {
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

}
