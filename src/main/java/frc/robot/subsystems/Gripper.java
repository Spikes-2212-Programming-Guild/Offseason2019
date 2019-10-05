package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.Namespace;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import java.util.function.Supplier;

public class Gripper extends GenericSubsystem {

    public static final Namespace NAMESPACE = ConstantHandler.addNamespace("Gripper");

    public static final Supplier<Double> IN_SPEED = NAMESPACE.addConstantDouble("in speed", -0.7);
    public static final Supplier<Double> OUT_SPEED = NAMESPACE.addConstantDouble("out speed", 0.7);

    private DigitalInput limit;
    private SpeedControllerGroup motor;
    public Gripper(SpeedControllerGroup motor, DigitalInput gripperLimit) {
        this.motor = motor;
        this.limit = gripperLimit;
        addChild(motor);
        addChild(gripperLimit);
    }
    @Override
    public void apply(double v) {
        motor.set(v);
    }

    @Override
    public boolean canMove(double v) {
        if(v > 0) {
            return true;
        }
        return !limit.get();
    }

    @Override
    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void initTestingDashboard() {
        SmartDashboard.putData("gripper/in", new MoveGenericSubsystem(Robot.gripper, Gripper.IN_SPEED.get()));
        SmartDashboard.putData("gripper/out", new MoveGenericSubsystem(Robot.gripper, Gripper.OUT_SPEED.get()));
    }

    @Override
    protected void initDefaultCommand() {

    }
}
