/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class RobotMap {
    public static interface CAN {
        int LIFT_MASTER = 0;
        int LIFT_SLAVE_0 = 1;
        int LIFT_SLAVE_1 = 2;
    }

    public static interface DIO {
        int LIFT_LIMIT_BOTTOM = 0;
        int LIFT_LIMIT_TOP = 1;
    }
}
