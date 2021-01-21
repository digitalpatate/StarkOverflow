package ch.heigvd.amt.starkoverflow.domain.leaderboard;

import ch.heigvd.amt.starkoverflow.application.leaderboard.dto.LeaderBoardDTO_game;

import java.util.Optional;

public interface ILeaderboardRepository {
    Optional<LeaderBoardDTO_game> findByPointScaleName(String pointScaleName);
}
