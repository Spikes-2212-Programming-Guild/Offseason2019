package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LowerLift extends Command {
    public LowerLift() {
        requires(Robot.lift);
    }

    @Override
    protected boolean isFinished() {
        return Robot.lift.isDown();
    }
}
