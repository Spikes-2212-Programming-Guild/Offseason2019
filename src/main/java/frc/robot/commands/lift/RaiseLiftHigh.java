package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class RaiseLiftHigh extends MoveGenericSubsystemWithPID {
    public RaiseLiftHigh(double setpoint) {
        super(Robot.lift, Robot.lift.getEncoder(), setpoint,
                Lift.HIGH_LEVEL_PID_SETTINGS);
    }
}
