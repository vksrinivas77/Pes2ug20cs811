package com.example.onlinevoting.service;
import java.util.List;
import org.springframework.data.domain.Page;
import com.example.onlinevoting.model.Candidate;

public interface CandidateService {
	List<Candidate> getAllCandidate();
	void saveCantidate(Candidate candidates);
	Candidate getCandidateById(long id);
	void deleteCandidateById(long id);
	void deleteAllCandidate();
	Page<Candidate> findCandidatePaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}