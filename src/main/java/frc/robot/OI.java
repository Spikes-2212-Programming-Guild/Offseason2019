/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI /* GEVALD! */ {
    private static Joystick leftJoystick=new Joystick(0);
    private static Joystick rightJoystick=new Joystick(2);

    public OI(){

    }
    public static double getLeftX(){
        return leftJoystick.getX();
    }
    public static double getLeftY(){
        return -leftJoystick.getY();
    }
    public static double getRightX() {
        return rightJoystick.getX();
    }
    public static double getRightY() {
        return -rightJoystick.getY();
    }
}
