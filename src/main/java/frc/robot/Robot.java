/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.spikes2212.command.drivetrains.commands.DriveArcade;
import com.spikes2212.dashboard.DashBoardController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Latch;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

  public static OI oi;
  public static Drivetrain drivetrain;

  public static Gripper gripper;
  public static Lift lift;
  public static Latch latch;
  public static Arm arm;

  public DashBoardController dbc = new DashBoardController();
  @Override
  public void robotInit() {
    gripper = SubsystemFactory.createGripper();
    lift = SubsystemFactory.createLift();
    latch = SubsystemFactory.createLatch();
    drivetrain = SubsystemFactory.createDrivetrain();
    arm=SubsystemFactory.createArm();
    oi = new OI();

    lift.initTestingDashboard();
    gripper.initTestingDashboard();
    latch.initTestingDashboard();
    arm.initTestingDashboard();

    dbc.addString("lift/state", () -> lift.getState().name());
    dbc.addNumber("lift/encoder value", lift.getEncoder()::pidGet);
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
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }
}
