package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.motor.Gearbox;
import com.spikes2212.utils.TalonSRXEncoder;
import edu.wpi.first.wpilibj.*;
import frc.robot.subsystems.*;

public class SubsystemFactory {
    public static Drivetrain createDrivetrain(){
        Gearbox leftGearbox=new Gearbox(RobotMap.CAN.DRIVETRAIN_TALON_LEFT,new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_VICTOR_LEFT));
        Gearbox rightGearbox=new Gearbox(RobotMap.CAN.DRIVETRAIN_TALON_RIGHT,new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_VICTOR_RIGHT));
        Encoder leftEncoder = new Encoder(RobotMap.DIO.DRIVETRAIN_ENCODER_LEFT_A, RobotMap.DIO.DRIVETRAIN_ENCODER_LEFT_B);
        Encoder rightEncoder = new Encoder(RobotMap.DIO.DRIVETRAIN_ENCODER_RIGHT_A, RobotMap.DIO.DRIVETRAIN_ENCODER_RIGHT_B);
        leftEncoder.setDistancePerPulse(6*0.0254*Math.PI/360);
        rightEncoder.setDistancePerPulse(6*0.0254*Math.PI/360);
        return new Drivetrain(leftGearbox, rightGearbox, leftEncoder, rightEncoder, new ADXRS450_Gyro());
    }
    public static Gripper createGripper() {
        VictorSP leftMotor = new VictorSP(RobotMap.PWM.GRIPPER_LEFT);
        VictorSP rightMotor = new VictorSP(RobotMap.PWM.GRIPPER_RIGHT);
        leftMotor.setInverted(true);

        SpeedControllerGroup motor = new SpeedControllerGroup(leftMotor, rightMotor);
        DigitalInput limit = new DigitalInput(RobotMap.DIO.GRIPPER_LIMIT);
        return new Gripper(motor, limit);
    }
    public static Lift createLift() {
        WPI_TalonSRX slave0 = new WPI_TalonSRX(RobotMap.CAN.LIFT_SLAVE_0);
        WPI_TalonSRX slave1 = new WPI_TalonSRX(RobotMap.CAN.LIFT_SLAVE_1);

        Gearbox gearbox = new Gearbox(RobotMap.CAN.LIFT_MASTER,
                slave0, slave1);
        gearbox.setNeutralMode(NeutralMode.Brake);
        slave0.setNeutralMode(NeutralMode.Brake);
        slave1.setNeutralMode(NeutralMode.Brake);

        DigitalInput bottomLimit = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_BOTTOM);
        DigitalInput topLimit = new DigitalInput(RobotMap.DIO.LIFT_LIMIT_TOP);

        TalonSRXEncoder encoder = new TalonSRXEncoder(new WPI_TalonSRX(RobotMap.CAN.LIFT_ENCODER), Lift.DISTANCE_PER_PULSE.get());
        encoder.setDistancePerPulse(Lift.LIFT_DISTANCE_PER_PULSE);
        return new Lift(gearbox, topLimit, bottomLimit, encoder);
    }
    public static Latch createLatch(){
        Servo latchServo=new Servo(RobotMap.PWM.LATCH_SERVO);
        Latch latch=new Latch(latchServo);
        return latch;
    }
    public static Arm createArm(){
        VictorSP motor1=new VictorSP(RobotMap.PWM.ARM_MOTOR1);
        VictorSP motor2=new VictorSP(RobotMap.PWM.ARM_MOTOR2);
        SpeedControllerGroup motor=new SpeedControllerGroup(motor1,motor2);
        AnalogPotentiometer potentiometer=new AnalogPotentiometer(RobotMap.ANALOG.ARM_POTENTIOMETER);
        DigitalInput limitBack=new DigitalInput(RobotMap.DIO.ARM_BACK_LIMIT);
        DigitalInput limitFront=new DigitalInput(RobotMap.DIO.ARM_FRONT_LIMIT);
        return new Arm(motor,limitBack,limitFront,potentiometer);
    }

}
