package se.ju23.typespeeder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ju23.typespeeder.entity.Result;
import se.ju23.typespeeder.logic.IGameLogic;
import se.ju23.typespeeder.repositories.ResultRepo;

@Service
public class GameService {

    private final IGameLogic gameLogic;


    @Autowired
    public GameService(IGameLogic gameLogic) {
        this.gameLogic = gameLogic;

    }


}
