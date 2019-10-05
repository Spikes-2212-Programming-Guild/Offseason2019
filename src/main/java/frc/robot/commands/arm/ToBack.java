package frc.robot.commands.arm;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import frc.robot.Robot;
import frc.robot.subsystems.Arm;

public class ToBack extends MoveGenericSubsystemWithPID {

    public ToBack() {
        super(Robot.arm, Robot.arm.getPotentiometer(), Arm.SETPOINT, Arm.BACK_PID_SETTINGS);
    }
}
