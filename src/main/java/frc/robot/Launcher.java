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
    private static final double volley_delay = 0.04;

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
        return Commands.sequence(
            runOnce(() -> barrels[barrelIndex].set(true)),
            Commands.waitSeconds(fire_pulse_seconds),
            runOnce(() -> barrels[barrelIndex].set(false))
        );
    }

    public Command fireNextCommand() {
        return Commands.defer(() -> {
            int barrelToFire = currentBarrel;
            currentBarrel = (currentBarrel + 1) % barrels.length;
            
            return fireBarrelCommand(barrelToFire);
        }, Set.of(this));
    }

    public Command fireAllVolleyCommand() {
        Command[] volley = new Command[barrels.length * 2];
        for (int i = 0; i < barrels.length; i++) {
            volley[i * 2] = fireBarrelCommand(i);
            if (i < barrels.length - 1) {
                volley[i * 2 + 1] = Commands.waitSeconds(volley_delay);
            } else {
                volley[i * 2 + 1] = Commands.none();
        }
        }
        return Commands.sequence(volley)
        .andThen(runOnce(() -> currentBarrel = 0));
    }

    public void periodic() {
        SmartDashboard.putNumber("Next Barrel Ready", currentBarrel + 1);
    }
    }


