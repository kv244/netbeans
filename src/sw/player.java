/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

import java.util.ArrayList;
import java.util.Objects;
import processing.core.*;

/**
 *
 * @author razvanjulianpetrescu
 */
class player extends movable{
  
  // TODO: 
  // make smoother rotation
  // add flares

    private PImage ptrImage;
    

    // ctor
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
        if(inScreen(p) || inScreen(p, destination))
            super.move(p);

        currentAngle += rotAngle;
        if(currentAngle >= super.getAngle()){
            //currentAngle = 0.0f;
            super.setRotate(false);
        }  
    }
    
    @Override 
    public void render(PApplet p){
        p.pushMatrix();
        p.imageMode(PApplet.CENTER);
        p.translate(position.x, position.y);
        p.rotate(super.getAngle());
        p.image(ptrImage, 0, 0);
        p.popMatrix();
    } 
}