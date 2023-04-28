package com.example.onlinevoting.service;
import java.util.List;
import org.springframework.data.domain.Page;
import com.example.onlinevoting.model.Voting;

public interface VotingService {
	List<Voting> getAllVoting();
	void saveVoting(Voting onlinevoting);
	Voting getVotingById(long id);
	void deleteVotingById(long id);
	void deleteAllVotings();
	Page<Voting> findVotingPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}