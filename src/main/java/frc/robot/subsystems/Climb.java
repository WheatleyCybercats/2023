package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climb extends SubsystemBase {
    private final CANSparkMax climbMotor = new CANSparkMax(Constants.climbMotor, CANSparkMaxLowLevel.MotorType.kBrushless);
    public Climb() {


    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }



    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    public void setClimbMotor(){
        climbMotor.set(-Constants.climbMotorSetSpeed);
    }

    public void setClimbMotorReverse(){
        climbMotor.set(Constants.climbMotorSetSpeed);
    }

    public void stopClimbMotor(){
        climbMotor.set(0);
    }










}
