// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase
{
    /** Creates a new Flywheel. */
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final VictorSPX indexer = new VictorSPX(Constants.indexMotorID);
    private final VictorSPX intakeRoller = new VictorSPX(Constants.intakeRoller);
    private boolean ready = false;
    private final CANSparkMax bottomFlywheelmotor = new CANSparkMax(Constants.bottomFlyWheelMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final CANSparkMax topFlywheelmotor = new CANSparkMax(Constants.topFlyWheelMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    public Flywheel() {
        indexer.setNeutralMode(NeutralMode.Brake);
    }
    
    
    @Override
    public void periodic()
    {
        // This method will be called once per scheduler run
        if(colorSensor.getProximity() > 300){
            ready=true;
        }
    }
    
    
    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }
    public void releaseBall(){
        setIndexer(1);
        intakeRoller.set(ControlMode.PercentOutput, 1);
    }
    public void setIndexer(double speed){
        indexer.set(ControlMode.PercentOutput, speed);
    }
    public void stopIndexer(){
        indexer.set(ControlMode.PercentOutput, 0);
    }

    public void setMotors() {
        if(ready){
            bottomFlywheelmotor.set(Constants.topFlyWheelMotorSetSpeed);
            bottomFlywheelmotor.set(Constants.bottomFlyWheelMotorSetSpeed);
            indexer.set(ControlMode.PercentOutput, 50);
        }
    }

    public void stopMotor(){
        bottomFlywheelmotor.set(0);
        topFlywheelmotor.set(0);
        indexer.set(ControlMode.PercentOutput, 0);
    }
}
