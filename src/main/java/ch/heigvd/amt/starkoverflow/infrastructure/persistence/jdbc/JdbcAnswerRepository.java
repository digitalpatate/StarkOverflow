package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;

    public JdbcAnswerRepository(){

    }

    public JdbcAnswerRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

}
