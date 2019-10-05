package frc.robot.commands.lift;

import com.spikes2212.utils.RunnableCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class LowerLift extends CommandGroup {
    public LowerLift() {
        requires(Robot.lift);
        addSequential(new SetLiftState(Lift.LiftState.DOWN));
    }

    @Override
    protected boolean isFinished() {
        return Robot.lift.isDown();
    }
}
