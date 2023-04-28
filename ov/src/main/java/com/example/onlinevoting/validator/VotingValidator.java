package com.example.onlinevoting.validator;

import com.example.onlinevoting.model.Voting;

public interface VotingValidator {
    void setNextValidator(VotingValidator nextValidator);

    void validate(Voting voting) throws Exception;
}
