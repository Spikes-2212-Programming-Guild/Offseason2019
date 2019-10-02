package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.motor.Gearbox;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.*;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;

import frc.robot.subsystems.Latch;

public class SubsystemFactory {
    public static Drivetrain createDrivetrain(){
        Gearbox leftGearbox=new Gearbox(RobotMap.CAN.DRIVETRAIN_TALON_LEFT,new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_VICTOR_LEFT));
        Gearbox rightGearbox=new Gearbox(RobotMap.CAN.DRIVETRAIN_TALON_RIGHT,new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_VICTOR_RIGHT));
        Encoder leftEncoder = new Encoder(RobotMap.DIO.DRIVETRAIN_ENCODER_LEFT_A, RobotMap.DIO.DRIVETRAIN_ENCODER_LEFT_B);
        Encoder rightEncoder = new Encoder(RobotMap.DIO.DRIVETRAIN_ENCODER_RIGHT_A, RobotMap.DIO.DRIVETRAIN_ENCODER_RIGHT_B);
        return new Drivetrain(leftGearbox, rightGearbox, leftEncoder, rightEncoder, new ADXRS450_Gyro());
    }
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
    public static Latch createLatch(){
        Servo latchServo=new Servo(RobotMap.PWM.LATCH_SERVO);
        Latch latch=new Latch(latchServo);
        return latch;
    }

}
