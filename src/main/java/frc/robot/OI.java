/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import com.spikes2212.utils.XboXUID;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.commands.arm.ToBack;
import frc.robot.commands.arm.ToFront;
import frc.robot.commands.lift.LowerLift;
import frc.robot.commands.lift.RaiseLift;
import frc.robot.commands.lift.RaiseLiftNoPID;
import frc.robot.commands.lift.SetLiftState;
import frc.robot.subsystems.Gripper;

import frc.robot.commands.latch.LatchClose;
import frc.robot.commands.latch.LatchOpen;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.Lift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI /* Gevald! */ {
    private XboXUID xbox = new XboXUID(2);
    private Button openLatch = xbox.getLBButton();
    private Button closeLatch = xbox.getLTButton();
    private Button gripperIn = xbox.getRBButton();
    private Button gripperOut = xbox.getRTButton();
    private Button moveArmFront = xbox.getBlueButton();
    private Button moveArmBack = xbox.getRedButton();
    private Button moveLiftLevel1 = xbox.getGreenButton();
    private Button moveLiftLevel2 = xbox.getRightButton();
    private Button moveLiftTop = xbox.getYellowButton();
    private Button moveLiftCargoAbove = xbox.getUpButton();
    private Button moveLiftCargoBelow = xbox.getDownButton();
    private Button moveLiftUp = xbox.getButtonBack();
    private Button moveLiftDown = xbox.getButtonStart();
    private static Joystick leftJoystick = new Joystick(0);
    private static Joystick rightJoystick = new Joystick(1);

    public OI() {
        gripperIn.whileHeld(new MoveGenericSubsystem(Robot.gripper, Gripper.IN_SPEED));
        gripperOut.whileHeld(new MoveGenericSubsystem(Robot.gripper, Gripper.OUT_SPEED));
        openLatch.whenPressed(new LatchOpen());
        closeLatch.whenPressed(new LatchClose());
        moveArmFront.whenPressed(new ToFront());
        moveArmBack.whenPressed(new ToBack());
        moveLiftCargoAbove.whenPressed(new RaiseLift(Lift.CARGO_SHIP_ABOVE_SETPOINT));
        moveLiftCargoBelow.whenPressed(new RaiseLift(Lift.CARGO_SHIP_BELOW_SETPOINT));
        moveLiftLevel1.whenPressed(new LowerLift());
        moveLiftLevel2.whenPressed(new RaiseLift(Lift.LEVEL_2_SETPOINT));
        moveLiftTop.whenPressed(new RaiseLift(Lift.TOP_SETPOINT));
        moveLiftUp.whileHeld(new RaiseLiftNoPID());
        moveLiftDown.whenPressed(new SetLiftState(Lift.LiftState.DOWN));
        
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
