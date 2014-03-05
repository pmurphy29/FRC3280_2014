/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014.systems;

import edu.pcta.rr2014.config.DriverConfig;
import edu.pcta.rr2014.config.RobotConfig;
import edu.pcta.rr2014.libs.Tools;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;

/**
 *
 * @author Matthew
 */
public class SuperStructure {
    
    private DoubleSolenoid mFiringSolenoid;
    private DoubleSolenoid mLoadingSolenoid;
    private PWM mPWM_Roller = null;
    private PWM mPWM_Winch = null;
    private DigitalInput mLoaderLimit = null;
    
    public SuperStructure() {
        
        mFiringSolenoid = new DoubleSolenoid(RobotConfig.SuperStructure.Pneumatics.FIRE_F, RobotConfig.SuperStructure.Pneumatics.FIRE_R);
        mLoadingSolenoid = new DoubleSolenoid(RobotConfig.SuperStructure.Pneumatics.LOAD_F, RobotConfig.SuperStructure.Pneumatics.LOAD_R);
        
        mPWM_Roller = new PWM(RobotConfig.SuperStructure.Motors_PWM.ROLLER);
        mPWM_Winch = new PWM(RobotConfig.SuperStructure.Motors_PWM.WINCH);
        
        mLoaderLimit = new DigitalInput(RobotConfig.SuperStructure.Limits.LOAD_LOWER);
        
    }
    
    public void process(Joystick joystick) {
        
        if (joystick.getRawButton(DriverConfig.CoDriver.LOAD_CLOSE) && isArmed) {
            mLoadingSolenoid.set(DoubleSolenoid.Value.kForward);
        } else if (joystick.getRawButton(DriverConfig.CoDriver.LOAD_OPEN)) {
            mLoadingSolenoid.set(DoubleSolenoid.Value.kReverse);
        }
        
        if (joystick.getRawButton(DriverConfig.CoDriver.INTAKE_IN) && (mLoadingSolenoid.get() == DoubleSolenoid.Value.kReverse)) {
            mPWM_Roller.setRaw(Tools.joy2pwm(-1.0));
        } else if (joystick.getRawButton(DriverConfig.CoDriver.INTAKE_OUT)) {
            mPWM_Roller.setRaw(Tools.joy2pwm(1.0));
        } else {
            mPWM_Roller.setRaw(Tools.joy2pwm(0));
        }
        
        if (mLoadingSolenoid.get() == DoubleSolenoid.Value.kReverse) {
            if (joystick.getRawButton(DriverConfig.CoDriver.KICK_FIRE) && isArmed && !isReloading) {
                System.out.println("Firing.");
                mFiringSolenoid.set(DoubleSolenoid.Value.kReverse);
                isArmed = false;
                reload(true);
            } else if (joystick.getRawButton(DriverConfig.CoDriver.KICK_RELOAD) && !isArmed && !isReloading) {
                reload(true);
            }
        }
        
    }
    private boolean isReloading = false;
    private boolean isArmed = false;
    
    public void reload(boolean threaded) {
        
        isReloading = true;
        
        if (threaded) {
            new Thread() {
                public void run() {
                    reload();
                }
            }.start();
        } else {
            reload();
        }
    }
    
    private void reload() {
        try {
            if (mLoadingSolenoid.get() != DoubleSolenoid.Value.kReverse) {
                mLoadingSolenoid.set(DoubleSolenoid.Value.kReverse);
            }
            
            if (!mLoaderLimit.get()) {
                System.out.println("Reloading...");
                mPWM_Winch.setRaw(Tools.joy2pwm(1.0));
                while (!mLoaderLimit.get()) {
                }
                mPWM_Winch.setRaw(Tools.joy2pwm(0));
                mFiringSolenoid.set(DoubleSolenoid.Value.kForward);
                mPWM_Winch.setRaw(Tools.joy2pwm(-1.0));
                Thread.sleep(5500);
                mPWM_Winch.setRaw(Tools.joy2pwm(0));
            } else {
                mFiringSolenoid.set(DoubleSolenoid.Value.kForward);
                System.out.println("System already reloaded.");
            }
        } catch (Exception e) {
        } finally {
            isArmed = true;
            isReloading = false;
        }
        System.out.println("Done reloading.");
    }
    
    public void stopAll() {
        mPWM_Roller.setRaw(Tools.joy2pwm(0));
        mPWM_Winch.setRaw(Tools.joy2pwm(0));
    }
        public void fire(){
                System.out.println("Firing.");
                mLoadingSolenoid.set(DoubleSolenoid.Value.kReverse);
                try{
                    Thread.sleep(200);
                }
                catch(InterruptedException ex){
                    System.out.println("auto interrupted");
                }
                mFiringSolenoid.set(DoubleSolenoid.Value.kReverse);
                try{Thread.sleep(200);
                }
                catch(InterruptedException ex){
                    
                }
                isArmed = false;
                reload(true);
    }
}
