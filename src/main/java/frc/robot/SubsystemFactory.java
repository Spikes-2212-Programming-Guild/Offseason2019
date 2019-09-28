package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import frc.robot.subsystem.Gripper;
import frc.robot.subsystems.Lift;

public class SubsystemFactory {
    public static GenericSubsystem createGripper() {
        SpeedControllerGroup motor = new SpeedControllerGroup
                (new VictorSP(RobotMap.PWM.GRIPPER_LEFT), new VictorSP(RobotMap.PWM.GRIPPER_RIGHT));
        DigitalInput limit = new DigitalInput(RobotMap.DIO.LIMIT_SWITCH);
        return new Gripper(motor, limit);
    }
    public static Lift createLift() {
        Gearbox gearbox = new Gearbox(RobotMap.CAN.LIFT_MASTER,
                new WPI_TalonSRX(RobotMap.CAN.LIFT_SLAVE_0), new WPI_TalonSRX(RobotMap.CAN.LIFT_SLAVE_1));
        gearbox.setNeutralMode(NeutralMode.Brake);

        DigitalInput bottomLimit = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_BOTTOM);
        DigitalInput topLimit = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_TOP);

        TalonSRXEncoder encoder = new TalonSRXEncoder(new WPI_TalonSRX(RobotMap.CAN.LIFT_ENCODER));

        return new Lift(gearbox, topLimit, bottomLimit, encoder);
    }
}


