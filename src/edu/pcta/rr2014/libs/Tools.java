/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014.libs;

/**
 *
 * @author Matthew
 */
public class Tools {

    public static int joy2pwm(double joyVal) {

        int pwmVal = (int) ((joyVal * 127) + 127);

        pwmVal = (pwmVal >= 255) ? 254 : pwmVal;
        pwmVal = (pwmVal <= 0) ? 1 : pwmVal;

        return pwmVal;
    }

    public static double pwm2joy(int pwmVal) {
        return 0;
    }
}