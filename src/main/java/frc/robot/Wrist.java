package frc.robot;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Wrist extends SubsystemBase {

    private final SparkMax wristMotor =
        new SparkMax(10, MotorType.kBrushless);

    public void moveWrist(double speed) {
    wristMotor.set(speed);
    }

    public void stopWrist() {
    wristMotor.stopMotor();
    }


    
}
