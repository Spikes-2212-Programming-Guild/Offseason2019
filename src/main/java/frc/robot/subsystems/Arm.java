package frc.robot.subsystems;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.utils.Namespace;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import java.util.function.Supplier;

public class Arm extends GenericSubsystem {
    private SpeedControllerGroup armMotor;
    private DigitalInput backLimit;
    private DigitalInput frontLimit;
    private AnalogPotentiometer potentiometer;
    public static final Namespace NAMESPACE = ConstantHandler.addNamespace("Arm");
    public static final Supplier<Double> TO_FRONT_SPEED = NAMESPACE.addConstantDouble("to front speed", 0.5);
    public static final Supplier<Double> TO_BACK_SPEED = NAMESPACE.addConstantDouble("to back speed", -0.5);
    public static final Namespace PID=NAMESPACE.addChild("PID");
    public static final Supplier <Double> SETPOINT=PID.addConstantDouble("setpoint",0.5);
    public static final Supplier <Double> TOLETRANCE=PID.addConstantDouble("tolerance",0.09);
    public static final Supplier <Double> WAIT_TIME=PID.addConstantDouble("wait time",0.9);
    public static final Supplier <Double> KP=PID.addConstantDouble("kp",0.9);
    public static final Supplier <Double> KI=PID.addConstantDouble("ki",0.9);
    public static final Supplier <Double> KD=PID.addConstantDouble("kd",0.9);

    public Arm(SpeedControllerGroup armMotor, DigitalInput backLimit, DigitalInput frontLimit, AnalogPotentiometer potentiometer) {
        this.armMotor = armMotor;
        this.backLimit = backLimit;
        this.frontLimit = frontLimit;
        this.potentiometer = potentiometer;
    }

    @Override
    public void apply(double v) {
        armMotor.set(v);
    }

    @Override
    public boolean canMove(double v) {
        if (v < 0 && backLimit.get() || v > 0 && frontLimit.get())
            return false;
        return true;
    }

    @Override
    public void stop() {
        armMotor.stopMotor();
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
