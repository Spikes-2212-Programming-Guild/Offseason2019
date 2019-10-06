package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class RaiseLiftNoPID extends CommandGroup {
	
	public RaiseLiftNoPID() {
		addSequential(new SetLiftState(Lift.LiftState.UP));
		addSequential(new MoveGenericSubsystem(Robot.lift, Lift.TEST_SPEED));
	}
}
