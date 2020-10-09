package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcStatisticRepository")
public class JdbcStatisticRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;

    public JdbcStatisticRepository(){

    }

    public JdbcStatisticRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

}
