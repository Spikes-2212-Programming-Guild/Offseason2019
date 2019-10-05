package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.Namespace;
import com.spikes2212.utils.PIDSettings;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Robot;
import frc.robot.commands.lift.LowerLift;

import java.util.function.Supplier;

public class Lift extends GenericSubsystem {

    public enum LiftState {
        UP {
            @Override
            public boolean canMove(double speed) {
                return !Robot.lift.isUp() && speed > Lift.STAYING_SPEED.get();
            }
        }, STILL {
            @Override
            public boolean canMove(double speed) {
                return false;
            }
        }, DOWN {
            @Override
            public boolean canMove(double speed) {
                return !Robot.lift.isDown() && speed < Lift.STAYING_SPEED.get();
            }
        };

        public abstract boolean canMove(double speed);
    }

    public static final Namespace NAMESPACE = ConstantHandler.addNamespace("Lift");
    public static final Namespace LOW_PID_NAMESPACE = NAMESPACE.addChild("LowPID");
    public static final Namespace HIGH_PID_NAMESPACE = NAMESPACE.addChild("HighPID");

    public static final Supplier<Double> STAYING_SPEED = NAMESPACE.addConstantDouble("staying speed", 0.04);

    public static final Supplier<Double> TEST_SPEED = NAMESPACE.addConstantDouble("Test Speed", 0.6);
    public static final Supplier<Double> TEST_SETPOINT = NAMESPACE.addConstantDouble("Test Setpoint", 70);

    public static final Supplier<Double> MAX_SPEED = NAMESPACE.addConstantDouble("Max Speed", 0.6);
    public static final Supplier<Double> MIN_SPEED = NAMESPACE.addConstantDouble("Min Speed", -0.6);
    public static final Supplier<Double> DISTANCE_PER_PULSE = NAMESPACE.addConstantDouble("Distance per Pulse", (1.0 / 1024.0));

    public static final Supplier<Double> PID_SWITCH_POINT = NAMESPACE.addConstantDouble("PID Switch Point", 40);

    public static final Supplier<Double> KP_LOW_LEVEL = LOW_PID_NAMESPACE.addConstantDouble("KP", 1);
    public static final Supplier<Double> KI_LOW_LEVEL = LOW_PID_NAMESPACE.addConstantDouble("KI", 1);
    public static final Supplier<Double> KD_LOW_LEVEL = LOW_PID_NAMESPACE.addConstantDouble("KD", 1);
    public static final Supplier<Double> TOLERANCE_LOW_LEVEL = LOW_PID_NAMESPACE.addConstantDouble("Tolerance", 1);
    public static final Supplier<Double> WAIT_TIME_LOW_LEVEL = LOW_PID_NAMESPACE.addConstantDouble("Wait Time", 1);

    public static final Supplier<Double> KP_HIGH_LEVEL = HIGH_PID_NAMESPACE.addConstantDouble("KP", 1);
    public static final Supplier<Double> KI_HIGH_LEVEL = HIGH_PID_NAMESPACE.addConstantDouble("KI", 1);
    public static final Supplier<Double> KD_HIGH_LEVEL = HIGH_PID_NAMESPACE.addConstantDouble("KD", 1);
    public static final Supplier<Double> TOLERANCE_HIGH_LEVEL = HIGH_PID_NAMESPACE.addConstantDouble("Tolerance", 1);
    public static final Supplier<Double> WAIT_TIME_HIGH_LEVEL = HIGH_PID_NAMESPACE.addConstantDouble("Wait Time", 1);

    public static final PIDSettings LOW_LEVEL_PID_SETTINGS =
            new PIDSettings(KP_LOW_LEVEL, KI_LOW_LEVEL, KD_LOW_LEVEL, TOLERANCE_LOW_LEVEL, WAIT_TIME_LOW_LEVEL);

    public static final PIDSettings HIGH_LEVEL_PID_SETTINGS =
            new PIDSettings(KP_HIGH_LEVEL, KI_HIGH_LEVEL, KD_HIGH_LEVEL, TOLERANCE_HIGH_LEVEL, WAIT_TIME_HIGH_LEVEL);

    private Gearbox gearbox;

    private DigitalInput topLimit;
    private DigitalInput bottomLimit;

    private TalonSRXEncoder encoder;

    private LiftState state;

    public Lift(Gearbox gearbox, DigitalInput topLimit, DigitalInput bottomLimit, TalonSRXEncoder encoder) {
        super(MIN_SPEED, MAX_SPEED);
        this.gearbox = gearbox;
        this.topLimit = topLimit;
        this.bottomLimit = bottomLimit;
        this.encoder = encoder;
        this.state = LiftState.DOWN;

        addChild(gearbox);
        addChild(topLimit);
        addChild(bottomLimit);
        addChild(encoder);
    }

    @Override
    public void apply(double speed) {
        gearbox.set(speed);
    }

    @Override
    public boolean canMove(double speed) {
        if (speed == STAYING_SPEED.get()) {
            return true;
        }
        return state.canMove(speed);
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
