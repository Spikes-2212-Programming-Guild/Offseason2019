package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.motor.Gearbox;

public class SubsystemFactory {
    public static TankDrivetrain createDrivetrain(){
        Gearbox leftGearbox=new Gearbox(RobotMap.CAN.DRIVETRAIN_TALON_LEFT,new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_VICTOR_LEFT));
        Gearbox rightGearbox=new Gearbox(RobotMap.CAN.DRIVETRAIN_TALON_RIGHT,new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_VICTOR_RIGHT));
        return new TankDrivetrain(leftGearbox,rightGearbox);
    }
}
