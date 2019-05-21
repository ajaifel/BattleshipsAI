package com.company;

import java.awt.*;
import java.util.ArrayList;

class AI {

    boolean hitResult;
    boolean taskComplete;
    private int huntModeActive;
    private int targets;
    private int hitLocationX;
    private int hitLocationY;
    private Point[] shotList;
    private Point[] hitList;
    private Point[] missList;
    private ArrayList<Point> huntList = new ArrayList<>();
    private int huntCount;
    private int missCount;
    private int hitCount;
    private int huntProgress;
    private int shotCount;

    AI(int s, int t) { //initialising all variables
        hitResult = false;
        taskComplete = false;
        targets = t;
        missCount = 0;
        hitCount = 0;
        huntCount = 0;
        huntProgress = 0;
        shotList = new Point[(s * s)/2];
        hitList = new Point[s * s];
        missList = new Point[s * s];
        int c = 0;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s; j++) {
                if ((i + j) % 2 == 0){
                    shotList[c] = new Point(i,j); // filling shotList with checkerboard search pattern
                    c++;
                }
            }
        }

    }

    int getHitLocationX() {
        return hitLocationX;
    }

    int getHitLocationY() {
        return hitLocationY;
    }

    void nextTurn() {
        logHitResult(); // adds shotList data to either miss or hit lists
        if (hitResult) huntMode(); // activates huntmode
        if (huntModeActive > 0){
            hitLocationX = huntList.get(huntProgress).x;
            hitLocationY = huntList.get(huntProgress).y;
            huntProgress++;
            huntModeActive--;
        } else {
            while (alreadyHit(shotList[shotCount].x, shotList[shotCount].y)) {
                shotCount++;
            }
            hitLocationX = shotList[shotCount].x;
            hitLocationY = shotList[shotCount].y;
            shotCount++;
        }
    }

    private void huntMode() {                                   // adds surrounding squares to huntList
        if (!alreadyHit(hitLocationX+1, hitLocationY)){      // tests to see if they're already shot at
            if (hitLocationX < 9) {                             // doesn't add if they're on a border
                huntList.add(new Point(hitLocationX + 1, hitLocationY));
                huntCount++;
                huntModeActive++;
            }
        }
        if (!alreadyHit(hitLocationX, hitLocationY+1)){
            if (hitLocationY < 9) {
                huntList.add(new Point(hitLocationX, hitLocationY + 1));
                huntCount++;
                huntModeActive++;
            }
        }
        if (!alreadyHit(hitLocationX-1, hitLocationY)){
            if (hitLocationX > 0) {
                huntList.add(new Point(hitLocationX - 1, hitLocationY));
                huntCount++;
                huntModeActive++;
            }
        }
        if (!alreadyHit(hitLocationX, hitLocationY-1)){
            if (hitLocationY > 0) {
                huntList.add(new Point(hitLocationX, hitLocationY - 1));
                huntCount++;
                huntModeActive++;
            }
        }
    }

    private boolean alreadyHit(int x, int y) { // tests locations to see if already shot at prior
        for (int i = 0; i < hitCount; i++){
            if (x == hitList[i].x && y == hitList[i].y){
                return true;
            }
        }
        for (int i = 0; i < missCount; i++) {
            if (x == missList[i].x && y == missList[i].y) {
                return true;
            }
        }
        return false;
    }

    private void logHitResult() { // saves hit data to the respective lists
        if (hitResult){
            targets--;
            if (targets == 0){
                taskComplete = true;
            }

            hitList[hitCount] = new Point(shotList[shotCount].x, shotList[shotCount].y);
            hitCount++;

        } else {
            missList[missCount]= new Point(shotList[shotCount].x, shotList[shotCount].y);
            missCount++;
        }
    }
}
