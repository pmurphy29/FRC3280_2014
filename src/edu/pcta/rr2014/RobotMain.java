/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.pcta.rr2014;

import edu.pcta.rr2014.systems.DriveTrain;
import edu.pcta.rr2014.systems.SuperStructure;
import edu.pcta.rr2014.config.DriverConfig;
import edu.pcta.rr2014.config.RobotConfig;
import edu.pcta.rr2014.libs.DataLogger;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class RobotMain extends SimpleRobot {

    //System data logger
    private DataLogger mDataLogger;
    //Driver station joysticks and system
    private Joystick mJoystickLeft = null;
    private Joystick mJoystickRight = null;
    private Joystick mJoystickCo = null;
    private DriverStation mDriverStation = null;
    //Robot systems
    private Compressor mCompressor = null;
    private DriveTrain mDriveTrain = null;
    private SuperStructure mSuperStructure = null;

    public RobotMain() {
        mDataLogger = new DataLogger();
        mDataLogger.log("System booting...");
    }

    public void robotInit() {

        mDataLogger.log("System initializing...");

        try {

            mDriverStation = DriverStation.getInstance();

            mJoystickLeft = new Joystick(DriverConfig.Joysticks.BASE_L);
            mJoystickRight = new Joystick(DriverConfig.Joysticks.BASE_R);
            mJoystickCo = new Joystick(DriverConfig.Joysticks.CO);

            mCompressor = new Compressor(RobotConfig.Pneumatics.PRESSURE,
                    RobotConfig.Pneumatics.CONTROL);

            mDriveTrain = new DriveTrain();
            mSuperStructure = new SuperStructure();

            mDataLogger.log("System ready.");

        } catch (RuntimeException e) {
            mDataLogger.log("System halted.", e);
            System.exit(0);
        }

    }

    public void autonomous() {

        mDataLogger.log("Beginning autonomous.");

        try {
            mSuperStructure.reload(false);
            
            Autonomous.run(mDriveTrain, mSuperStructure);
            mDataLogger.log("Autonomous completed.");
        } catch (RuntimeException e) {
            mDataLogger.log("Autonomous aborted.", e);
        }
    }

    public void operatorControl() {

        mDataLogger.log("Beginning operator control.");

        try {

            mSuperStructure.reload(true);

            if (mCompressor != null) {
                mCompressor.start();
            }

            mDataLogger.log("");
            while (this.isOperatorControl() && mDriveTrain != null && mSuperStructure != null) {
                //mDataLogger.logData(mDriverStation, mJoystickLeft, mJoystickRight, mJoystickCo, mDriveTrain, mSuperStructure);
                mDriveTrain.process(mJoystickLeft, mJoystickRight);
                mSuperStructure.process(mJoystickCo);
            }

        } catch (RuntimeException e) {
            mDataLogger.log("Operator control aborted", e);
        }
    }

    public void disabled() {

        if (mDriveTrain != null) {
            mDriveTrain.stopAll();
        }

        if (mSuperStructure != null) {
            mSuperStructure.stopAll();
        }

        mDataLogger.disable();

    }
}
