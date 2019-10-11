package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.Namespace;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.arm.ToBack;
import frc.robot.commands.arm.ToFront;

import java.util.function.Supplier;

public class Arm extends GenericSubsystem {
    private SpeedController motor;
    private DigitalInput backLimit;
    private DigitalInput frontLimit;
    private AnalogPotentiometer potentiometer;
    public static final Namespace NAMESPACE = ConstantHandler.addNamespace("Arm");
    public static final Supplier<Double> TO_FRONT_SPEED = NAMESPACE.addConstantDouble("to front speed", 0.5);
    public static final Supplier<Double> TO_BACK_SPEED = NAMESPACE.addConstantDouble("to back speed", -0.5);
    public static final Namespace FRONT_PID =NAMESPACE.addChild("Front PID");
    public static final Supplier <Double> SETPOINT= FRONT_PID.addConstantDouble("setpoint",0.5);
    public static final Supplier <Double> TOLERANCE = FRONT_PID.addConstantDouble("tolerance",0.09);
    public static final Supplier <Double> WAIT_TIME= FRONT_PID.addConstantDouble("wait time",0.9);
    public static final Supplier <Double> FRONT_KP= FRONT_PID.addConstantDouble("kp",0.9);
    public static final Supplier <Double> FRONT_KI = FRONT_PID.addConstantDouble("ki",0.9);
    public static final Supplier <Double> FRONT_KD = FRONT_PID.addConstantDouble("kd",0.9);
    public static final PIDSettings FRONT_PID_SETTINGS =new PIDSettings(FRONT_KP, FRONT_KI, FRONT_KD, TOLERANCE,WAIT_TIME);

    public static final Namespace BACK_PID =NAMESPACE.addChild("Front PID");
    public static final Supplier <Double> BACK_KP= FRONT_PID.addConstantDouble("kp",0.9);
    public static final Supplier <Double> BACK_KI = FRONT_PID.addConstantDouble("ki",0.9);
    public static final Supplier <Double> BACK_KD = FRONT_PID.addConstantDouble("kd",0.9);
    public static final PIDSettings BACK_PID_SETTINGS =new PIDSettings(BACK_KP, BACK_KI, BACK_KD, TOLERANCE,WAIT_TIME);

    public Arm(SpeedController motor, DigitalInput backLimit, DigitalInput frontLimit, AnalogPotentiometer potentiometer) {
        this.motor = motor;
        this.backLimit = backLimit;
        this.frontLimit = frontLimit;
        this.potentiometer = potentiometer;

        addChild(motor);
        addChild(backLimit);
        addChild(frontLimit);
        addChild(potentiometer);
    }

    @Override
    public void apply(double v) {
        motor.set(v);
    }

    @Override
    public boolean canMove(double v) {
            return v < 0 && backLimit.get() || v > 0 && frontLimit.get();
    }

    @Override
    public void stop() {
        motor.stopMotor();
    }

    @Override
    public void initTestingDashboard() {
        SmartDashboard.putData("arm/to front", new ToFront());
        SmartDashboard.putData("arm/to back", new ToBack());
    }

    public AnalogPotentiometer getPotentiometer() {
        return potentiometer;
    }

    public double getPotentiometerValue() {
        return potentiometer.get();
    }

    public boolean isFront() {
        return frontLimit.get();
    }

    public boolean isBack() {
        return backLimit.get();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
