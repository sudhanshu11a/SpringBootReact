package io.javabrains.ipldashboard.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.javabrains.ipldashboard.entity.Match;
import io.javabrains.ipldashboard.entity.Team;
import io.javabrains.ipldashboard.repository.MatchRepository;
import io.javabrains.ipldashboard.repository.TeamRepository;

@RestController
@CrossOrigin
public class TeamController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TeamController.class);

    private TeamRepository teamRepository;
    
    private MatchRepository matchRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    @GetMapping("/team")
    public Iterable<Team> getAllTeam() {
        return this.teamRepository.findAll();
    }

    @GetMapping("/team/{teamName}")
    public ResponseEntity<?> getTeam(@PathVariable String teamName){
        LOGGER.info("get team method called for team "+ teamName);
        Team team = teamRepository.findByTeamName(teamName);
        if(team ==null) return ResponseEntity.ok("No conent Found");
        team.setMatchList(matchRepository
            .getLatestMatchesByTeam(teamName, 4));
        return ResponseEntity.ok(team);
    }

    @GetMapping("/team/{teamName}/matches")
    public ResponseEntity<?> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        List<Match> matches = null;
        //LocalDate startDate= LocalDate.of(year,1,1);
        matches = matchRepository.getMatchesByTeamBetweenDates(teamName, 
        LocalDate.of(year,1,1), LocalDate.of(year+1,1,1));
        return ResponseEntity.ok(matches);
    }
}


