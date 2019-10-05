package frc.robot.subsystems;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.Namespace;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.latch.LatchClose;
import frc.robot.commands.latch.LatchOpen;

import java.util.function.Supplier;

public class Latch extends Subsystem {

    public static final Namespace LATCH_NAMESPACE = ConstantHandler.addNamespace("Latch");

    public static final Supplier<Double> OPEN_ANGLE = LATCH_NAMESPACE.addConstantDouble("Open Angle", 45.0);
    public static final Supplier<Double> CLOSE_ANGLE = LATCH_NAMESPACE.addConstantDouble("Close Angle", 0.0);
    private Servo servo;

    public Latch(Servo servo) {
        this.servo = servo;

        addChild(servo);
    }

    public void open() {
        servo.setAngle(OPEN_ANGLE.get());
    }

    public void close() {
        servo.setAngle(CLOSE_ANGLE.get());
    }

    public boolean isOpen() {
        return servo.getAngle() == OPEN_ANGLE.get();
    }

    public boolean isClose() {
        return servo.getAngle() == CLOSE_ANGLE.get();
    }

    public void initTestingDashboard() {
        SmartDashboard.putData("latch/open", new LatchOpen());
        SmartDashboard.putData("latch/close", new LatchClose());
    }

    @Override
    protected void initDefaultCommand() {
    }
}
