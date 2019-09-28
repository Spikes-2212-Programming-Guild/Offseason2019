package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.Namespace;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.lift.LowerLift;

import java.util.function.Supplier;

public class Lift extends GenericSubsystem {
    public static final Namespace namespace = ConstantHandler.addNamespace("Lift");
    public static final Namespace lowPIDNamespace = namespace.addChild("LowPID");
    public static final Namespace highPIDNamespace = namespace.addChild("HighPID");

    public static final Supplier<Double> PID_SWITCH_POINT = namespace.addConstantDouble("PID_SWITCH_POINT", 40);

    public static final Supplier<Double> KP_LOW = lowPIDNamespace.addConstantDouble("KP", 1);
    public static final Supplier<Double> KI_LOW = lowPIDNamespace.addConstantDouble("KI", 1);
    public static final Supplier<Double> KD_LOW = lowPIDNamespace.addConstantDouble("KD", 1);
    public static final Supplier<Double> TOLERANCE_LOW = lowPIDNamespace.addConstantDouble("Tolerance", 1);
    public static final Supplier<Double> WAIT_TIME_LOW = lowPIDNamespace.addConstantDouble("Wait Time", 1);

    public static final Supplier<Double> KP_HIGH = highPIDNamespace.addConstantDouble("KP", 1);
    public static final Supplier<Double> KI_HIGH = highPIDNamespace.addConstantDouble("KI", 1);
    public static final Supplier<Double> KD_HIGH = highPIDNamespace.addConstantDouble("KD", 1);
    public static final Supplier<Double> TOLERANCE_HIGH = highPIDNamespace.addConstantDouble("Tolerance", 1);
    public static final Supplier<Double> WAIT_TIME_HIGH = highPIDNamespace.addConstantDouble("Wait Time", 1);

    private Gearbox gearbox;

    private DigitalInput topLimit;
    private DigitalInput bottomLimit;

    private TalonSRXEncoder encoder;

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

    public boolean isDown() {
        return bottomLimit.get();
    }

    public boolean isUp() {
        return topLimit.get();
    }

    public TalonSRXEncoder getEncoder() {
        return encoder;
    }

    @Override
    public void stop() {
        gearbox.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LowerLift());
    }
}
