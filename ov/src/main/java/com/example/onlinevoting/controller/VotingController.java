package com.example.onlinevoting.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.onlinevoting.model.Candidate;
import com.example.onlinevoting.model.Voting;
import com.example.onlinevoting.service.VotingService;
import com.example.onlinevoting.service.CandidateService;
import com.example.onlinevoting.validator.VotingValidatorChain;

@Controller
public class VotingController {
	@Autowired
	private VotingService votingService;
	@Autowired
	private CandidateService candidateService;
	private final VotingValidatorChain validatorChain = new VotingValidatorChain();

	
	// display list of votings
	@GetMapping("/")
	public String viewVotingPage(Model model) {
		return findVotingPaginated(1, "id", "asc", model);
	}

	@GetMapping("/showNewVotingForm")
	public String showNewVotingForm(Model model) {
		// create model attribute to bind form data
		Voting Votings = Voting.getInstance();
		model.addAttribute("Votings", Votings);
		return "new_voting";
	}

	@PostMapping("/saveVoting")
	public String saveVoting(@ModelAttribute("Votings") Voting voting, Model model) {
		try {
			validatorChain.validate(voting);
			votingService.saveVoting(voting);
			return "redirect:/";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "new_voting";
		}
	}

	@GetMapping("/showFormForVotingUpdate/{id}")
	public String showFormForVotingUpdate(@PathVariable(value = "id") long id, Model model) {
		// get onlinevoting from the service
		Voting votings = votingService.getVotingById(id);
		// set onlinevoting as a model attribute to pre-populate the form
		model.addAttribute("votings", votings);
		return "update_voting";
	}

	@GetMapping("/deleteVoting/{id}")
	public String deleteVoting(@PathVariable(value = "id") long id) {

		// call delete onlinevoting method
		this.votingService.deleteVotingById(id);
		return "redirect:/";
	}

	@GetMapping("/votingpage/{pageNo}")
	public String findVotingPaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		Page<Voting> page = votingService.findVotingPaginated(pageNo, pageSize, sortField, sortDir);
		List<Voting> listVoting = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listVoting", listVoting);
		return "index";
	}

	// display list of votings
	@GetMapping("/candidate")
	public String viewCandidatePage(Model model) {
		return findCandidatePaginated(1, "id", "asc", model);
	}

	@GetMapping("/showNewCandidateForm")
	public String showNewCandidateForm(Model model) {
		// create model attribute to bind form data
		Candidate candidates = Candidate.getInstance();
		model.addAttribute("candidate", candidates);
		return "new_candidate";
	}

	@PostMapping("/saveCandidate")
	public String saveCandidate(@ModelAttribute("Candidate") Candidate candidate, Model model) {
		candidateService.saveCantidate(candidate);
			return "redirect:/candidate";
	}

	@GetMapping("/showFormForCandidateUpdate/{id}")
	public String showFormForCandidateUpdate(@PathVariable(value = "id") long id, Model model) {
		// get onlinevoting from the service
		Candidate candidates = candidateService.getCandidateById(id);
		// set onlinevoting as a model attribute to pre-populate the form
		model.addAttribute("candidate", candidates);
		return "update_candidate";
	}

	@GetMapping("/deleteCandidate/{id}")
	public String deleteCandidate(@PathVariable(value = "id") long id) {

		// call delete onlinevoting method
		this.candidateService.deleteCandidateById(id);
		return "redirect:/candidate";
	}

	@GetMapping("/candidatepage/{pageNo}")
	public String findCandidatePaginated(@PathVariable(value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		Page<Candidate> page = candidateService.findCandidatePaginated(pageNo, pageSize, sortField, sortDir);
		List<Candidate> listCandidate = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("listCandidate", listCandidate);
		return "candidate";
	}

	@GetMapping("/clear")
	public String Clear() {
		return "clear";
	}

	@GetMapping("/deleteAll")
	public String deleteAll() {
		this.candidateService.deleteAllCandidate();
		this.votingService.deleteAllVotings();
		return "redirect:/";
	}
}