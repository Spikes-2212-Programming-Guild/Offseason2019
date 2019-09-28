package frc.robot.commands.latch;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LatchClose extends Command {
    public LatchClose(){
        requires(Robot.latch);
    }

    @Override
    protected void execute() {
        Robot.latch.close();
    }

    @Override
    protected boolean isFinished() {
        return Robot.latch.isClose();
    }

}
