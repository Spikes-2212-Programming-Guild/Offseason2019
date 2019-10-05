/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

public class RobotMap {
    public interface CAN {
        int DRIVETRAIN_TALON_LEFT = 5;
        int DRIVETRAIN_TALON_RIGHT = 4;
        int DRIVETRAIN_VICTOR_LEFT = 6;
        int DRIVETRAIN_VICTOR_RIGHT = 1;
        int LIFT_MASTER = 0;
        int LIFT_SLAVE_0 = 2;
        int LIFT_SLAVE_1 = 3;
        int LIFT_ENCODER = 8;
    }

    public interface PWM {
        int LATCH_SERVO = 9;
        int GRIPPER_RIGHT = 0;
        int GRIPPER_LEFT = 1;
        int ARM_MOTOR1 = 6;
        int ARM_MOTOR2 = 7;
    }

    public interface DIO {
        int GRIPPER_LIMIT = 8;
        int LIFT_LIMIT_BOTTOM = 9;
        int LIFT_LIMIT_TOP = 7;
        int DRIVETRAIN_ENCODER_LEFT_A = 0;
        int DRIVETRAIN_ENCODER_LEFT_B = 1;
        int DRIVETRAIN_ENCODER_RIGHT_A = 3;
        int DRIVETRAIN_ENCODER_RIGHT_B = 2;
        int ARM_FRONT_LIMIT = 4;
        int ARM_BACK_LIMIT = 5;
    }

    public interface ANALOG {
        int ARM_POTENTIOMETER = 1;
    }

}
