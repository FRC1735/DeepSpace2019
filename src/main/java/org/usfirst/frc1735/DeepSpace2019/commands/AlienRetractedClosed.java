// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.DeepSpace2019.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc1735.DeepSpace2019.subsystems.*;
import org.usfirst.frc1735.DeepSpace2019.Robot;

/**
 *
 */
public class AlienRetractedClosed extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AlienRetractedClosed() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    /*
        final AlienDeployer.State alienDeployerState = Robot.alienDeployer.getState();
        final HatchGrabber.State hatchGrabberState = Robot.hatchGrabber.getState();
        
        System.out.println("AlienRetractedClosed, alienDeployerState: " + alienDeployerState.name() + ", hatchGrabberState: " + hatchGrabberState.name());

        if (alienDeployerState.equals(AlienDeployer.State.EXTENDED)
            && hatchGrabberState.equals(HatchGrabber.State.CLOSED)) {
            // retreat alien
            addSequential(new AlienDeploy(AlienDeployer.in));
        } else  if (alienDeployerState.equals(AlienDeployer.State.EXTENDED)
            && hatchGrabberState.equals(HatchGrabber.State.OPENED)) {
            // release hatch 
            addSequential(new HatchManipulator(HatchGrabber.in));
            // retreat alien
            addSequential(new AlienDeploy(AlienDeployer.in));
        }   
        */

        // release hatch 
        addSequential(new HatchManipulator(HatchGrabber.in));
        // retreat alien
        addSequential(new AlienDeploy(AlienDeployer.in));
    } 
}
