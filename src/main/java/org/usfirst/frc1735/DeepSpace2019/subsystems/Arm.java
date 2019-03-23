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


import org.usfirst.frc1735.DeepSpace2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Arm extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private DigitalInput frontSwingSwitch;
    private DigitalInput backSwingSwitch;
    private WPI_TalonSRX armMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
  
    public Arm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        frontSwingSwitch = new DigitalInput(2);
        addChild("FrontSwingSwitch",frontSwingSwitch);
        
        
        backSwingSwitch = new DigitalInput(3);
        addChild("BackSwingSwitch",backSwingSwitch);
        
        
        armMotor = new WPI_TalonSRX(5);
        
        
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ArmWithJoystick());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new ArmWithRotarySwitch());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        //updatePIDsFromSD(); // optionally update the current PIDs from the SD.  Comment out to avoid this.
        //updateSetpointFromSD(); // optionally update the controller setpoint based on above calculations.  Comment out to avoid this.
        m_absEncoderEntry.setDouble(armMotor.getSelectedSensorPosition()); // Current absolute encoder val.  1024 ticks/revolution = 2.84444 ticks/degree.  256 in 90 degrees.
    }

    // This function causes all thelocal/active PID coefficients and related controller variables to be updated based on whatever's in the SmartDashboard.
    // Can be called in the above periodic() function, or intiated by a Command button on the SmartDashboard
    public void updatePIDsFromSD() {
        // read PID coefficients from SmartDashboard (Arg is the default)
        double p = m_pArmEntry.getDouble(0);
        double i = m_iArmEntry.getDouble(0);
        double d = m_dArmEntry.getDouble(0);
        //double iz = m_izArmEntry.getDouble(0);
        double ff = m_ffArmEntry.getDouble(0);
        double max = m_maxOutputArmEntry.getDouble(0);
        double min = m_minOutputArmEntry.getDouble(0);

        // if PID coefficients on SmartDashboard are different from our latest operating values, write the new values to controller and store them locally.
        if((p != m_pArm)) { armMotor.config_kP(0, p, 0); m_pArm = p; }
        if((i != m_iArm)) { armMotor.config_kI(0, i, 0); m_iArm = i; }
        if((d != m_dArm)) { armMotor.config_kD(0, d, 0); m_dArm = d; }
        //if((iz != m_izArm)) { m_pidController.setIZone(iz); m_izArm = iz; }
        if((ff != m_ffArm)) { armMotor.config_kF(0, ff, 0); m_ffArm = ff; }
        if((max != m_maxOutputArm) || (min != m_minOutputArm)) { 
        armMotor.configPeakOutputForward(max,0);
        armMotor.configPeakOutputReverse(min,0);
        m_minOutputArm = min; m_maxOutputArm = max;
        }
    }

    // Update the setpoint based on the current ARM member variable
    public void updateSetpointFromSD() {
        /**
         * PIDController objects are commanded to a set point using the 
         * SetReference() method.
         * 
         * The first parameter is the value of the set point, whose units vary
         * depending on the control type set in the second parameter.
         * 
         * The second parameter is the control type can be set to one of four 
         * parameters:
         *  com.revrobotics.ControlType.kDutyCycle
         *  com.revrobotics.ControlType.kPosition
         *  com.revrobotics.ControlType.kVelocity
         *  com.revrobotics.ControlType.kVoltage
         */
        double setpointDegrees = degreesToTicks(m_setpointArmEntry.getDouble(0));
        System.out.println(setpointDegrees);
        //if ((setpointDegrees != m_setpointDegreesArm)) {
            m_setpointDegreesArm = setpointDegrees;
            System.out.println("Changing ARM setpoint to new value: " + m_setpointDegreesArm);
            armMotor.set(ControlMode.Position, m_setpointDegreesArm);
        //}
    }

    public double degreesToTicks(final double d) {
        final double tarePoint = 120; // this is the intended resting point of the arm in the forward down position when against the ground
        final double ticksPerDegree = -2.84;

        final double difference = tarePoint - d;
        final double offset = difference * ticksPerDegree;

        return m_tarePointTicks + offset;
    }

    // this function will reset what the forward most position of the arm is in encoder ticks
    // the intended use is to move the arm into that position and call this function
    // the reason for this function is to compensate in the middle of a match
    // if the encoder gets shifted on the arm such that -512 is no longer straight up and down
    public void tareArm() {
        double oldTarePoint = m_tarePointTicks;
        m_tarePointTicks = armMotor.getSelectedSensorPosition();
        DriverStation.reportWarning("Tared arm, new forward position is: " + m_tarePointTicks + ", old position was: " + oldTarePoint , false);
    }

    // One-time initialization of the ARM PID controller and hardware.  Called by Robot.robotInit()
    public void initArm() {
        // Motor is mounted so that positive voltages are towards the back of the robot.  Want to invert that
        armMotor.setInverted(InvertType.InvertMotorOutput);

        // Put the Talons in "Brake" mode for better accuracy
        armMotor.setNeutralMode(NeutralMode.Brake);

    	// Voltage compensation mode should make 100% output request scale to 12V regardless of battery voltage.
    	// (if battery voltage is less than 12v it will just put all available voltage out)
    	armMotor.enableVoltageCompensation(true);
    	// Set the desired 100% at 12V
        armMotor.configVoltageCompSaturation(12.0, 0);
        // set the peak and nominal outputs, -1 to 1 in percentage of nominal voltage (even if battery voltage is higher)
    	armMotor.configNominalOutputForward(0.0, 0);
    	armMotor.configNominalOutputReverse(-0.0, 0);
    	armMotor.configPeakOutputForward(kMaxOutputArm,0);
        armMotor.configPeakOutputReverse(kMinOutputArm,0);
        
    	// set closed loop gains in slot0 - see documentation
    	armMotor.selectProfileSlot(0,0);
    	// Set the closed loop ramp as well (time in seconds to full speed; timeout)
    	armMotor.configClosedloopRamp(kAccelTime, 0); //want to get to full speed in 1/3 sec
    	armMotor.configOpenloopRamp(kAccelTime, 0);

        //Set the closed-loop allowable error.  Empirically on no-load, error was <50 units.
    	armMotor.configAllowableClosedloopError(0, (int) kToleranceEncUnits, 0); // index, err, timeout in ms


        // Encoder setup:
        // Chose the sensor and direction
    	armMotor.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0); // extra args are:  primary closed loop, timeout in ms
        armMotor.setSensorPhase(false); //true = invert the value.
        armMotor.configFeedbackNotContinuous(false, 0); // prevent the abs encoder from wrapping

        
        // Some packet frames default to updating at 160ms, which is waaaay too slow for our 20ms DS periodic interval!
		// CTRE recommends that you set relevant frame periods to be at least as fast as periodic rate-- their example uses:
		armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, 0);
		armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 0);

        // display PID coefficients on Shuffleboard (and create references to each entry at the same time)
        //.withWidget(BuiltInWidgets.kNumberSlider)
        //.withProperties(Map.of("min", -0.3, "max", 0.3)) // specify widget properties here                                                    
        m_armTab = Shuffleboard.getTab("Arm"); // Creates tab if it doesn't already exist.

        m_pArmEntry = m_armTab.add("P Arm", kPArm)
                                .withSize(2, 1) // make the widget 2 wide x1 tall
                                .withPosition(0, 0) // col, row
                                .getEntry();
        m_iArmEntry = m_armTab.add("I Arm", kIArm)
                                .withSize(2, 1)
                                .withPosition(2, 0)
                                .getEntry();
        m_dArmEntry = m_armTab.add("D Arm", kDArm)
                                .withSize(2, 1)
                                .withPosition(4, 0)
                                .getEntry();
        m_izArmEntry = m_armTab.add("I Zone Arm", kIzArm)
                                .withSize(3, 1)
                                .withPosition(6, 0)
                                .getEntry();
        m_ffArmEntry = m_armTab.add("Feed Forward Arm", kFFArm)
                                .withSize(4, 1)
                                .withPosition(9, 0)
                                .getEntry();
        m_minOutputArmEntry = m_armTab.add("Min Output Arm", kMinOutputArm)
                                .withSize(4, 1)
                                .withPosition(0, 2)
                                .getEntry();
        m_maxOutputArmEntry = m_armTab.add("Max Output Arm", kMaxOutputArm)
                                .withSize(4, 1)
                                .withPosition(4, 2)
                                .getEntry();
        m_setpointArmEntry = m_armTab.add("Set Degrees Arm", 0)
                               .withSize(4, 1)
                                .withPosition(8, 2)
                                .getEntry();
        m_absEncoderEntry = m_armTab.add("Abs Encoder", armMotor.getSelectedSensorPosition())
                                .withSize(4, 1)
                                .withPosition(8, 4)
                                .getEntry();
        m_simpleArmJoyvalEntry = m_armTab.add("Simple Arm Joyval", 0)
                                .withSize(4, 1)
                                .withPosition(8, 8)
                                .getEntry();

// Command to trigger a read of all ARM-related variables from Shuffleboard
        m_armTab.add("UpdateArmPIDs", new UpdateArmPIDs())
                                .withSize(4, 1)
                                .withPosition(13, 0);
// Command to force an update of the setpoint from Shuffleboard
        m_armTab.add("UpdateArmSetpoint", new UpdateArmSetpoint())
                                .withSize(4, 1)
                                .withPosition(12, 2);

        m_armTab.add("TareArm", new TareArm())
                                .withSize(4, 1)
                                .withPosition(12, 3);

}

    // Simple function to directly control the motor without any PID
    public void simpleMoveArm(double magDir) {
        // magDir comes in as a corrected value from joystick (Y is inverted already), so positive means towards the front of the robot.
        double motorVal;
        if (magDir > 0) {
            motorVal = magDir * Math.abs(m_maxOutputArm);
        }
        else {
            motorVal = magDir * Math.abs(m_minOutputArm);
        }
        m_simpleArmJoyvalEntry.setDouble(motorVal); //MagDir comes directly from Joysticks (-1 to 1); need to scale to our defined max/min values
        armMotor.set(magDir); // uses open loop mode, when called without a mode.
    }

    //Use this function to use a joystick value to modifies PID setpoint for the ARM
    public void PIDJoyMoveArm(double magDir) {
        //magdir is assumed to be corrected for joystick Y values before getting here.
        // range is +1 to -1.  We want to map that so that +1 is the forward-most position of the arm/encoder, and -1 is the rearmost (With zero being straight up)
        // Assume (for now) symmetric values for fwd vs backward.
        double setpointArm = magDir * kMaxArmSwingInDegrees;
        armMotor.set(ControlMode.Position, setpointArm);
    }

    public void PIDMoveArm(double setpointTicks) {
        armMotor.set(ControlMode.Position, setpointTicks);
    }

    public double getSelectedSensorPosition() {
        return armMotor.getSelectedSensorPosition();
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Member variables
    // Pointer to the Shuffleboard tab for the Arm:
    ShuffleboardTab m_armTab;
    // Entries for each of the values we want to manipulate in the tab
    NetworkTableEntry m_pArmEntry;
    NetworkTableEntry m_iArmEntry;
    NetworkTableEntry m_dArmEntry;
    NetworkTableEntry m_ffArmEntry;
    NetworkTableEntry m_izArmEntry;
    NetworkTableEntry m_maxOutputArmEntry;
    NetworkTableEntry m_minOutputArmEntry;
    NetworkTableEntry m_setpointArmEntry; // Change this to modify the setpoint (in rotations)
    NetworkTableEntry m_processVariableArmEntry; // current encoder position (Value?  Rotations?)
    NetworkTableEntry m_simpleArmJoyvalEntry; // to print current joystick value for calibration of motor limits
    NetworkTableEntry m_absEncoderEntry; // For absolute encoder values
    

    // Arm setpoints:
    //    Max rotation is 240', which is +/-120 if zero is Top Dead Center.
    //    Therefore, in rotations, each direction is .33333333 rotations of the whole arm, but that's with a 49:1 reduction.  sooo...
    static final double kArmGearRatio = 49.0;
    static final double kMaxArmSwingInDegrees = 90; // start with +/- this amount so that we don't get close to the hard limits while testing.  We'll sneak up on the final value when tuning.
    static double m_setpointDegreesArm = 0; // for live updates via SD, this is the current/latest setpoint captured.
    static final double kEncoderTicksPerDegree = (1024 / 360); // 4096 encoder ticks per revolution;
    static final double kToleranceDegrees = 1; // Stop if we are within this many degrees of the setpoint.
    static final double kToleranceEncUnits = kToleranceDegrees*kEncoderTicksPerDegree;
    
    // Arm PID coefficients (compiled-in; can override via SD)
    static final double kPArm = 0.45; 
    static final double kIArm = 1e-3;
    static final double kDArm = 0; 
    static final double kIzArm = 0; 
    static final double kFFArm = 0; 
    static final double kMaxOutputArm = 0.35;
    static final double kMinOutputArm = -0.35;
    static final double kAccelTime = 0.25; // time it takes to reach full speed
    
    // Current Arm PID coefficients (pulled from compile or SD)
    static double m_pArm = kPArm; 
    static double m_iArm = kIArm;
    static double m_dArm = kDArm; 
    static double m_izArm = kIzArm; 
    static double m_ffArm = kFFArm; 
    static double m_maxOutputArm = kMaxOutputArm;  // May adjust this higher later...
    static double m_minOutputArm = kMinOutputArm;

    // Preset Angles for Arm in Degrees
    public static double kForwardBallPickup = 120;
    public static double kForwardHatchPickup = 92;
    public static double kForwardRocketOne = 80;
    public static double kForwardCargo = 38 ;
    public static double kForwardRocketTwo = 20;
    public static double kUp = 0;
    public static double kBackwardRocketTwo = -kForwardRocketTwo;
    public static double kBackwardCargo = -kForwardCargo;
    public static double kBackwardRocketOne = -kForwardRocketOne;
    public static double kBackwardHatchPickup = -90;
    public static double kBackwardBallPickup = -kForwardBallPickup;


    // Variables related to taring the arm
    static final double kTarePointTicks = -162;
    double m_tarePointTicks = kTarePointTicks;
}

