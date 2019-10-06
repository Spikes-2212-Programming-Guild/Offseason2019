package frc.robot.commands.lift;

import com.spikes2212.utils.RunnableCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class SetLiftState extends RunnableCommand {

    public SetLiftState(Lift.LiftState state) {
        super(() -> Robot.lift.setState(state));
        requires(Robot.lift);
    }
}
