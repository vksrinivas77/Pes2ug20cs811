package com.example.onlinevoting.validator;

import com.example.onlinevoting.model.Voting;

public class VotingValidatorChain {
    private VotingValidator head;

    public VotingValidatorChain() {
        initialize();
    }

    private void initialize() {
        head = new StringValidator();
    }

    public void validate(Voting voting) throws Exception {
        head.validate(voting);
    }
}
