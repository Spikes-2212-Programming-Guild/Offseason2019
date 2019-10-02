package frc.robot.subsystems;

import com.spikes2212.command.drivetrains.TankDrivetrain;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SpeedController;

public class Drivetrain extends TankDrivetrain {
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    private GyroBase gyro;

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
