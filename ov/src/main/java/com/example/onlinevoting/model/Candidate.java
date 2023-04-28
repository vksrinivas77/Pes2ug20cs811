package com.example.onlinevoting.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Candidate")
public class Candidate {
	private static Candidate obj;
	private Candidate() {
	}
	// Only one thread can execute this at a time
	public static synchronized Candidate getInstance() {
		if (obj == null)
			obj = new Candidate();
		return obj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String CandidateName;
	private String CandidatePartyName;
	private String CandidateAge;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCandidateName() {
		return CandidateName;
	}

	public void setCandidateName(String CandidateName) {
		this.CandidateName = CandidateName;
	}

	public String getCandidatePartyName() {
		return CandidatePartyName;
	}

	public void setCandidatePartyName(String CandidatePartyName) {
		this.CandidatePartyName = CandidatePartyName;
	}

	public String getCandidateAge() {
		return CandidateAge;
	}

	public void setCandidateAge(String CandidateAge) {
		this.CandidateAge = CandidateAge;
	}
}