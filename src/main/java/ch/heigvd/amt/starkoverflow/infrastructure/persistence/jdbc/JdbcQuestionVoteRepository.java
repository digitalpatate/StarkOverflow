package ch.heigvd.amt.starkoverflow.infrastructure.persistence.jdbc;

import ch.heigvd.amt.starkoverflow.domain.IEntity;
import ch.heigvd.amt.starkoverflow.domain.answer.Answer;
import ch.heigvd.amt.starkoverflow.domain.answer.AnswerId;
import ch.heigvd.amt.starkoverflow.domain.question.Question;
import ch.heigvd.amt.starkoverflow.domain.question.QuestionId;
import ch.heigvd.amt.starkoverflow.domain.user.UserId;
import ch.heigvd.amt.starkoverflow.domain.vote.IQuestionVoteRepository;
import ch.heigvd.amt.starkoverflow.domain.vote.Vote;
import ch.heigvd.amt.starkoverflow.domain.vote.VoteId;
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
@Named("JdbcQuestionVoteRepository")
//@NoArgsConstructor
@AllArgsConstructor
@Log
public class JdbcQuestionVoteRepository extends JdbcRepository implements IQuestionVoteRepository {

    @Override
    public Vote save(Vote entity) {
        super.insert("question_votes", Arrays.asList(
                "vote_id",
                "fk_author",
                "fk_question"
        ), Arrays.asList(
                entity.getId().asString(),
                entity.getUserId().asString(),
                entity.getVotableId().asString()
        ));

        return entity;
    }

    @Override
    public void remove(VoteId id) {
        super.remove("question_votes", "vote_id", id);
    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        Optional<IEntity> vote = super.find("question_votes", "vote_id", id.asString());
        return vote.map(entity -> (Vote) entity);
    }

    @Override
    public Collection<Vote> findAll() {
        return (Collection) super.findAll("question_votes");
    }

    @Override
    public Vote userVoteOnQuestion(UserId viewer, QuestionId questionId) {
        Vote vote = null;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT * FROM question_votes WHERE fk_author=? AND fk_question=?");
            preparedStatement.setString(1, viewer.asString());
            preparedStatement.setString(2, questionId.asString());
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
    public long getNbVotesOfQuestion(QuestionId questionId) {
        long nbVotes = 0;

        try {
            PreparedStatement preparedStatement = dataSource.getConnection()
                    .prepareStatement("SELECT COUNT(*) AS total FROM question_votes WHERE fk_question=?");
            preparedStatement.setString(1, questionId.asString());
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
    public int getTotalQuestionVoteRepository() {
        int nbQuestionVote = -1;
        try {
            ResultSet resultSet = safeExecuteQuery("SELECT COUNT(*) AS totalQuestionVote FROM question_votes",null);
            if(resultSet.next()) {
                nbQuestionVote = resultSet.getInt("totalQuestionVote");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return nbQuestionVote;
    }

    @Override
    public Vote resultSetToEntity(ResultSet resultSet) throws SQLException {
        return new Vote(
                new VoteId(resultSet.getString("vote_id")),
                new UserId(resultSet.getString("fk_author")),
                new Question(new QuestionId(resultSet.getString("fk_question")))
        );
    }
}
