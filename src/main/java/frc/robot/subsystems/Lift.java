package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.DigitalInput;

public class Lift extends GenericSubsystem {
    Gearbox gearbox;

    DigitalInput topLimit;
    DigitalInput bottomLimit;

    TalonSRXEncoder encoder;

    public Lift(Gearbox gearbox, DigitalInput topLimit, DigitalInput bottomLimit, TalonSRXEncoder encoder) {
        this.gearbox = gearbox;
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.encoder = encoder;

        addChild(gearbox);
        addChild(topLimit);
        addChild(bottomLimit);
        addChild(encoder);
    }

    @Override
    public void apply(double v) {
        gearbox.set(v);
    }

    @Override
    public boolean canMove(double v) {
        if(v > 0 && topLimit.get())
            return false;
        else if(v < 0 && bottomLimit.get())
            return false;
        return true;
    }

    @Override
    public void stop() {
        gearbox.set(0);
    }

    @Override
    protected void initDefaultCommand() {

    }
}
