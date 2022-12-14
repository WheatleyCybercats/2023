// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;


public class DriveTrain extends SubsystemBase{
    private final WPI_TalonFX leftLeader = new WPI_TalonFX(3);
    private final WPI_TalonFX rightLeader = new WPI_TalonFX(1);
    private final WPI_TalonFX leftFollower = new WPI_TalonFX(4);
    private final WPI_TalonFX rightFollower = new WPI_TalonFX(2);
    private final MotorControllerGroup leftGroup = new MotorControllerGroup(leftLeader,leftFollower);
    private final MotorControllerGroup rightGroup = new MotorControllerGroup(rightLeader,rightFollower);


    public void setLeftMotors(double speed){
        leftGroup.set(speed);
    }
    public void setRightMotors(double speed){
        rightGroup.set(speed);
    }
    public DriveTrain(){
        leftFollower.set(ControlMode.Follower, leftLeader.getDeviceID());
        rightFollower.set(ControlMode.Follower, rightLeader.getDeviceID());
        leftLeader.setInverted(true);
        leftFollower.setInverted(true);
        rightLeader.setInverted(false);
        rightFollower.setInverted(false);
        leftLeader.setNeutralMode(NeutralMode.Brake);
        rightLeader.setNeutralMode(NeutralMode.Brake);
        leftFollower.setNeutralMode(NeutralMode.Brake);
        rightFollower.setNeutralMode(NeutralMode.Brake);
    }
}