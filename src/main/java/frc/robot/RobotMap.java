/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class RobotMap {
    public interface CAN{
        int DRIVETRAIN_TALON_LEFT =5;
        int DRIVETRAIN_TALON_RIGHT =4;
        int DRIVETRAIN_VICTOR_LEFT =6;
        int DRIVETRAIN_VICTOR_RIGHT =1;
        int LIFT_MASTER = 0;
        int LIFT_SLAVE_0 = 2;
        int LIFT_SLAVE_1 = 3;
        int LIFT_ENCODER = 8;
    }
    public interface PWM{
        int LATCH_SERVO=9;
        int GRIPPER_RIGHT=0;
        int GRIPPER_LEFT=1;
    }
    public interface DIO{
        int LIMIT_SWITCH =6;
        int LIFT_LIMIT_BOTTOM = 8;
        int LIFT_LIMIT_TOP = 9;
    }

}
