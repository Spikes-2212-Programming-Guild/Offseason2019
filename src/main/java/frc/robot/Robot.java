/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.command.drivetrains.commands.DriveArcade;
import com.spikes2212.command.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.command.genericsubsystem.GenericSubsystem;
import com.spikes2212.command.genericsubsystem.commands.MoveGenericSubsystem;
import com.spikes2212.dashboard.DashBoardController;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.latch.LatchClose;
import frc.robot.commands.latch.LatchOpen;
import frc.robot.commands.lift.RaiseLift;
import frc.robot.commands.lift.SetLiftState;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Latch;
import frc.robot.subsystems.Lift;

public class Robot extends TimedRobot {

  public static OI oi;
  public static Drivetrain drivetrain;

  public static Gripper gripper;
  public static Lift lift;
  public static Latch latch;
  public static Arm arm;

  public static DashBoardController dbc;

  @Override
  public void robotInit() {
    gripper = SubsystemFactory.createGripper();
    lift = SubsystemFactory.createLift();
    latch = SubsystemFactory.createLatch();
    drivetrain = SubsystemFactory.createDrivetrain();
    arm = SubsystemFactory.createArm();
    oi = new OI();

    setDefaultCommand();

    dbc = new DashBoardController();

    testGripper();
    testLatch();
    testLift();
    SmartDashboard.putData("drivetrain/drive tank with PID", new DriveTankWithPID(drivetrain, drivetrain.getLeftEncoder()
    , drivetrain.getRightEncoder(), Drivetrain.TEST_SETPOINT, Drivetrain.TEST_SETPOINT, Drivetrain.FORWARD_PID_SETTINGS));
  }

  public void testGripper() {
    SmartDashboard.putData("gripper/in", new MoveGenericSubsystem(gripper, Gripper.IN_SPEED.get()));
    SmartDashboard.putData("gripper/out", new MoveGenericSubsystem(gripper, Gripper.OUT_SPEED.get()));
  }

  public void testLatch() {
    SmartDashboard.putData("latch/open", new LatchOpen());
    SmartDashboard.putData("latch/close", new LatchClose());
  }

  public void testLift() {
    SmartDashboard.putData("lift/raise with constant speed", new MoveGenericSubsystem(lift, Lift.TEST_SPEED));
    SmartDashboard.putData("lift/raise with PID", new RaiseLift(Lift.TEST_SETPOINT));
    SmartDashboard.putData("lift/setState Up", new SetLiftState(Lift.LiftState.UP));
    SmartDashboard.putData("lift/setState Down", new SetLiftState(Lift.LiftState.DOWN));
    dbc.addNumber("lift/encoder value", lift.getEncoder()::pidGet);
    dbc.addString("lift/state", () -> lift.getState().name());
  }

  public void setDefaultCommand() {
    drivetrain.setDefaultCommand(new DriveArcade(drivetrain, OI::getRightY, OI::getLeftX));
  }

  @Override
  public void robotPeriodic() {
    dbc.update();
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    lift.getEncoder().reset();
    drivetrain.getRightEncoder().reset();
    drivetrain.getLeftEncoder().reset();
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
