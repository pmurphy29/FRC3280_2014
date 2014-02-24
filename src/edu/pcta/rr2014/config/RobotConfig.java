/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014.config;

/**
 *
 * @author Matthew
 */
public class RobotConfig {

    public static final class Pneumatics {

        public static final int PRESSURE = 1;
        public static final int CONTROL = 1;
    }

    public static final class DriveTrain {

        public static final class Motors_PWM {

            public static final int LEFTFRONT = 2;
            public static final int LEFTREAR = 6;
            public static final int RIGHTFRONT = 5;
            public static final int RIGHTRREAR = 4;
        }

        public static final class Encoders {

            public static final int LEFT = -1;
            public static final int RIGHT = -1;
        }
    }

    public static final class SuperStructure {

        public static final class Motors_PWM {

            public static final int WINCH = 1;
            public static final int ROLLER = 3;
        }

        public static final class Pneumatics {

            public static final int FIRE_F = 4;
            public static final int FIRE_R = 3;
            public static final int LOAD_F = 2;
            public static final int LOAD_R = 1;
        }
        
        public static final class Limits{
            
            public static final int LOAD_LOWER = 2;
        }
    }
}
