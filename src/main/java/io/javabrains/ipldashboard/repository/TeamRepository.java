package io.javabrains.ipldashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.javabrains.ipldashboard.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{

    Team findByTeamName(String teamName);
}
