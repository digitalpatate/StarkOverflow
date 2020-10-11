package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.domain.user.User;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcVoteRepository")
@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcVoteRepository implements IVoteRepository {
    @Resource(lookup = "jdbc/postgresql")
    DataSource dataSource;

    @Override
    public Vote save(Vote entity) {
        String query = String.format("INSERT INTO votes(vote_id, author) " +
                "VALUES(?,?)");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            System.out.println();
            statement.setString(1, entity.getId().asString());
            statement.setString(2, entity.getUser_id().asString());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return entity;
    }

    @Override
    public void remove(VoteId id) {
        String query = String.format("DELETE FROM votes WHERE vote_id = '%s';", id.asString());
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        String  query = String.format("SELECT * FROM votes WHERE vote_id='%s'",id.asString());

        PreparedStatement statement = null;
        try {
            statement = dataSource.getConnection().prepareStatement(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Collection<Vote> foundedVotes = new ArrayList<>();
        try {
            ResultSet res = statement.executeQuery();
            while (res.next()){
                Vote vote = resultSetToEntity(res);
                foundedVotes.add(vote);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foundedVotes.stream().findFirst();
    }

    @Override
    public Collection<Vote> findAll() {
        String query = String.format("SELECT * FROM votes");
        Collection<Vote> foundedVotes = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            ResultSet res = statement.executeQuery();


            /*
             * Not sure if it's working ...
             */
            while (res.next()){
                Vote vote = resultSetToEntity(res);
                log.info(vote.toString());
                foundedVotes.add(vote);
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foundedVotes;
    }

    private Vote resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Vote(
                new VoteId(resultSet.getString("vote_id")),
                new UserId(resultSet.getString("author"))
        );
    }
}
