/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014.systems;

import edu.pcta.rr2014.config.DriverConfig;
import edu.pcta.rr2014.config.RobotConfig;
import edu.pcta.rr2014.libs.Tools;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWM;

/**
 *
 * @author Matthew
 */
public class DriveTrain {

    private boolean mReversed = false;
    private int mValLeft = -1;
    private int mValRight = -1;
    private PWM mPWM_LF = null;
    private PWM mPWM_LR = null;
    private PWM mPWM_RF = null;
    private PWM mPWM_RR = null;

    public DriveTrain() {

        mPWM_LF = new PWM(RobotConfig.DriveTrain.Motors_PWM.LEFTFRONT);
        mPWM_LR = new PWM(RobotConfig.DriveTrain.Motors_PWM.LEFTREAR);
        mPWM_RF = new PWM(RobotConfig.DriveTrain.Motors_PWM.RIGHTFRONT);
        mPWM_RR = new PWM(RobotConfig.DriveTrain.Motors_PWM.RIGHTRREAR);
    }

    public void process(Joystick joyLeft, Joystick joyRight) {
        
        if(joyLeft.getRawButton(DriverConfig.BaseDriver.FWD)){
            mReversed = false;
        }else if(joyLeft.getRawButton(DriverConfig.BaseDriver.REV)){
            mReversed = true;
        }

        if (!mReversed) {
            drive(joyLeft.getY(), joyRight.getY());
        } else {
            drive(-joyRight.getY(), -joyLeft.getY());
        }

    }

    public void drive(double left, double right) {

        mValLeft = Tools.joy2pwm(left);
        mValRight = Tools.joy2pwm(-right);

        mPWM_LF.setRaw(mValLeft);
        mPWM_LR.setRaw(mValLeft);
        mPWM_RF.setRaw(mValRight);
        mPWM_RR.setRaw(mValRight);

    }

    public void stopAll() {

        mValLeft = 0;
        mValRight = 0;

        mPWM_LF.setRaw(Tools.joy2pwm(0));
        mPWM_LR.setRaw(Tools.joy2pwm(0));
        mPWM_RF.setRaw(Tools.joy2pwm(0));
        mPWM_RR.setRaw(Tools.joy2pwm(0));
    }
}
