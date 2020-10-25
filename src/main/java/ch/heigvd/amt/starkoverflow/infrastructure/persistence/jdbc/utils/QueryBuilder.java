package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc.utils;

import java.util.List;
import java.util.logging.Logger;

public class QueryBuilder {

    private StringBuilder query = new StringBuilder();

    public QueryBuilder select(String ... fields){
        query.append("SELECT ");
        for (String field : fields){
            query.append(field).append(", ");
        }
        strikeNChar(2);
        return this;
    }

    public QueryBuilder from(String tableName){
        query.append("FROM ").append(tableName).append(" ");
        return this;
    }
    public QueryBuilder where(String fieldName, String condition){
        query.append("WHERE ").append(fieldName).append(condition).append("? ");
        return this;
    }
    public QueryBuilder delete(String tablename){
        query.append("DELETE FROM ").append(tablename).append(" ");
        return this;
    }
    public QueryBuilder insert(String tableName, List<String> fields){

        query.append("INSERT INTO ").append(tableName);
        query.append("( ");
        for (String field : fields){
            query.append(field).append(", ");
        }
        strikeNChar(2);
        query.append(") ");
        query.append("VALUES ").append("( ");

        for (String field : fields){
            query.append("?").append(", ");
        }
        strikeNChar(2);
        query.append(") ");

        return this;

    }

    public QueryBuilder update(String tableName){
        query.append("UPDATE ").append(tableName).append(" ");
        return this;
    }

    public QueryBuilder set(String field, String value){
        query.append("SET ").append(field).append("=").append(value).append(" ");
        return this;
    }

    public QueryBuilder count(String... fields){
        query.append("COUNT").append("(");
        for (String field : fields){
            query.append(field).append(", ");
        }
        strikeNChar(2);
        query.append(") ");
        return this;
    }
    public QueryBuilder as(String alias){
        query.append("AS ").append(alias).append(" ");
        return this;
    }
    public String build(){
        Logger.getAnonymousLogger().info(query.toString());
        return query.toString();
    }

    private void strikeNChar(int nbChar){
        String queryInString = query.toString();
        queryInString = queryInString.substring(0,queryInString.length()-nbChar);
        query = new StringBuilder(queryInString);
    }


}
