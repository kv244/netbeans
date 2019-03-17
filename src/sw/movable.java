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
    
    private float mxSpeed;
    private PVector speed;
    // position
    protected PVector position;
    
    // destination
    private PVector destination;
    private float distance;
    // orientation
    private float angle = 0.0f;
    
    // flags
    private boolean isMoving;
    private boolean isRotating;
  
    public movable(PApplet p, float maxSpeed){
        mxSpeed = maxSpeed;
        destination = new PVector(p.width/2, p.height/2);
        position = new PVector(p.width / 2, p.height/2);
        speed = new PVector(5, 5);
        
        distance = 0;
        isMoving = false;
        isRotating = false;
        speed.normalize();
    }

    public float getAngle(){
        return angle;
    }
    
    public float getSpeed(){
        return speed.mag();
    }
    
    public void setSpeed(float s){
        speed.setMag(s);
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
    
    // TODO
    public void decSpeed(float sp){}
    public void incSpeed(float sp){}
  
    // new heading set
    public void setTo(float x, float y){
        destination = new PVector(x, y);
        speed = PVector.sub(destination, position);
        speed.normalize();
        angle = speed.heading();
        /*
        System.out.println("dest " + destination.toString());
        System.out.println("post " + position.toString());
        */
    }
       
    // called every loop before rendering
    public void move(PApplet p){
        distance = position.dist(destination);
        isMoving = Math.abs(distance) > 1.0f;
        
        //System.out.println(distance);
        if(isMoving)
            position.add(speed);
        // TODO add shake?
    }
    
    public abstract void render(PApplet p);
}