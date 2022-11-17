package frc.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.*;
import frc.robot.Constants;

public class ColorSensor extends SubsystemBase {

    private final I2C.Port i2cPort = I2C.Port.kOnboard;
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();
    private final CANSparkMax imaginaryMotor = new CANSparkMax(Constants.ImaginaryMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
/**Don't have a motor to usea*/
    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

    private String colorString = "NoColor";

    /**Used to specify the colors based on enviroment using RGB values*/

    public ColorSensor(){
        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);
    }

    @Override
    public void periodic(){
        // This method will be called once per scheduler run
        Color detectedColor = m_colorSensor.getColor();
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        if (match.color == kBlueTarget) {
            colorString = "blue";
        } else if (match.color == kRedTarget) {
            colorString = "red";
        } else if (match.color == kGreenTarget) {
            colorString = "green";
        } else if (match.color == kYellowTarget) {
            colorString = "yellow";
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
                imaginaryMotor.set(1);
                break;
            case "red":
                imaginaryMotor.set(2);
                break;
            case "green":
                imaginaryMotor.set(3);
                break;
            case "yellow":
                imaginaryMotor.set(4);
                break;
            default:
                imaginaryMotor.set(0);
                break;
        }
    }

    public void stopMotor(){
        imaginaryMotor.set(0);
    }
}
