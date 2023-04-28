package com.example.onlinevoting.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Voting")
public class Voting {
	private static Voting obj;
	private Voting() {
	}
	// Only one thread can execute this at a time
	public static synchronized Voting getInstance() {
		if (obj == null)
			obj = new Voting();
		return obj;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String VoterName;
	private String VoterID;
	private String VoterGender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVoterName() {
		return VoterName;
	}

	public void setVoterName(String VoterName) {
		this.VoterName = VoterName;
	}

	public String getVoterID() {
		return VoterID;
	}

	public void setVoterID(String VoterID) {
		this.VoterID = VoterID;
	}

	public String getVoterGender() {
		return VoterGender;
	}

	public void setVoterGender(String VoterGender) {
		this.VoterGender = VoterGender;
	}
}