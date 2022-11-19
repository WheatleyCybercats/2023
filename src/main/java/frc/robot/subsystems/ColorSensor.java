package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch colorMatcher = new ColorMatch();
    private final CANSparkMax indexer = new CANSparkMax(Constants.indexMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);

    private String colorString = "NoColor";

    /**Used to specify the colors based on enviroment using RGB values*/

    public ColorSensor(){
        colorMatcher.addColorMatch(kBlueTarget);
        colorMatcher.addColorMatch(kRedTarget);
    }

    @Override
    public void periodic(){
        // This method will be called once per scheduler run
        Color detectedColor = colorSensor.getColor();
        ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
        if (match.color == kBlueTarget) {
            colorString = "blue";
        } else if (match.color == kRedTarget) {
            colorString = "red";
        } else {
            colorString = "None";
        }

        setMotorSpeed();

    }


    @Override
    public void simulationPeriodic(){
        // This method will be called once per scheduler run during simulation
    }

    public void setMotorSpeed(){
        switch(colorString){
            case "blue":
                indexer.set(1);
                break;
            case "red":
                indexer.set(1);
                break;
            default:
                indexer.set(1);
                break;
        }
    }

    public void stopMotor(){
        indexer.set(0);
    }
}
