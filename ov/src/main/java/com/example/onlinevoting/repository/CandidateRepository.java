package com.example.onlinevoting.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.onlinevoting.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

}