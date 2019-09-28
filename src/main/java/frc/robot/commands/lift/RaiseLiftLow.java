package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class RaiseLiftLow extends MoveGenericSubsystemWithPID {
    public RaiseLiftLow(double setpoint) {
        super(Robot.lift, Robot.lift.getEncoder(), setpoint,
                Lift.LOW_LEVEL_PID_SETTINGS);
    }
}
