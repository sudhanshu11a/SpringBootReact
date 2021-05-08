package io.javabrains.ipldashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.javabrains.ipldashboard.entity.Match;

@Repository
public interface MatchRepository extends CrudRepository<Match, Long>{

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable Pageable);

    @Query("SELECT m FROM Match m where ( m.team1 = :team OR m.team2 = :team) AND m.date BETWEEN :startDate AND :endDate ORDER BY m.date DESC")
    List<Match> getMatchesByTeamBetweenDates(@Param("team") String team, @Param("startDate") LocalDate stertDate, @Param("endDate") LocalDate endDate);

    default List<Match> getLatestMatchesByTeam(String teamName, int count){
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
    
}
