package frc.robot;

import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;

public class SubsystemFactory {
    public static GenericSubsystem createGripper() {
        SpeedControllerGroup gripperSpeedcontrollers = new
                SpeedControllerGroup(new VictorSP(RobotMap.PWM.gripperLeft), new VictorSP(RobotMap.PWM.gripperRight));
        GenericSubsystem gripper = new GenericSubsystem() {
            @Override
            public void apply(double v) {
                gripperSpeedcontrollers.set(v);
            }

            @Override
            public boolean canMove(double v) {
               if(v > 0 ){
                   return true;
               }
                DigitalInput limit= new DigitalInput(RobotMap.DIO.limitSwitch);
                return !limit.get();
            }

            @Override
            public void stop() {
            gripperSpeedcontrollers.stopMotor();
            }

            @Override
            protected void initDefaultCommand() {

            }
        };
        return gripper;
    }
}

