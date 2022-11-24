package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch colorMatcher = new ColorMatch();
    private final VictorSPX indexer = new VictorSPX(Constants.indexMotorID);
    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);

    /**Used to specify the colors based on environment using RGB values*/

    public ColorSensor(){
        indexer.setNeutralMode(NeutralMode.Brake);

        Color kBlueTarget = new Color(0.143, 0.427, 0.429);
        Color kRedTarget = new Color(0.561, 0.232, 0.114);
        colorMatcher.addColorMatch(kRedTarget);
        colorMatcher.addColorMatch(kBlueTarget);
    }

    @Override
    public void periodic(){
        // This method will be called once per scheduler run
        if(colorSensor.getProximity() > 100){
            if (colorMatcher.matchClosestColor(colorSensor.getColor()).color == kRedTarget) {
                if (Constants.teamColor.equalsIgnoreCase(colorMatcher.matchClosestColor(colorSensor.getColor()).color.toString())) {
                    setIndexer(0.1);
                } else {
                    setIndexer(-0.1);
                }
            }
        }
    }


    @Override
    public void simulationPeriodic(){
        // This method will be called once per scheduler run during simulation
    }

    public void setIndexer(double speed){
        indexer.set(ControlMode.PercentOutput,speed);
    }

    public void stopMotor(){
        indexer.set(ControlMode.PercentOutput,0);
    }
}
