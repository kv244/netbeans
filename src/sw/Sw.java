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
public class Sw extends PApplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       PApplet.main(new String[]{sw.Sw.class.getName()});
    }
 
    // player
    movable m;
    float maxSpeed = 1000;

    // mouse
    float mx, my;

    // sky
    PVector[] sky;
    int maxStars = 100;

    void initSky(){
      for(int i = 0; i < sky.length; i++)
        sky[i] = new PVector(random(width), random(height), random(255));
    }
    
    //size
    @Override
    public void settings() {
        size(800, 600);
    }

    // init
    @Override 
    public void setup(){
      //m = new machine(5, maxSpeed);
      m = new player(this, 1000, "rcket.png");
      sky = new PVector[maxStars];
      initSky();
    }

    // main loop
    @Override 
    public void draw(){
      background(128);
      sky();
      m.move(this);
      m.render(this);
      hud();
    }

    // paint the background
    void sky(){
      pushStyle();
      noStroke();
        for (PVector sky1 : sky) {
            fill(sky1.z, sky1.z, sky1.z);
            circle(sky1.x, sky1.y, 2);
            //point(sky1.x, sky1.y);
            sky1.z = random(255);
            sky1.x += 1 - random(2);
            sky1.x += 1 - random(2);
        }
      popStyle();
    }

    // display
    void hud(){
      pushStyle();
      textSize(20); fill(0, 0, 255);
      String tx = "Speed: " + Float.toString(maxSpeed - m.getSpeed());
      text(tx, 20, 20);
      popStyle();
    }

    // mouse
    @Override
    public void mousePressed(){
      m.setTo(mouseX, mouseY);
    }

    // keys
    @Override
    public void keyPressed(){
      if(key == 'a')
        m.incSpeed(10);
      if(key == 's')
        m.decSpeed(10);
    }
    
}
