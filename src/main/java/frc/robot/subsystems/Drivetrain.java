// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase
{
    /** Creates a new Flywheel. */

    private final CANSparkMax bottomFlywheelmotor = new CANSparkMax(Constants.bottomFlyWheelMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax topFlywheelmotor = new CANSparkMax(Constants.topFlyWheelMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    public Drivetrain() {

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
        //sets the speed of the bottom flywheel motor
        bottomFlywheelmotor.set(speed);
    }

    public void setBottomFlywheelmotor(){
        bottomFlywheelmotor.set(Constants.bottomFlyWheelMotorSetSpeed);
    }

    public void setTopFlywheelmotor(){
        topFlywheelmotor.set(Constants.topFlyWheelMotorSetSpeed);
    }

    public void stopMotor(){
        bottomFlywheelmotor.set(0);
        topFlywheelmotor.set(0);
    }
}
