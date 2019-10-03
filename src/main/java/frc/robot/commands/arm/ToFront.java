package frc.robot.commands.arm;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class ToFront extends MoveGenericSubsystemWithPID {

    public ToFront() {
        super(Robot.arm, Robot.arm.getPotentiometer(), Arm.SETPOINT, Arm.FRONT_PID_SETTINGS);
    }
}
