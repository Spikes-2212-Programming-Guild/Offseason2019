package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.WaitForCondition;
import frc.robot.subsystems.Lift;

public class LowerLift extends CommandGroup {
    public LowerLift() {
        requires(Robot.lift);
        addSequential(new SetLiftState(Lift.LiftState.DOWN));
        addSequential(new WaitForCondition(() -> Robot.lift.getEncoder().pidGet() == Lift.SWITCH_POINT.get()));
        addSequential(new MoveGenericSubsystemWithPID(Robot.lift, Robot.lift.getEncoder(), 0, Lift.DOWN_PID_SETTINGS));
    }

    @Override
    protected boolean isFinished() {
        return Robot.lift.isDown();
    }
}
