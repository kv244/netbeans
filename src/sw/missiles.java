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
    
    private boolean isOut = false; // out of screen notifier
    private static int noMissiles = 0;
    private int currentMissile = 0;
   
    public boolean gotOut(){
        return isOut;
    }
    
    // ctor
    public missiles(PApplet p, float maxSpeed){
        super(p, maxSpeed);
        speed = new PVector(7, 7); // initial speed
        speed.normalize(); // speed set to 1 by default
        
        currentMissile = noMissiles++;
    }
    
    @Override
    public void move(PApplet p){
        super.move(p);
        if(inScreen(p) == false)
            isOut = true;
    }

    @Override
    public void render(PApplet p) {
        
        p.pushStyle();
        p.stroke(3);
        p.fill(128, 0, 0);
        p.line(position.x, position.y, position.x + speed.x * 2, position.y + speed.y * 2);
        p.popStyle();
    }
    
    public int getMissile(){
        return currentMissile;
    }
    
    @Override 
    public boolean hitCondition(){
        return true;
    }
}
