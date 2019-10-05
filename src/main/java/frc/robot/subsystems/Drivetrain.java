package frc.robot.subsystems;

import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.Namespace;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SpeedController;

import java.util.function.Supplier;

public class Drivetrain extends TankDrivetrain {
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private GyroBase gyro;

    public static final Namespace NAMESPACE = ConstantHandler.addNamespace("Drivetrain");
    public static final Namespace FORWARD_PID = NAMESPACE.addChild("Forward PID");

    public static final Supplier<Double> FORWARD_KP = FORWARD_PID.addConstantDouble("KP", 0.7);
    public static final Supplier<Double> FORWARD_KI = FORWARD_PID.addConstantDouble("KI", 0.0001);
    public static final Supplier<Double> FORWARD_KD = FORWARD_PID.addConstantDouble("KD", -0.01);
    public static final Supplier<Double> FORWARD_WAIT_TIME = FORWARD_PID.addConstantDouble("Wait Time", 0.3);
    public static final Supplier<Double> FORWARD_TOLERANCE = FORWARD_PID.addConstantDouble("Tolerance", 0.1);

    public static final PIDSettings FORWARD_PID_SETTINGS = new PIDSettings(FORWARD_KP, FORWARD_KI, FORWARD_KD, FORWARD_TOLERANCE, FORWARD_WAIT_TIME);

    public static final Namespace ORIENT_PID = NAMESPACE.addChild("Orient PID");

    public static final Supplier<Double> ORIENT_KP = ORIENT_PID.addConstantDouble("KP", 0.07);
    public static final Supplier<Double> ORIENT_KI = ORIENT_PID.addConstantDouble("KI", 0.0003);
    public static final Supplier<Double> ORIENT_KD = ORIENT_PID.addConstantDouble("KD", -0.001);
    public static final Supplier<Double> ORIENT_WAIT_TIME = ORIENT_PID.addConstantDouble("Wait Time", 0.2);
    public static final Supplier<Double> ORIENT_TOLERANCE = ORIENT_PID.addConstantDouble("Tolerance", 0.5);

    public static final Supplier<Double> TEST_SETPOINT = NAMESPACE.addConstantDouble("test setpoint", 3);

    public static final PIDSettings ORIENT_PID_SETTINGS = new PIDSettings(ORIENT_KP, ORIENT_KI, ORIENT_KD, ORIENT_TOLERANCE, ORIENT_WAIT_TIME);


    public Drivetrain(SpeedController left, SpeedController right, Encoder leftEncoder, Encoder rightEncoder, GyroBase gyro) {
        super(left, right);
        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;
        this.gyro = gyro;

        addChild(left);
        addChild(right);
        addChild(leftEncoder);
        addChild(rightEncoder);
        addChild(gyro);
    }

    @Override
    public void setRight(double speed) {
        super.setRight(-speed);
    }

    public Encoder getLeftEncoder() {
        return leftEncoder;
    }
    
    public Encoder getRightEncoder() {
        return rightEncoder;
    }
}
