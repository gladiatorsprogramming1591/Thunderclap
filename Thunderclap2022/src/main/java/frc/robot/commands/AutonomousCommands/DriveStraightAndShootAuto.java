/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.UseHopperModeCommands.ShootAllBalls;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class DriveStraightAndShootAuto extends SequentialCommandGroup {

  private final static double kFastDriveSpeed = 0.8;
  // private final static double kSlowDriveSpeed = 0.3;
  private final static double kStopSpeed = -0.3;
  private final static double kFastDriveTime = 1.0;
  // private final static double kSlowDriveTime = 0.3;
  private final static double kStopTime = 0.7;

  /**
   * Creates a new Command Group. There are 4 types of command groups:
   * SequentialCommandGroup - first command is executed, then the second, etc. and
   * so on until the list finishes ParallelCommandGroup - ends commands when they
   * finish ParallelRaceGroup - ends as soon as any command in the group ends
   * ParallelDeadlineGroup - ends when a specific command (the “deadline”) ends
   *
   * @param subsystem1 The subsystem this command will run on
   * @param subsystem2 The subsystem this command will run on
   */
  public DriveStraightAndShootAuto(DriveTrainC driveTrain, IntakeSubsystem intake, HopperSubsystem hopper, 
      ShooterSubsystem shooter) {
    addCommands(
        // Drive straight fast
        new DriveTimed(driveTrain, kFastDriveSpeed, 0, kFastDriveTime, this.getName()+"Fast"),

        // Drive straight slow and park at shooter spot
        new DriveTimed(driveTrain, kStopSpeed, 0, kStopTime, this.getName()+"Slow"), 
        
        // Now Shoot
        new ShootAllBalls(hopper, shooter)
    );
  }

}