package com.example.onlinevoting.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlinevoting.model.Voting;

public interface VotingRepository extends JpaRepository<Voting, Long>{

}