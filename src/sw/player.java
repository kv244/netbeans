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
class player extends movable{
  
  // TODO: 
  // make smoother rotation
  // add flares
  // add max speed + speed control

  private PImage ptrImage;
  private float currentAngle = 0.0f;
  private final float rotAngle = (float)Math.PI / 10;
  
  public player(PApplet p, float maxSpeed, String imgPath){
    
    super(p, maxSpeed);
    
    try{
        
      ptrImage = p.loadImage(imgPath);
    }catch(Exception x){
      System.out.println("Error creating player: " + x.getMessage());
    }
  }
  
  // also set for movement
  @Override
    public void move(PApplet p){
        //System.out.println("super angle " + Float.toString(super.getAngle()));
        
        super.move(p);
        
        currentAngle += rotAngle;
        if(currentAngle >= super.getAngle()){
            //currentAngle = 0.0f;
            super.setRotate(false);
        }  
    }
    
    @Override
    public void setTo(float x, float y){
        super.setTo(x, y);
        super.setRotate(true);
        currentAngle = 0.0f;
        
    }
    
    @Override 
    public void render(PApplet p){
        // this needs to be set to center
        p.pushMatrix();
        p.imageMode(PApplet.CENTER);
        p.translate(position.x, position.y);
        p.rotate(super.getAngle());
        p.image(ptrImage, 0, 0);
        p.popMatrix();
    } 
}

