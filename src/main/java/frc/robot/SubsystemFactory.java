package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.motor.Gearbox;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.Lift;

public class SubsystemFactory {
    public static Lift createLift() {
        Gearbox gearbox = new Gearbox(RobotMap.CAN.MASTER,
                new WPI_TalonSRX(RobotMap.CAN.SLAVE_0), new WPI_TalonSRX(RobotMap.CAN.SLAVE_1));

        DigitalInput bottomLimit = new DigitalInput(RobotMap.DIO.LIMIT_BOTTOM);
        DigitalInput topLimit = new DigitalInput(RobotMap.DIO.LIMIT_TOP);

        return new Lift(gearbox, topLimit, bottomLimit);
    }
}
