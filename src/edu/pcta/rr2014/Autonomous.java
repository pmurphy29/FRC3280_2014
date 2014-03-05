/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pcta.rr2014;

import edu.pcta.rr2014.systems.DriveTrain;
import edu.pcta.rr2014.systems.SuperStructure;

/**
 *
 * @author Matthew
 */
public class Autonomous {
    
      public static void run(DriveTrain driveTrain, SuperStructure superStructure, boolean delayed){
        driveTrain.drive(.35, .35);
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException ex){
            System.out.println("Auto sleep interrupted");
        }
        if(delayed){
            try{
                System.out.println("4 second delay");
                Thread.sleep(4000);
            }
            catch(InterruptedException ex){
             System.out.println("Auto delay interrupted");   
            }
        }
        driveTrain.drive(0,0);
        superStructure.fire();
        
        
    }
    
}

    
}
