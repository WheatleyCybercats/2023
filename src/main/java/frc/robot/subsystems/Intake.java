// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase
{

    private final DoubleSolenoid intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.solenoidID, Constants.solenoid2ID);
    private final VictorSPX intakeRoller = new VictorSPX(Constants.intakeRoller);
    private final CANSparkMax moveIntakeMotor = new CANSparkMax(Constants.spinTake, CANSparkMaxLowLevel.MotorType.kBrushless);

    public Intake() {
        intakeRoller.setInverted(false);
        intakeRoller.setNeutralMode(NeutralMode.Brake);
         intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }


    @Override
    public void periodic()
    {
        bringIntakeDown();
        setIntakeMotor(-1);
        setIntakeRoller(-0.8);
    }


    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }

    public void setIntakeRoller(double speed){
        //sets the speed of the bottom flywheel motor
        intakeRoller.set(ControlMode.PercentOutput, speed);
    }

    public void setIntakeMotor(double speed){
        moveIntakeMotor.set(-speed);
    }
    //The spinny red and  black thingy

    public void bringIntakeDown(){

        intakeSolenoid.set(DoubleSolenoid.Value.kReverse);
    }


    public void bringIntakeUp(){

        intakeSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void stopMotor(){
        intakeRoller.set(ControlMode.PercentOutput,0);
        moveIntakeMotor.set(0);
    }
}
