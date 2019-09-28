package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class RaiseLiftHigh extends MoveGenericSubsystemWithPID {
    public RaiseLiftHigh(PIDSource source, double setpoint) {
        super(Robot.lift, source, setpoint,
                new PIDSettings(Lift.KP_HIGH, Lift.KI_HIGH, Lift.KD_HIGH, Lift.TOLERANCE_HIGH, Lift.WAIT_TIME_HIGH));
    }
}
