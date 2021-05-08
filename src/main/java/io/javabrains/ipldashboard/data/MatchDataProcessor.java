package io.javabrains.ipldashboard.data;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;

import io.javabrains.ipldashboard.entity.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

  @Override
  public Match process(final MatchInput matchData) throws Exception {
    Match match = new Match();
    match.setId(Long.valueOf(matchData.getId()));
    match.setCity(matchData.getCity());
    match.setDate(LocalDate.parse(matchData.getDate()));
    match.setPlayerOfMatch(matchData.getPlayer_of_match());
    match.setVenue(matchData.getVenue());
    match.setNeutralVenue(matchData.getNeutral_venue());
    match.setTeam1(matchData.getTeam1());
    match.setTeam2(matchData.getTeam2());
    match.setTossWinner(matchData.getToss_winner());
    match.setTossDecision(matchData.getToss_decision());
    match.setWinner(matchData.getWinner());
    match.setResult(matchData.getResult());
    match.setResultMargin(matchData.getResult_margin());
    match.setEliminator(matchData.getEliminator());
    match.setMethod(matchData.getMethod());
    match.setUmpire1(matchData.getUmpire1());
    match.setUmpire2(matchData.getUmpire2());
    return match;
  }

}