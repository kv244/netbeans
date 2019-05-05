/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import processing.core.*;

/**
 * missile class
 * image represented by dots or other drawable
 * @author julianp
 */
public class missiles extends movable {
    
    private boolean isOut = false;
    private static int noMissiles = 0;
    private int currentMissile = 0;
    
    
    // TODO add move which sets isOut and hit?
    public boolean gotOut(){
        return isOut;
    }
    
    // ctor
    public missiles(PApplet p, float maxSpeed){
        super(p, maxSpeed);
        currentMissile = noMissiles++;
    }

    @Override
    public void render(PApplet p) {
        // System.out.println("Missile rendered " + position.toString());
        p.pushStyle();
        p.noStroke();
        p.fill(128, 0, 0);
        p.circle(position.x, position.y, currentMissile * 2);
        p.popStyle();
    }
    
    public int getMissile(){
        return currentMissile;
    }
}
