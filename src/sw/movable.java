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
public interface movable{
  
    // some movables will need to rotate
    // some will accelerate/deccelerate when reaching target
 
    public void decSpeed(float sp);
    public void incSpeed(float sp);
  
    public float getSpeed();
  
    public void setTo(float x, float y);
    public void move(PApplet p);
    public void render(PApplet p);
}