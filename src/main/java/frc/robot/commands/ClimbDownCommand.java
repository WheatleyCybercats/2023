package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climb;

public class ClimbDownCommand extends CommandBase {

    private final Climb subsystem;

    public ClimbDownCommand(Climb subsystem) {
        this.subsystem = subsystem;
        addRequirements(subsystem);

    }


    @Override

    public void initialize() {
    }

    @Override
    public void execute() {
        subsystem.setClimbMotorReverse();
    }
    @Override
    public void end(boolean interrupted) {
        subsystem.stopClimbMotor();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
