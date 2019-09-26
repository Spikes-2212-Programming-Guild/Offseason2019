package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.motor.Gearbox;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Lift extends GenericSubsystem {
    Gearbox gearbox = new Gearbox(RobotMap.CAN.MASTER,
            new WPI_TalonSRX(RobotMap.CAN.SLAVE_0), new WPI_TalonSRX(RobotMap.CAN.SLAVE_1));

    DigitalInput topLimit = new DigitalInput(RobotMap.DIO.LIMIT_TOP);
    DigitalInput bottomLimit = new DigitalInput(RobotMap.DIO.LIMIT_BOTTOM);

    @Override
    public void apply(double v) {
        gearbox.set(v);
    }

    @Override
    public boolean canMove(double v) {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    protected void initDefaultCommand() {

    }
}
