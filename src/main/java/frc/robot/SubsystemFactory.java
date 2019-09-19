package frc.robot;

import edu.wpi.first.wpilibj.Servo;
import frc.robot.subsystems.Latch;

public class SubsystemFactory {
    public static Latch createLatch(){
        Servo latchServo=new Servo(RobotMap.PWM.LATCH_SERVO);
        Latch latch=new Latch(latchServo);
        return latch;
    }

}
