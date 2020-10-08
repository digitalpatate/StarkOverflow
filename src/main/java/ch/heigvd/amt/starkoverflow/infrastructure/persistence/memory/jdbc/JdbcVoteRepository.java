package ch.heigvd.amt.starkoverflow.infrastructure.persistence.memory.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcVoteRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;

    public JdbcVoteRepository(){

    }

    public JdbcVoteRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

}
