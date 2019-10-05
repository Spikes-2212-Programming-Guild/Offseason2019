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
    public static final Namespace PID = NAMESPACE.addChild("PID");

    public static final Supplier<Double> KP = PID.addConstantDouble("KP", 0.5);
    public static final Supplier<Double> KI = PID.addConstantDouble("KI", 0.0);
    public static final Supplier<Double> KD = PID.addConstantDouble("KD", 0.0);
    public static final Supplier<Double> WAIT_TIME = PID.addConstantDouble("Wait Time", 0.1);
    public static final Supplier<Double> TOLERANCE = PID.addConstantDouble("Tolerance", 1.0);

    public static final PIDSettings PID_SETTINGS = new PIDSettings(KP, KI, KD, TOLERANCE, WAIT_TIME);

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
