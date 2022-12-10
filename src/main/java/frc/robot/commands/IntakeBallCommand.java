// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.ColorSensor;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;


/** An example command that uses an example subsystem. */
public class IntakeBallCommand extends CommandBase
{
    private final Intake subsystem;

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


    /**
     * Creates a new FlywheelCommand.
     *
     * @param subsystem The subsystem used by this command.
     */

    public IntakeBallCommand(Intake subsystem) {
        this.subsystem = subsystem;
    }


    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}


    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //subsystem.bringIntakeDown();
        subsystem.bringIntakeDown();
    }


    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        subsystem.bringIntakeUp();
    }


    // Returns true when the command should end.
    @Override
    public boolean isFinished()
    {
        return false;
    }
}
