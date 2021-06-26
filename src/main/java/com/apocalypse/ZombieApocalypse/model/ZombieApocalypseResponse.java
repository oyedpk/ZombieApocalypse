package com.apocalypse.ZombieApocalypse.model;

import java.util.List;

public class ZombieApocalypseResponse {

    private List<Coordinate> zombiePosList;
    private List<Coordinate> creaturePosList;

    public List<Coordinate> getZombiePosList() {
        return zombiePosList;
    }

    public void setZombiePosList(List<Coordinate> zombiePosList) {
        this.zombiePosList = zombiePosList;
    }

    public List<Coordinate> getCreaturePosList() {
        return creaturePosList;
    }

    public void setCreaturePosList(List<Coordinate> creaturePosList) {
        this.creaturePosList = creaturePosList;
    }
}
