/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.CombinationCommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.HopperCommands.MoveBall;
import frc.robot.commands.HopperCommands.SuckerOn;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.ShooterCommands.ShooterOn;
import frc.robot.commands.UseHopperModeCommands.ShootOneBall;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives backward.
 */
public class ShootOneBallWrapped extends SequentialCommandGroup {
  /**
   * Creates a new Command Group.
   * There are 4 types of command groups:
   * SequentialCommandGroup - first command is executed, then the second, etc. and so on until the list finishes
   * ParallelCommandGroup - ends commands when they finish
   * ParallelRaceGroup - ends as soon as any command in the group ends
   * ParallelDeadlineGroup - ends when a specific command (the “deadline”) ends
   *
   * @param subsystem1 The subsystem this command will run on
   * @param subsystem2 The subsystem this command will run on
   */
  public ShootOneBallWrapped(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, IntakeSubsystem intakeSubsystem) {
    addCommands(
        // Turns off intake and sucker
        new IntakeandSuckerOff(intakeSubsystem, hopperSubsystem),

        // Turn on shooter
        new ShooterOn(shooterSubsystem),

        // Wait for shooter to get to speed
        new WaitCommand(0.3),

        // Turn the sucker on to advance the ball to the shooter  
        new SuckerOn(hopperSubsystem)
    );
  }

}