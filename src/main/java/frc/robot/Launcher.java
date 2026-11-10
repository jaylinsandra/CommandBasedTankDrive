package frc.robot;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase {

    private final PneumaticHub hub = new PneumaticHub(1);

    public Launcher() {
        
    }
}
