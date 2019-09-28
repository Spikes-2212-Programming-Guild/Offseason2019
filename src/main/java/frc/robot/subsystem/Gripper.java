package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.dashboard.ConstantHandler;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.RobotMap;

import java.util.function.Supplier;

public class Gripper extends GenericSubsystem {

    public static Supplier<Double> IN_SPEED = ConstantHandler.addConstantDouble("gripper in speed", -0.7);
    public static Supplier<Double> OUT_SPEED = ConstantHandler.addConstantDouble("gripper out speed", 0.7);

    SpeedControllerGroup gripperSC = new SpeedControllerGroup
            (new WPI_TalonSRX(RobotMap.PWM.GRIPPER_RIGHT),
                    new WPI_TalonSRX(RobotMap.PWM.GRIPPER_LEFT));

    DigitalInput limit = new DigitalInput(RobotMap.DIO.limitSwitch);

    @Override
    public void apply(double v) {
        gripperSC.set(v);
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
        gripperSC.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {

    }
}
