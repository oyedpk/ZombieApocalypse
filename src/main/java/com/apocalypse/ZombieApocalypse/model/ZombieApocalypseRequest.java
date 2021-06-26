package com.apocalypse.ZombieApocalypse.model;

import java.util.List;

public class ZombieApocalypseRequest {

    private Integer grid;
    private Coordinate zombiePos;
    private List<Coordinate> creaturePosList;
    private String directions;

    public Integer getGrid() {
        return grid;
    }

    public void setGrid(Integer grid) {
        this.grid = grid;
    }

    public Coordinate getZombiePos() {
        return zombiePos;
    }

    public void setZombiePos(Coordinate zombiePos) {
        this.zombiePos = zombiePos;
    }

    public List<Coordinate> getCreaturePosList() {
        return creaturePosList;
    }

    public void setCreaturePosList(List<Coordinate> creaturePosList) {
        this.creaturePosList = creaturePosList;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }
}
