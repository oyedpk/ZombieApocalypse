package com.apocalypse.ZombieApocalypse.util;

import com.apocalypse.ZombieApocalypse.exception.InvalidRequestException;
import com.apocalypse.ZombieApocalypse.model.Coordinate;
import com.apocalypse.ZombieApocalypse.model.ZombieApocalypseRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Queue;


public class ZombieApocalypseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZombieApocalypseUtil.class);

    public static void zombieMoving(int zombie, int zX, int zY, int[][] array, String directionStr
            , List<Coordinate> zombiesFinalPostitions, Queue<Coordinate> zombiesToProcess) {
        for (char c : directionStr.toCharArray()) {
            switch (c) {
                case 'D':
                    zY = zY == array[0].length-1 ? 0 : zY+1;
                    break;
                case 'U':
                    zY = zY == 0 ? array[0].length-1 : zY-1;
                    break;
                case 'R':
                    zX = zX == array[0].length-1 ? 0 : zX+1;
                    break;
                case 'L':
                    zX = zX == 0 ? array[0].length-1 : zX-1;
                    break;
            }
            if (array[zX][zY] > 0) {
                LOGGER.info("zombie {} infected creature at ({},{})", zombie, zX, zY);
                zombiesToProcess.add(new Coordinate(zX, zY));
                array[zX][zY] = -1;
            } else {
                LOGGER.info("zombie {} moved to ({},{})", zombie, zX, zY);
            }
        }
        zombiesFinalPostitions.add(new Coordinate(zX, zY));
    }


    public static void validateZombieApocalypse(ZombieApocalypseRequest zombieApocalypseRequest) throws InvalidRequestException {
        if(Objects.isNull(zombieApocalypseRequest) || Objects.isNull(zombieApocalypseRequest.getZombiePos()) ||
         Objects.isNull(zombieApocalypseRequest.getDirections())
                || Objects.isNull(zombieApocalypseRequest.getCreaturePosList())
                ||  zombieApocalypseRequest.getCreaturePosList().isEmpty()
                || Objects.isNull(zombieApocalypseRequest.getGrid())) {
            LOGGER.error("Invalid Request");
            throw new InvalidRequestException("Invalid Request : Some fields are null");
        }
        for(char c:zombieApocalypseRequest.getDirections().toCharArray()) {
            if(c!='R' && c!='U' && c!='D' && c!='L') {
                LOGGER.error("Invalid directions");
                throw new InvalidRequestException("Invalid directions : valid directions D R L U");
            }
        }
        if(Objects.isNull(zombieApocalypseRequest.getZombiePos().getX())
                || Objects.isNull(zombieApocalypseRequest.getZombiePos().getX())) {
            LOGGER.error("Invalid ZombiePos");
            throw new InvalidRequestException("Invalid ZombiePos : valid int required");
        } else if(zombieApocalypseRequest.getZombiePos().getX()>=zombieApocalypseRequest.getGrid() ||
                zombieApocalypseRequest.getZombiePos().getY()>=zombieApocalypseRequest.getGrid()) {
            LOGGER.error("Invalid ZombiePos");
            throw new InvalidRequestException("Invalid ZombiePos : value shoudl be less than grid size");
        }

        for(Coordinate coordinate:zombieApocalypseRequest.getCreaturePosList()) {
            if(Objects.isNull(coordinate.getX())
                    || Objects.isNull(coordinate.getY())) {
                LOGGER.error("Invalid CreaturePos");
                throw new InvalidRequestException("Invalid CreaturePos : valid int required");
            } else if(coordinate.getX()>=zombieApocalypseRequest.getGrid() ||
                    coordinate.getY()>=zombieApocalypseRequest.getGrid()) {
                LOGGER.error("Invalid CreaturePos");
                throw new InvalidRequestException("Invalid CreaturePos : value shoudl be less than grid size");
            }
        }
    }

}
