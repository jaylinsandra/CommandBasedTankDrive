package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.ArrayList;
import java.util.List;
import org.littletonrobotics.junction.Logger;

public class LED extends SubsystemBase {

    private final AddressableLED ledStrip = new AddressableLED(0);
    private final AddressableLEDBuffer ledBuffer =new AddressableLEDBuffer(94);
    
    public LED() {
        ledStrip.setLength(ledBuffer.getLength());
        ledStrip.setData(ledBuffer);
        ledStrip.start();
    }

    public void turnOff() {
        for (int i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, 0, 0, 0);
        }
        ledStrip.setData(ledBuffer);
    }

private void setWhite() {
    for (int i = 0; i < ledBuffer.getLength(); i++) {
        ledBuffer.setRGB(i, 255, 255, 255);
    }
    ledStrip.setData(ledBuffer);
}

private void setRipple(int position) {
    for (int i = 0; i <= position; i++) {
        ledBuffer.setRGB(i, 255, 255, 255);
    }
    ledStrip.setData(ledBuffer);
}

public Command playAnimationCommand() {

    List<Command> animation = new ArrayList<>();

    //ripple
    for (int i = 0; i < ledBuffer.getLength(); i++) {
        final int position = i;
        animation.add(Commands.runOnce(() -> {
            setRipple(position);
            Logger.recordOutput("LED/RipplePosition", position);
        }, this));
        animation.add(Commands.waitSeconds(0.02));
    }

    //strobe
    Command strobe = Commands.sequence(
        Commands.runOnce(() -> {
            setWhite();
            Logger.recordOutput("LED/AnimationPhase", "Strobe");
        }, this),
        Commands.waitSeconds(0.25),
        Commands.runOnce(() -> {
            turnOff();
        }, this)
    ); 
    animation.add(strobe.repeatedly().withTimeout(7));  

    return Commands.sequence(animation.toArray(new Command[0])).finallyDo(interrupted -> turnOff());
}
}
