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
import java.util.Arrays;
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
        super.insert("votes", Arrays.asList(
                "vote_id",
                "author"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getUser_id().asString()
        ));

        return entity;
    }

    @Override
    public void remove(VoteId id) {
        super.remove("votes", "vote_id", id);
    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        Optional<IEntity> vote = super.find("votes", "vote_id", id.asString());
        return vote.map(entity -> (Vote) entity);
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
