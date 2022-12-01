package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbUpCommand extends CommandBase {

    private final Climb subsystem;


    public ClimbUpCommand(Climb subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);
    }



    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setClimbMotor();
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.stopClimbMotor();
    }

}
