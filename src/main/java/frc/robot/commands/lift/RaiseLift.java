package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class RaiseLift extends CommandGroup {
    public RaiseLift(double setpoint) {
        addSequential(new SetLiftState(Lift.LiftState.UP));
        addSequential(new MoveGenericSubsystemWithPID(Robot.lift, Robot.lift.getEncoder(), setpoint, Robot.lift.UP_PID_SETTINGS));
        addSequential(new SetLiftState(Lift.LiftState.STILL));
        addSequential(new MoveGenericSubsystem(Robot.lift, Lift.STAYING_SPEED));
    }
}
