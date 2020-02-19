/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project. Squia=dward                                                   */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.AutonomousCommands.DriveTimed;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives
 * backward.
 */
public class DriveLeftAutonomous extends SequentialCommandGroup {

  private final static double kFastDriveSpeed = 0.5;
  private final static double kSlowDriveSpeed = 0.25;
  private final static double kRotationSpeed = 0.4;
  private final static double kFastDriveTime = 1.5;
  private final static double kSlowDriveTime = 0.5;
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
  public DriveLeftAutonomous(DriveTrain driveTrain) {
    addCommands(
        //Turn right while driving
        new DriveTimed(driveTrain, kFastDriveSpeed, kRotationSpeed, kFastDriveTime),
        //Turn left while driving
        new DriveTimed(driveTrain, kFastDriveSpeed, -kRotationSpeed, kFastDriveTime),
        //Park at the shooter spot
        new DriveTimed(driveTrain, kSlowDriveSpeed, 0, kSlowDriveTime)
        
    );
  }

}