/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.LiftCommands;

import frc.robot.subsystems.LiftSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class ReleaseWinch extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final LiftSubsystem m_liftSubsystem;
  
  /**
   * Creates a new ArmCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ReleaseWinch(LiftSubsystem liftSubsystem) {
    m_liftSubsystem = liftSubsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(liftSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("ReleaseWinch command Calling ");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_liftSubsystem.releaseWinch();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true; 
  }
}
