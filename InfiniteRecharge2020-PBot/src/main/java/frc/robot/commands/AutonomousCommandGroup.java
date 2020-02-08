/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ExampleSubsystem;

/**
 * A complex auto command that drives forward, releases a hatch, and then drives backward.
 */
public class AutonomousCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new Command Group.
   *
   * @param subsystem1 The subsystem this command will run on
   * @param subsystem2 The subsystem this command will run on
   */
  public AutonomousCommandGroup(ExampleSubsystem subsystem1, ExampleSubsystem subsystem2) {
    addCommands(
        // Example 1
        new ExampleCommand(subsystem1),

        // Example 2
        new ExampleCommand(subsystem2)
        
    );
  }

}