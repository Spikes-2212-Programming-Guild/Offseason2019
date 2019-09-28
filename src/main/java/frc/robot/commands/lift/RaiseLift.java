package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.Lift;

public class RaiseLift extends CommandGroup {
    public RaiseLift(double setpoint) {
        addSequential(new RaiseLiftLow(Math.min(setpoint, Lift.PID_SWITCH_POINT.get())));
        if(setpoint < Lift.PID_SWITCH_POINT.get())
            addSequential(new RaiseLiftHigh(setpoint));
    }
}
