// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase
{
    //TODO - How does intake work?

    private final CANSparkMax Intake = new CANSparkMax(Constants.spinTake, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax IntakeMotor = new CANSparkMax(Constants.intakeRoller, CANSparkMaxLowLevel.MotorType.kBrushless);
    public Intake() {

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

    private void setIntake(double speed){
        //sets the speed of the bottom flywheel motor
        Intake.set(speed);
    }

    public void setIntakeMotor(){
        IntakeMotor.set(Constants.bottomFlyWheelMotorSetSpeed);
    }


    public void stopMotor(){
        Intake.set(0);
        IntakeMotor.set(0);
    }
}
