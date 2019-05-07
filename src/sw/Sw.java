/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


// TODO
//  Move missiles to player? movable?
//  Calculate missiles setTo and hit condition; notification out of screen, hit
//  Missiles speed
//  Missiles rendering (line depending orientation)

package sw;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import processing.core.*;
import javax.sound.midi.*;

/**
 *
 * @author razvanjulianpetrescu
 */
public class Sw extends PApplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       PApplet.main(new String[]{
           sw.Sw.class.getName()
       });
    }
 
    // set to true to enable initial midi player
    private final boolean withSound = false;
    
    // player
    private player m;
    private final gameState gs = new gameState();
    private File midiFile = null;
    private Sequencer sequencer = null;

    // missiles
    // to move to player?
    public ArrayList<missiles> missiles; // make private, render public
    
    // mouse
    float mx, my;

    // sky
    PVector[] sky;
    int maxStars = 1000;
    
    // intro screen
    PShape ptrIntro;

    //size
    @Override
    public void settings() {
        size(800, 600);
    }    
    
    // init
    @Override 
    public void setup(){
      
      m = new player(this, 10, "rcket.png");
      m.setPosition(new PVector(width/2, height/2));
      m.setTo(new PVector(width/2, height/2));
      
      missiles = new ArrayList<>();
      
      sky = new PVector[maxStars];
      initSky();
      
      ptrIntro = loadShape("sw.svg");
      if(withSound)
        midiFile = new File("sw.mid");
    }

    // main loop
    @Override 
    public void draw(){
      background(255, 255, 255);
      
      switch(gs.getState()){
          case INIT:
            renderIntro();
            break;
            
          case RUNNING:
            sky(true);
            m.move(this);
            m.render(this);
            moveMissiles(this);
            renderMissiles(this);
            hud();
            break;
            
          case ENDED:
              renderIntro();
              break;
      }          
    }
    
    // missile handlers, to move to player? TODO
    public void moveMissiles(PApplet p){
        missiles.forEach((mm) -> {
            mm.move(p);
        });
   
    }
    
    
    // renders missiles and removes if they are out of screen
    public void renderMissiles(PApplet p){
        ArrayList<missiles> copyMissiles = new ArrayList(this.missiles);
        this.missiles.forEach((mm) -> {
            mm.render(p);
        
            if(mm.gotOut())
                copyMissiles.remove(mm);
        });
        
        this.missiles = copyMissiles;
        
    }
    
    public void addMissile(PApplet p){
        missiles newMissile = new missiles(p, 5);
        newMissile.setPosition(m.position);
        newMissile.setTo(m.destination);
        missiles.add(newMissile);
    }
    // end missiles
     
    // init sky
    void initSky(){
      for(int i = 0; i < sky.length; i++)
        sky[i] = new PVector(random(width), random(height), random(255));
    }

    // intro
    void renderIntro(){
        shape(ptrIntro, 0, 0, width, height);
        if(withSound){
            try{
                sequencer = MidiSystem.getSequencer();
                sequencer.open();
                Sequence sequence = MidiSystem.getSequence(midiFile);
                sequencer.setSequence(sequence);
                sequencer.start();
            }catch(IOException | InvalidMidiDataException | MidiUnavailableException x){
                System.out.println("Error playing sound: " + x.getMessage());
            }
        }
    }

    // paint the background
    void sky(boolean fx){
      if(fx == false)
          return;
      
      pushStyle();
      noStroke();
      for (PVector sky1 : sky) {
        fill(sky1.z);
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
      String tx = "Speed: " + Float.toString(m.getSpeed()) + " / " + Float.toString(m.getMaxSpeed());
      text(tx, 20, 20);
      popStyle();
    }

    // mouse
    // TODO finish other states
    @Override
    public void mousePressed(){
        
          switch(gs.getState()){
            case INIT:
                if(sequencer != null)
                    sequencer.stop();
                gs.nextState();
                break;
            case RUNNING:
                m.setTo(mouseX, mouseY);
                break;
            case ENDED:
                break;
          }
    }

    // keys
    @Override
    public void keyPressed(){
        if(key == CODED && keyCode == ESC)
            System.exit(0);
        
        if(gs.getState() == gameState.state.RUNNING){
            if(key == 'a'){
                m.incSpeed(m.getSpeedIncrease());
            }
            
            if(key == 's')
                m.decSpeed(m.getSpeedIncrease());
            
            if(key == ' '){
                addMissile(this);
            }    
            
            // debug
            if(key == 'z'){
                System.out.println(m.position);
                missiles.stream().map((x) -> {
                    System.out.print(x.getMissile());
                    return x;
                }).map((x) -> {
                    System.out.print(" ");
                    return x;
                }).forEachOrdered((x) -> {
                    System.out.println(x.position);
                });
            }
        }
    }   
}
