package com.apocalypse.ZombieApocalypse.service;

import com.apocalypse.ZombieApocalypse.exception.InvalidRequestException;
import com.apocalypse.ZombieApocalypse.model.Coordinate;
import com.apocalypse.ZombieApocalypse.model.ZombieApocalypseRequest;
import com.apocalypse.ZombieApocalypse.model.ZombieApocalypseResponse;
import com.apocalypse.ZombieApocalypse.util.ZombieApocalypseUtil;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

@Service
public class ZombieService {

    private static final Logger LOGGER=LoggerFactory.getLogger(ZombieService.class);

    public List<ZombieApocalypseResponse> processZombieApocalypses(List<ZombieApocalypseRequest> zombieApocalypseRequests) throws InvalidRequestException {
        List<ZombieApocalypseResponse> zombieApocalypseResponses=new ArrayList<>();
        for(ZombieApocalypseRequest zombieApocalypseRequest: zombieApocalypseRequests) {
           ZombieApocalypseUtil.validateZombieApocalypse(zombieApocalypseRequest);
            ZombieApocalypseResponse zombieApocalypseResponse = zombieMovements(zombieApocalypseRequest);
            if(Objects.nonNull(zombieApocalypseResponse)) {
                LOGGER.info("***********************************************");
                LOGGER.info("OUTPUT");
                zombieApocalypseResponses.add(zombieApocalypseResponse);
                LOGGER.info("zombies' positions:");
                if(Objects.isNull(zombieApocalypseResponse.getZombiePosList())
                        || zombieApocalypseResponse.getZombiePosList().isEmpty()) {
                    LOGGER.info("none");
                } else {
                    LOGGER.info(zombieApocalypseResponse.getZombiePosList().toString());
                }
                LOGGER.info("creatures' positions:");
                if(Objects.isNull(zombieApocalypseResponse.getCreaturePosList())
                        || zombieApocalypseResponse.getCreaturePosList().isEmpty()) {
                    LOGGER.info("none");
                } else {
                    LOGGER.info(zombieApocalypseResponse.getCreaturePosList().toString());
                }
                LOGGER.info("***********************************************");
            }

        }
        return zombieApocalypseResponses;
    }

    public ZombieApocalypseResponse zombieMovements(ZombieApocalypseRequest zombieApocalypseRequest) {

        int grid = zombieApocalypseRequest.getGrid();
        int[][] array=new int[grid][grid];
        int zX= zombieApocalypseRequest.getZombiePos().getX();
        int zY= zombieApocalypseRequest.getZombiePos().getY();


        array[zX][zY]=-1;       //fill zombie
        for(Coordinate coordinate: zombieApocalypseRequest.getCreaturePosList()) {
            array[coordinate.getX()][coordinate.getY()]=1;    //fill creature
        }

        List<Coordinate> zombiesFinalPostitions = new ArrayList<>();
        Queue<Coordinate> zombiesToProcess = new LinkedList<>();
        zombiesToProcess.add(zombieApocalypseRequest.getZombiePos());
        int zombieNum=0;
        while(!zombiesToProcess.isEmpty()) {
            Coordinate zombieCordinates=zombiesToProcess.remove();
            int i=zombieCordinates.getX();
            int j=zombieCordinates.getY();
            ZombieApocalypseUtil.zombieMoving(zombieNum, i, j, array, zombieApocalypseRequest.getDirections(), zombiesFinalPostitions, zombiesToProcess);
            zombieNum++;
        }

        List<Coordinate> creaturesFinalPositions = new ArrayList<>();
        for(Coordinate coordinate: zombieApocalypseRequest.getCreaturePosList()) {
            if(array[coordinate.getX()][coordinate.getY()]>0) {
                creaturesFinalPositions.add(coordinate);
            }
        }

        ZombieApocalypseResponse zombieApocalypseResponse =new ZombieApocalypseResponse();
        zombieApocalypseResponse.setZombiePosList(zombiesFinalPostitions);
        zombieApocalypseResponse.setCreaturePosList(creaturesFinalPositions);

        return zombieApocalypseResponse;
    }



}
