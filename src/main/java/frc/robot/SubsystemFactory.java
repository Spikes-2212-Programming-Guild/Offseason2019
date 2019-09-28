package frc.robot;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.subsystem.Gripper;

public class SubsystemFactory {
    public static GenericSubsystem createGripper() {
        SpeedControllerGroup gripperSC = new SpeedControllerGroup
                (new VictorSP(RobotMap.PWM.GRIPPER_LEFT), new VictorSP(RobotMap.PWM.GRIPPER_RIGHT));
        DigitalInput limit = new DigitalInput(RobotMap.DIO.limitSwitch);
        return new Gripper(gripperSC, limit);
    }

}


