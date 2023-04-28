package com.example.onlinevoting.validator;

import com.example.onlinevoting.model.Voting;

public class StringValidator implements VotingValidator {
    private VotingValidator nextValidator;

    @Override
    public void setNextValidator(VotingValidator nextValidator) {
        this.nextValidator = nextValidator;
    }

    @Override
    public void validate(Voting voting) throws Exception {
        if (!isAlpha(voting.getVoterName())|| !isEnum(voting.getVoterID())|| !isAlpha(voting.getVoterGender())) {
            throw new Exception("All fields should contain only characters And Voter should contain only ID Number");
        }

        if (nextValidator != null) {
            nextValidator.validate(voting);
        }
    }

    private boolean isAlpha(String str) {
        return str != null && str.matches("^[a-zA-Z]+$");
    }
    private boolean isEnum(String str) {
        return str != null && str.matches("^[1-9]+$");
    }
}
