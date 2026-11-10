package frc.robot;


import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {

    private final SparkMax turretMotor =
        new SparkMax(7, MotorType.kBrushless);

    public void moveTurret(double speed) {
        turretMotor.set(speed);
    }

    public void stopTurret() {
        turretMotor.stopMotor();
    }
}