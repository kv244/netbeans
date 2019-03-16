/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import processing.core.*;

/**
 *
 * @author razvanjulianpetrescu
 */
class player implements movable{
  
  // make smoother rotation
  // add flares

  private float mxSpeed;
  private PImage ptrImage;
  private float speed = 650;
  private float px;
  private float py;
  private float dx; private float dy; 
  private float toX; private float toY;
  float angle = 0.0f;
  
  
  public player(PApplet p, float maxSpeed, String imgPath){
    mxSpeed = maxSpeed;
    px = 0; py = 0; toX = 0; toY = 0; dx = 0; dy = 0;

    
    try{
        
      ptrImage = p.loadImage(imgPath);
    }catch(Exception x){
      System.out.println("Error creating player: " + x.getMessage());
    }
  }
  
  
  @Override
    public void decSpeed(float sp){}
  @Override
    public void incSpeed(float sp){}
  
  @Override
    public float getSpeed(){
      return speed;
    }
  
   private void calcDelta(){
    dx = (toX - px) / speed;
    dy = (toY - py) / speed;
   
    angle = (float) Math.atan2((double)(toY - py), (double)(toX - px));
  } 
  
  @Override
    public void setTo(float x, float y){
      toX = x; toY = y;
      calcDelta();
    }
    
    @Override
    public void move(PApplet p){
      if( px >= 0 && px <= p.width) 
        px += dx;
      if( py >= 0 && py <= p.height)
        py += dy;
    }
    
    @Override 
    public void render(PApplet p){
      p.pushMatrix();
      p.translate(px, py);
      p.rotate(angle);
      p.image(ptrImage, 0, 0);
      p.popMatrix();
    } 
}

