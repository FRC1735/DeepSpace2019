// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1735.DeepSpace2019;

import org.usfirst.frc1735.DeepSpace2019.commands.AlienAttackLight;
import org.usfirst.frc1735.DeepSpace2019.commands.AutonomousCommand;
import org.usfirst.frc1735.DeepSpace2019.commands.AutonomousExperiment;
import org.usfirst.frc1735.DeepSpace2019.commands.DriveWithPID;
import org.usfirst.frc1735.DeepSpace2019.commands.GetDefaultPIDs;
import org.usfirst.frc1735.DeepSpace2019.commands.LightsOff;
import org.usfirst.frc1735.DeepSpace2019.commands.LightsOn;
import org.usfirst.frc1735.DeepSpace2019.commands.OrangeLight;
import org.usfirst.frc1735.DeepSpace2019.commands.ResetGyro;
import org.usfirst.frc1735.DeepSpace2019.commands.Turn;
import org.usfirst.frc1735.DeepSpace2019.commands.UpdateDebugEnable;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

    //Manually define all Joystick button objects instead of using RobotBuilder...
    public JoystickButton tankMode;
    public JoystickButton arcadeMode;
    public JoystickButton clawIn;
    public JoystickButton clawOut;
    public JoystickButton alienRetreat;
    public JoystickButton alienAttack;
    public JoystickButton grabHatch;
    public JoystickButton releaseHatch;
    public JoystickButton tareArm;
    public JoystickButton armPresetForwardBallPickUp;
    public JoystickButton armPresetForwardHatchPickUp;
    public JoystickButton armPresetForwardRocketOneDrop;
    public JoystickButton armPresetUp;
    public JoystickButton armPresetBackwardBallPickUp;
    public JoystickButton armPresetBackwardHatchPickUp;
    public JoystickButton armPresetBackwardRocketOneDrop;
    public JoystickButton launchPadZero;
    public JoystickButton launchPadOne;
    public JoystickButton launchPadTwo;
    

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick joyRight;
    public Joystick joyLeft;
    public Joystick operator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        operator = new Joystick(2);
        
        joyLeft = new Joystick(0);
        
        joyRight = new Joystick(1);
        


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("DriveWithPID", new DriveWithPID());
        SmartDashboard.putData("Turn: absZero", new Turn(0, 0));
        SmartDashboard.putData("Turn: abs90", new Turn(90, 0));
        SmartDashboard.putData("UpdateDebugEnable", new UpdateDebugEnable());
        SmartDashboard.putData("ResetGyro", new ResetGyro());
        SmartDashboard.putData("AutonomousExperiment", new AutonomousExperiment());
        SmartDashboard.putData("LightsOn", new LightsOn());
        SmartDashboard.putData("LightsOff", new LightsOff());
        SmartDashboard.putData("GetDefaultPIDs", new GetDefaultPIDs());
        SmartDashboard.putData("OrangeLight", new OrangeLight());
        SmartDashboard.putData("AlienAttackLight", new AlienAttackLight());
        SmartDashboard.putData("AlienRetractedClosed", new AlienRetractedClosed());
        SmartDashboard.putData("AlienExtendedClosed", new AlienExtendedClosed());
        SmartDashboard.putData("AlienExtendedOpen", new AlienExtendedOpen());
        SmartDashboard.putData("AlienRetractedClosed2", new AlienRetractedClosed2());
        SmartDashboard.putData("AlienExtendedClosed2", new AlienExtendedClosed2());
        SmartDashboard.putData("AlienExtendedOpen2", new AlienExtendedOpen2());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS


    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoyRight() {
        return joyRight;
    }

    public Joystick getJoyLeft() {
        return joyLeft;
    }

    public Joystick getOperator() {
        return operator;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

