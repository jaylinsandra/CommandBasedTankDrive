package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;


public class DriveTrain extends SubsystemBase {
    private  final int leftmotor1port = 2;
    private  final int leftmotor2port = 4;
    private  final int rightmotor1port = 1;
    private  final int rightmotor2port = 3;
     

    SparkMax leftmotor1 =
        new SparkMax(leftmotor1port, MotorType.kBrushless);
    SparkMax leftmotor2 =
        new SparkMax(leftmotor2port, MotorType.kBrushless);
    SparkMax rightmotor1 =
        new SparkMax(rightmotor1port, MotorType.kBrushless);
    SparkMax rightmotor2 =
        new SparkMax(rightmotor2port, MotorType.kBrushless);
        
    public  DifferentialDrive drive = new DifferentialDrive(leftmotor1, rightmotor1);

    public DriveTrain(){
        
    SparkMaxConfig leftLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig rightLeaderConfig = new SparkMaxConfig();
    SparkMaxConfig leftFollowerConfig = new SparkMaxConfig();
    SparkMaxConfig rightFollowerConfig = new SparkMaxConfig();
        leftLeaderConfig.inverted(true);
        rightLeaderConfig.inverted(false);
        leftFollowerConfig.follow(leftmotor1);
        rightFollowerConfig.follow(rightmotor1);

        leftmotor1.configure(leftLeaderConfig, ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters);
        leftmotor2.configure(leftFollowerConfig, ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters);
        rightmotor1.configure(rightLeaderConfig, ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters);
        rightmotor2.configure(rightFollowerConfig, ResetMode.kResetSafeParameters,
            PersistMode.kNoPersistParameters);
   
  

    

    }
   
   
        }
    



