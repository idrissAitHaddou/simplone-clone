package org.example.Learner;

import java.sql.ResultSet;

public class LearnerServer {
    private LearnerRepository learnerRepository = new LearnerRepository();
    public ResultSet getAllMyBreifsLaunchedServer() {
        ResultSet breifs = null;
        return  breifs = learnerRepository.getAllMyBreifsLaunchedRepository();
    }
}
