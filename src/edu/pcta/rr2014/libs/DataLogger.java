/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014.libs;

import edu.pcta.rr2014.systems.DriveTrain;
import edu.pcta.rr2014.systems.SuperStructure;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Matthew
 */
public class DataLogger {
    
    public DataLogger(){
    }
    
    public void log(String s){
        System.out.println(s);
    }
    
    public void  log(String s, Exception ex){
        System.out.println(s);
        System.out.println(ex);
    }
    
    public void logData(DriverStation ds, Joystick left, Joystick right, Joystick co, DriveTrain dt, SuperStructure ss){
        System.out.println("Data: B:" + ds.getBatteryVoltage() + " L:" + left.getY() + " R:" + right.getY());
    }
    
    public void disable(){
        System.out.println("Disabled.");
    }
    
}
