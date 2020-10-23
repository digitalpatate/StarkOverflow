package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IAnswerVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;
import ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc.utils.QueryBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerVoteRepository")
//@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcAnswerVoteRepository extends JdbcRepository implements IAnswerVoteRepository {

    @Override
    public Vote save(Vote entity) {
        super.insert("answer_votes", Arrays.asList(
                "vote_id",
                "fk_author",
                "fk_answer"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getUserId().asString(),
                entity.getVotableId().asString()
        ));

        return entity;
    }

    @Override
    public void remove(VoteId id) {
        super.remove("answer_votes", "vote_id", id);
    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        Optional<IEntity> vote =  super.find("answer_votes", "vote_id", id.asString());

        return vote.map(entity -> (Vote) entity);
    }

    @Override
    public Collection<Vote> findAll() {
        return (Collection) super.findAll("answer_votes");
    }

    @Override
    public Vote userVoteOnAnswer(UserId viewer, AnswerId answerId) {
        Vote vote = null;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM answer_votes WHERE fk_author=? AND fk_answer=?");
            preparedStatement.setString(1, viewer.asString());
            preparedStatement.setString(2, answerId.asString());
            preparedStatement.execute();
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) { // if result set is not empty, user has voted the answer
                vote = resultSetToEntity(res);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return vote;
    }

    @Override
    public long getNbVotesOfAnswer(AnswerId answerId) {
        long nbVotes = 0;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT COUNT(*) AS total FROM answer_votes WHERE fk_answer=?");
            preparedStatement.setString(1, answerId.asString());
            ResultSet res = preparedStatement.executeQuery();

            if(res.next()) {
                nbVotes = res.getLong("total");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return nbVotes;
    }

    @Override
    public Vote resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Vote(
                new VoteId(resultSet.getString("vote_id")),
                new UserId(resultSet.getString("fk_author")),
                new Answer(new AnswerId(resultSet.getString("fk_answer")))
        );
    }
}
