package frc.robot.commands.lift;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystemWithPID;
import com.spikes2212.utils.PIDSettings;
import edu.wpi.first.wpilibj.PIDSource;
import frc.robot.Robot;
import frc.robot.subsystems.Lift;

public class RaiseLiftLow extends MoveGenericSubsystemWithPID {
    public RaiseLiftLow(PIDSource source, double setpoint) {
        super(Robot.lift, source, setpoint,
                new PIDSettings(Lift.KP_LOW, Lift.KI_LOW, Lift.KD_LOW, Lift.TOLERANCE_LOW, Lift.WAIT_TIME_LOW));
    }
}
