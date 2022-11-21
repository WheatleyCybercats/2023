// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.BringIntakeUpCommand;
import frc.robot.commands.ColorSensorCommand;
import frc.robot.commands.FlywheelCommand;
import frc.robot.commands.IntakeBallCommand;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;

public class RobotContainer
{
    // The robot's subsystems and commands are defined here...
    private final Flywheel flywheel = new Flywheel();

    private final FlywheelCommand flywheelCommand = new FlywheelCommand(flywheel);
    private final Intake intake = new Intake();
    private final IntakeBallCommand IntakeBallCommand = new IntakeBallCommand(intake);
    private final BringIntakeUpCommand BringIntakeUpCommand = new BringIntakeUpCommand(intake);
    public static Joystick driverController = new Joystick(Constants.DriveJoystick);



    public RobotContainer()
    {
        // Configure the button bindings
        configureButtonBindings();
    }

    private void configureButtonBindings()
    {
        // Add button to command mappings here.
        // See https://docs.wpilib.org/en/stable/docs/software/commandbased/binding-commands-to-triggers.html
        JoystickButton spinButton = new JoystickButton(driverController,3);
        spinButton.whileHeld(flywheelCommand);
        JoystickButton intakeButton = new JoystickButton(driverController, 1);
        intakeButton.whileHeld(IntakeBallCommand);
        intakeButton.whenReleased(BringIntakeUpCommand);
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand()
    {
        // An FlywheelCommand will run in autonomous
        return flywheelCommand;
    }
}