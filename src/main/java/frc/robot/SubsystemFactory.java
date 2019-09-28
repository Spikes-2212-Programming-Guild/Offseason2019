package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.subsystems.Lift;

public class SubsystemFactory {
    public static Lift createLift() {
        Gearbox gearbox = new Gearbox(RobotMap.CAN.LIFT_MASTER,
                new WPI_TalonSRX(RobotMap.CAN.LIFT_SLAVE_0), new WPI_TalonSRX(RobotMap.CAN.LIFT_SLAVE_1));

        DigitalInput bottomLimit = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_BOTTOM);
        DigitalInput topLimit = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_TOP);

        TalonSRXEncoder encoder = new TalonSRXEncoder(new WPI_TalonSRX(RobotMap.CAN.LIFT_MASTER));

        return new Lift(gearbox, topLimit, bottomLimit, encoder);
    }
}
