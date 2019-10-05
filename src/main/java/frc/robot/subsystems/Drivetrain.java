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

    public static final Supplier<Double> FORWARD_KP = FORWARD_PID.addConstantDouble("KP", 0.5);
    public static final Supplier<Double> FORWARD_KI = FORWARD_PID.addConstantDouble("KI", 0.0);
    public static final Supplier<Double> FORWARD_KD = FORWARD_PID.addConstantDouble("KD", 0.0);
    public static final Supplier<Double> FORWARD_WAIT_TIME = FORWARD_PID.addConstantDouble("Wait Time", 0.1);
    public static final Supplier<Double> FORWARD_TOLERANCE = FORWARD_PID.addConstantDouble("Tolerance", 1.0);

    public static final PIDSettings FORWARD_PID_SETTINGS = new PIDSettings(FORWARD_KP, FORWARD_KI, FORWARD_KD, FORWARD_TOLERANCE, FORWARD_WAIT_TIME);

    public static final Namespace GYRO_PID = NAMESPACE.addChild("Forward PID");

    public static final Supplier<Double> GYRO_KP = GYRO_PID.addConstantDouble("KP", 0.5);
    public static final Supplier<Double> GYRO_KI = GYRO_PID.addConstantDouble("KI", 0.0);
    public static final Supplier<Double> GYRO_KD = GYRO_PID.addConstantDouble("KD", 0.0);
    public static final Supplier<Double> GYRO_WAIT_TIME = GYRO_PID.addConstantDouble("Wait Time", 0.1);
    public static final Supplier<Double> GYRO_TOLERANCE = GYRO_PID.addConstantDouble("Tolerance", 1.0);

    public static final PIDSettings GYRO_PID_SETTINGS = new PIDSettings(GYRO_KP, GYRO_KI, GYRO_KD, GYRO_TOLERANCE, GYRO_WAIT_TIME);


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
}
