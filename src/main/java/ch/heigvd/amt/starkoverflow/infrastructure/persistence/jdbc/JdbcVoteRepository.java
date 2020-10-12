package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
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
//@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcVoteRepository extends JdbcRepository implements IVoteRepository {

    @Override
    public Vote save(Vote entity) {
        String query = String.format("INSERT INTO votes(vote_id, author) " +
                "VALUES(?,?)");
        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
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
        super.remove("votes", "vote_id", id);
    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        Optional<IEntity> vote = super.find("votes", "vote_id", id.asString());
        return vote.map(entity -> Optional.of((Vote) entity)).orElse(null);
    }

    @Override
    public Collection<Vote> findAll() {
        return (Collection) super.findAll("votes");
    }

    @Override
    public Vote resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Vote(
                new VoteId(resultSet.getString("vote_id")),
                new UserId(resultSet.getString("author"))
        );
    }
}
