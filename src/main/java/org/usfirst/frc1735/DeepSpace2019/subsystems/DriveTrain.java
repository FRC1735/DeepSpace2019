// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.DeepSpace2019.subsystems;


// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc1735.DeepSpace2019.SmartDashboardKeys;
import org.usfirst.frc1735.DeepSpace2019.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX leftMotor;
    private WPI_TalonSRX rightMotor;
    private DifferentialDrive differentialDrive1;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public DriveTrain() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        leftMotor = new WPI_TalonSRX(1);
        
        
        
        rightMotor = new WPI_TalonSRX(2);
        
        
        
        differentialDrive1 = new DifferentialDrive(leftMotor, rightMotor);
        addChild("Differential Drive 1",differentialDrive1);
        differentialDrive1.setSafetyEnabled(true);
        differentialDrive1.setExpiration(0.1);
        differentialDrive1.setMaxOutput(1.0);

        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoystick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    }

    public void driveWithJoystick(Joystick joystick) {
        double joystickX, joystickY;
        
        joystickX = joystick.getX();
        joystickY = joystick.getY();

        double joystickDeadzone = SmartDashboard.getNumber(SmartDashboardKeys.JOYSTICK_DEADZONE, 0);
        if (Math.abs(joystickX) < joystickDeadzone) {
            joystickX = 0;
        }
        if (Math.abs(joystickY) < joystickDeadzone) {
            joystickY = 0;
        }

        differentialDrive1.arcadeDrive(-joystickY, joystickX, true);
    }
    
    public void stop() {
        differentialDrive1.stopMotor();
    }

    public void setArcadeMode() {
        SmartDashboard.putString(SmartDashboardKeys.DRIVETRAIN_MODE, "ARCADE");    
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

