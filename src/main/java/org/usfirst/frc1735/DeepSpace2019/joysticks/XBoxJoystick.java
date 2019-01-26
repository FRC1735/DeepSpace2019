package org.usfirst.frc1735.DeepSpace2019.joysticks;

import org.usfirst.frc1735.DeepSpace2019.Robot;
import org.usfirst.frc1735.DeepSpace2019.commands.EnterArcadeMode;
import org.usfirst.frc1735.DeepSpace2019.commands.EnterTankMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XBoxJoystick extends AbstractJoystick {
    private static final int BUTTON_X = 3;
    private static final int BUTTON_Y = 4;

    public XBoxJoystick(final Joystick joystick, final Role role) {
        super(joystick, role);
    }

    @Override
    public double getX() {
        return joystick.getRawAxis(4);
    }

    @Override
    public double getY() {
        return joystick.getRawAxis(5);
    }

    @Override
    void initializeKeymap() {
        switch (role) {
            case DRIVER_LEFT:
                Robot.oi.arcadeMode = new JoystickButton(joystick, BUTTON_X);
                Robot.oi.arcadeMode.whenPressed(new EnterArcadeMode());

                Robot.oi.tankMode = new JoystickButton(joystick, BUTTON_Y);
                Robot.oi.tankMode.whenPressed(new EnterTankMode());

                break;

            case OPERATOR:
                // Do nothing for now
                break;
        }
    }
}