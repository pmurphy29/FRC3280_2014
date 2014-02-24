/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014.config;

/**
 *
 * @author Matthew
 */
public class DriverConfig {
    
    public static final class Joysticks{
        public static final int BASE_L = 1;
        public static final int BASE_R = 2;
        public static final int CO = 3;
    }
    
    public static final class BaseDriver{
        
        public static final int FWD = 11;
        public static final int REV = 10;
        
    }
    
    public static final class CoDriver{
        
        public static final int LOAD_OPEN = 2;
        public static final int LOAD_CLOSE = 4;
        
        public static final int KICK_FIRE = 8;
        public static final int KICK_RELOAD = 6;
        
        public static final int INTAKE_IN = 7;
        public static final int INTAKE_OUT = 5;
        
    }
    
}
