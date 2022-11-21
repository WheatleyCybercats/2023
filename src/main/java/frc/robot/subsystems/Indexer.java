package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase {
    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final VictorSPX indexer = new VictorSPX(Constants.indexMotorID);
    public boolean ballInIndexer = false;

    public Indexer(){
        indexer.setNeutralMode(NeutralMode.Brake);
    }

    @Override
    public void periodic(){
        // This method will be called once per scheduler run
        if(colorSensor.getProximity() > 100){
            setIndexer(0.8);
        }
    }

    public void releaseBall(){
        setIndexer(-0.8);
    }

    @Override
    public void simulationPeriodic(){
        // This method will be called once per scheduler run during simulation
    }

    public void setIndexer(double speed){
        indexer.set(ControlMode.PercentOutput, speed);
    }

    public void stopIndexer(){
        indexer.set(ControlMode.PercentOutput, 0);
    }
}
