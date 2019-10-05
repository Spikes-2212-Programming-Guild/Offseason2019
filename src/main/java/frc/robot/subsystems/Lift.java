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
                return speed == 0.0 || !Robot.lift.isUp() && speed > Lift.STAYING_SPEED.get();
            }
        }, STILL {
            @Override
            public boolean canMove(double speed) {
                return speed == Lift.STAYING_SPEED.get();
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
    public static final Namespace UP_PID_NAMESPACE = NAMESPACE.addChild("Up PID");
    public static final Namespace DOWN_PID_NAMESPACE = NAMESPACE.addChild("Down PID");
    public static final Namespace SETPOINTS = NAMESPACE.addChild("setpoints");

    public static final Supplier<Double> STAYING_SPEED = NAMESPACE.addConstantDouble("staying speed", 0.04);

    public static final Supplier<Double> TEST_SPEED = NAMESPACE.addConstantDouble("Test Speed", 0.6);
    public static final Supplier<Double> TEST_SETPOINT = NAMESPACE.addConstantDouble("Test Setpoint", 70);

    public static final Supplier<Double> MAX_SPEED = NAMESPACE.addConstantDouble("Max Speed", 0.6);
    public static final Supplier<Double> MIN_SPEED = NAMESPACE.addConstantDouble("Min Speed", -0.6);
    public static final Supplier<Double> DISTANCE_PER_PULSE = NAMESPACE.addConstantDouble("Distance per Pulse", (1.0 / 1024.0));

    public static final Supplier<Double> SWITCH_POINT = NAMESPACE.addConstantDouble("PID Switch Point", 40);

    public static final Supplier<Double> KP_UP = UP_PID_NAMESPACE.addConstantDouble("KP", 1);
    public static final Supplier<Double> KI_UP = UP_PID_NAMESPACE.addConstantDouble("KI", 1);
    public static final Supplier<Double> KD_UP = UP_PID_NAMESPACE.addConstantDouble("KD", 1);
    public static final Supplier<Double> TOLERANCE_UP = UP_PID_NAMESPACE.addConstantDouble("Tolerance", 1);
    public static final Supplier<Double> WAIT_TIME_UP = UP_PID_NAMESPACE.addConstantDouble("Wait Time", 1);

    public static final Supplier<Double> KP_DOWN = DOWN_PID_NAMESPACE.addConstantDouble("KP", 1);
    public static final Supplier<Double> KI_DOWN = DOWN_PID_NAMESPACE.addConstantDouble("KI", 1);
    public static final Supplier<Double> KD_DOWN = DOWN_PID_NAMESPACE.addConstantDouble("KD", 1);
    public static final Supplier<Double> TOLERANCE_DOWN = DOWN_PID_NAMESPACE.addConstantDouble("Tolerance", 1);
    public static final Supplier<Double> WAIT_TIME_DOWN = DOWN_PID_NAMESPACE.addConstantDouble("Wait Time", 1);

    public static final Supplier<Double> TOP_SETPOINT = SETPOINTS.addConstantDouble("top", 80);
    public static final Supplier<Double> LEVEL_1_SETPOINT = SETPOINTS.addConstantDouble("level 1", 0);
    public static final Supplier<Double> LEVEL_2_SETPOINT = SETPOINTS.addConstantDouble("level 2", 30);
    public static final Supplier<Double> CARGO_SHIP_ABOVE_SETPOINT = SETPOINTS.addConstantDouble("cargo ship above", 10);
    public static final Supplier<Double> CARGO_SHIP_BELOW_SETPOINT = SETPOINTS.addConstantDouble("cargo ship below", 50);

    public static final PIDSettings UP_PID_SETTINGS =
            new PIDSettings(KP_UP, KI_UP, KD_UP, TOLERANCE_UP, WAIT_TIME_UP);

    public static final PIDSettings DOWN_PID_SETTINGS =
            new PIDSettings(KP_DOWN, KI_DOWN, KD_DOWN, TOLERANCE_DOWN, WAIT_TIME_DOWN);

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

    public LiftState getState() {
        return state;
    }

    public void setState(LiftState state) {
        this.state = state;
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
