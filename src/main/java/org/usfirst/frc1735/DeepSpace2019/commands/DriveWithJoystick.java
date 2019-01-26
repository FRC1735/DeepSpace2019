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

import org.usfirst.frc1735.DeepSpace2019.Robot;
import org.usfirst.frc1735.DeepSpace2019.joysticks.AbstractJoystick;
import org.usfirst.frc1735.DeepSpace2019.joysticks.JoystickFactory;
import org.usfirst.frc1735.DeepSpace2019.joysticks.Role;
import org.usfirst.frc1735.DeepSpace2019.smartdashboard.SmartDashboardKeys;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystick extends Command {
    double joystickDeadzone; 
    JoystickFactory joystickFactory;  
    AbstractJoystick joystickLeft;
    AbstractJoystick joystickRight;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveWithJoystick() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        joystickDeadzone = SmartDashboard.getNumber(SmartDashboardKeys.JOYSTICK_DEADZONE, 0);
        joystickFactory = new JoystickFactory();
        joystickLeft = joystickFactory.get(Robot.oi.joyLeft, Role.DRIVER_LEFT);
        joystickRight = joystickFactory.get(Robot.oi.joyRight, Role.DRIVER_LEFT);

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        Robot.driveTrain.drive(applyDeadzoneFilter(joystickLeft.getX()), applyDeadzoneFilter(joystickLeft.getY()),
                        applyDeadzoneFilter(joystickRight.getX()), applyDeadzoneFilter(joystickRight.getY()));
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }

    private double applyDeadzoneFilter(final double joystickAxisValue) {
        if (Math.abs(joystickAxisValue) < joystickDeadzone) {
            return 0;
        } else {
            return joystickAxisValue;
        }
    }

}
