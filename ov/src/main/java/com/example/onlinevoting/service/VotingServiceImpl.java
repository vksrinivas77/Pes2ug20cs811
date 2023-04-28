package com.example.onlinevoting.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.onlinevoting.model.Voting;
import com.example.onlinevoting.repository.VotingRepository;

@Service
public class VotingServiceImpl implements VotingService {

	@Autowired
	private VotingRepository votingRepository;

	@Override
	public List<Voting> getAllVoting() {
		return votingRepository.findAll();
	}

	@Override
	public void saveVoting(Voting Voting) {
		this.votingRepository.save(Voting);
	}

	@Override
	public Voting getVotingById(long id) {
		Optional<Voting> optional = votingRepository.findById(id);
		Voting onlinevoting = null;
		if (optional.isPresent()) {
			onlinevoting = optional.get();
		} else {
			throw new RuntimeException(" Voting not found for id :: " + id);
		}
		return onlinevoting;
	}

	@Override
	public void deleteVotingById(long id) {
		this.votingRepository.deleteById(id);
	}

	@Override
	public void deleteAllVotings() {
		this.votingRepository.deleteAll();
	}

	@Override
	public Page<Voting> findVotingPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.votingRepository.findAll(pageable);
	}
}