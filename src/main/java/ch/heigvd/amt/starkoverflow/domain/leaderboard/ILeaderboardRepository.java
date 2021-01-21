package ch.heigvd.amt.starkoverflow.domain.leaderboard;

import ch.heigvd.amt.starkoverflow.infrastructure.gamificator.dto.LeaderBoardDTO;

import java.util.Optional;

public interface ILeaderboardRepository {
    Optional<LeaderBoardDTO> findByPointScaleName(String pointScaleName);
}
