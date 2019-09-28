/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class RobotMap {
    public interface PWM{
        int GRIPPER_RIGHT=0;
        int GRIPPER_LEFT=1;
    }
    public interface DIO{
        int LIMIT_SWITCH =6;
        int LIFT_LIMIT_BOTTOM = 8;
        int LIFT_LIMIT_TOP = 9;
    }
    public static interface CAN {
        int LIFT_MASTER = 0;
        int LIFT_SLAVE_0 = 2;
        int LIFT_SLAVE_1 = 3;
        int LIFT_ENCODER = 8;
    }
}
