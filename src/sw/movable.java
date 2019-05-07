/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// when reached destination, start stalling

package sw;
import processing.core.*;

/**
 *
 * @author razvanjulianpetrescu
 * make this class and extend
 */
public abstract class movable{
  
    // some movables will need to rotate
    // some will accelerate/deccelerate when reaching target
    
    private final float maxSpeed;
    private float intSpeed; // speed multiplier
    protected PVector speed;
    private final float speedIncrease = 0.5f;
    
    // position
    protected PVector position;
    
    // destination
    protected PVector destination;
    protected float distance;
    
    // orientation
    private float angle = 0.0f;
    
    // flags
    private boolean isMoving;
    private boolean isRotating;
    
    // rotation properties
    protected float currentAngle = 0.0f;
    protected final float rotAngle = (float)Math.PI / 10;
  
    public void setPosition(PVector v){
        position = new PVector(v.x, v.y);
    }
    
    // ctor
    public movable(PApplet p, float maxSpeed){
        this.maxSpeed = maxSpeed;
        
        // move to descendent
        
        // TODO this needs to be moved
        
        distance = 0.0f;
        isMoving = false;
        isRotating = false;
        
        intSpeed = 1.0f;
    }
    
    public float getMaxSpeed(){
        return maxSpeed;
    }

    public float getAngle(){
        return angle;
    }
    
    public float getSpeed(){
        return speed.mag();
    }
    // internal update of speed vector to the updated scalar speed
    private void setSpeed(float s){
        speed.setMag(s);
    }
    
    public float getSpeedIncrease(){
        return this.speedIncrease;
    }
    
    public void setMove(boolean m){
        isMoving = m;
    }
    
    public boolean getMove(){
        return isMoving;
    }
    
    public void setRotate(boolean r){
        isRotating = r;
    }
    
    public boolean getRotate(){
        return isRotating;
    }
    
    // increases the multiplier
    public void incSpeed(float sp){
        intSpeed += ( intSpeed < this.maxSpeed ) ? sp : 0;
        setSpeed(intSpeed);
    }
    public void decSpeed(float sp){
        intSpeed -= ( intSpeed - sp > 0 ) ? sp : 0;
        setSpeed(intSpeed);
    }
    
    public void setTo(PVector v){
        setTo(v.x, v.y);
    }
  
    // new heading set (response to click to determine new bearing)
    public void setTo(float x, float y){
        destination = new PVector(x, y);
        speed = PVector.sub(destination, position);
        speed.normalize();
        setSpeed(intSpeed);
        angle = speed.heading();
        
        setRotate(true); // if rotable TBD
        currentAngle = 0.0f;
        
    }
       
    // called every loop before rendering
    // update location
    public void move(PApplet p){
        //System.out.println("moved " + this.getClass().getName());
        distance = position.dist(destination);
        isMoving = hitCondition(); 
            // stop condition is hitting destination, so it will never go out
            // for other movables, the condition may be different TBD
            // TODO this however can be missed!
        
        if(isMoving)
            position.add(speed); 
    }
    
    /**
     * Determine if a hit is recorded
     * Has to be overridden by each subclass
     * For player, when distance is hit
     * For missile, when hit target?
     * @return true while NOT hit
    */
    public abstract boolean hitCondition();
    
    // is it in screen
    // handling of this is left to descendents
    // optional parameter: vector, other than self, to determine 
    // in screen property for
    protected boolean inScreen(PApplet p, PVector ... which){
        PVector v;
        if(which.length == 0)
            v = this.position;
        else
            v = (PVector)which[0];
        
        return v.x >= 0 && v.x <= p.width 
                && v.y >= 0 && v.y <= p.height;
    }
    
    public abstract void render(PApplet p);
}