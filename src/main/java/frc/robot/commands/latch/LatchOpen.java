package frc.robot.commands.latch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LatchOpen extends Command {
    public LatchOpen(){
        requires(Robot.latch);
    }

    @Override
    protected void execute() {
        Robot.latch.open();
    }

    @Override
    protected boolean isFinished() {
        return Robot.latch.isOpen();
    }

}
