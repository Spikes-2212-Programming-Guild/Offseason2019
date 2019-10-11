package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.Namespace;
import com.spikes2212.utils.PIDSettings;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.lift.LowerLift;
import frc.robot.commands.lift.RaiseLift;

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

    public static class TranslateLiftHeight implements Supplier<Double> {

        private Supplier<Double> wrapped;

        public TranslateLiftHeight(Supplier<Double> wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public Double get() {
            return wrapped.get() - INITIAL_HEIGHT.get();
        }
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
    public static final Supplier<Double> SWITCH_HEIGHT = NAMESPACE.addConstantDouble("Switch Point Height", 0.708);
    public static final Supplier<Double> INITIAL_HEIGHT = NAMESPACE.addConstantDouble("Initial Height", 0.395);

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

    public static final Supplier<Double> TOP_SETPOINT = new TranslateLiftHeight(SETPOINTS.addConstantDouble("top", 1.9));
    public static final Supplier<Double> LEVEL_2_SETPOINT = new TranslateLiftHeight(SETPOINTS.addConstantDouble("level 2", 0.926));
    public static final Supplier<Double> CARGO_SHIP_ABOVE_SETPOINT = new TranslateLiftHeight(SETPOINTS.addConstantDouble("cargo ship above", 0.572));
    public static final Supplier<Double> CARGO_SHIP_BELOW_SETPOINT = new TranslateLiftHeight(SETPOINTS.addConstantDouble("cargo ship below", 1.28));

    public static final double LIFT_DISTANCE_PER_PULSE = (1.9-0.395) / 80*4096;
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
    public void initTestingDashboard() {
        SmartDashboard.putData("lift/raise with constant speed", new MoveGenericSubsystem(this, Lift.TEST_SPEED));
        SmartDashboard.putData("lift/raise with PID", new RaiseLift(Lift.TEST_SETPOINT));
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new LowerLift());
    }


}
