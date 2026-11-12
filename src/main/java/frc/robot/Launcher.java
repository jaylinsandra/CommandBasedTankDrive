package frc.robot;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Set;

public class Launcher extends SubsystemBase {

    private final PneumaticHub hub = new PneumaticHub(1);
    
    private final Solenoid[] barrels = {
        hub.makeSolenoid(0),
        hub.makeSolenoid(1),
        hub.makeSolenoid(2),
        hub.makeSolenoid(3),
        hub.makeSolenoid(4)
    };
    private int currentBarrel = 0;
    private static final double fire_pulse_seconds = 0.15;

    public Launcher() {
        hub.enableCompressorDigital();
        closeAllValves();
    }

    private void closeAllValves() {
        for (Solenoid valve : barrels) {
            valve.set(false);
        }
        }

    public Command fireBarrelCommand(int barrelIndex) {
        
    }

    public Command fireNextCommand() {

    }

    public Command fireAllVolleyCommand() {

    }

    public Command resetBarrelsCommand() {

    }

    public void periodic() {
        SmartDashboard.putNumber("Next Barrel Ready", currentBarrel + 1);
    }
    }


