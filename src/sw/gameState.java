/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw;

/**
 *
 * @author razvanjulianpetrescu
 */
public class gameState {
    public enum state{
        INIT, 
        RUNNING, 
        ENDED
    }
    
    private state _state;
    
    public gameState(){
        _state = state.INIT;
    }
    
    public state getState(){
        return _state;
    }
    
    public void nextState(){
        switch(_state){
            case INIT:
                _state = state.RUNNING; break;
            case RUNNING:
                _state = state.ENDED; break;
            case ENDED:
                _state = state.INIT; break;
                
        }
    }
}
