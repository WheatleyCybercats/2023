// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public class DriveTrain extends SubsystemBase{
    private final WPI_TalonFX leftMotor1 = new WPI_TalonFX(3);
    private final WPI_TalonFX leftMotor2 = new WPI_TalonFX(4);
    private final WPI_TalonFX rightMotor1 = new WPI_TalonFX(1);
    private final WPI_TalonFX rightMotor2 = new WPI_TalonFX(2);


    public void setLeftMotors(double speed){
        leftMotor1.set(speed);
        leftMotor2.set(speed);
    }
    public void setRightMotors(double speed){
        rightMotor1.set(speed);
        rightMotor2.set(speed);
    }
    public DriveTrain(){
        leftMotor1.setInverted(true);
        leftMotor2.setInverted(true);
        rightMotor1.setInverted(false);
        rightMotor2.setInverted(false);
    }
}