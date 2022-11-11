// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package main.java.frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ExampleSubsystem extends SubsystemBase
{
    /** Creates a new ExampleSubsystem. */

    private final CANSparkMax bottomFlywheelmotor = new CANSparkMax(Constants.bottomFlyWheelMotor, RobotDriveBase.MotorType.kBrushless);
    public ExampleSubsystem() {

    }
    
    
    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
    }
    
    
    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }

    private void setMotor(double speed){
        bottomFlywheelmotor.set(speed);
    }

    public void setBottomFlywheelmotor(){
        bottomFlywheelmotor.set(Constants.bottomFlyWheelMotorSetSpeed);
    }

    public void stopMotor(){
        bottomFlywheelmotor.set(0);
    }
}
