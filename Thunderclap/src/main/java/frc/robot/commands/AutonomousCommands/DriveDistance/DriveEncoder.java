/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands.DriveDistance;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Drive a certain distance based on an encoder value, autonomously.
 */
public class DriveEncoder extends CommandBase {
  private final DriveTrain m_DriveTrain;
  private final double m_motorRotations;
  private final double m_startEncoderValue;

  /**
   * Creates a new DriveEncoder command.
   *
   * @param subsystem The subsystem used by this command.
   * @param wheelRotations The distance in rotations the robot should travel.
   */
  public DriveEncoder(DriveTrain subsystem, double wheelRotations) {
    m_DriveTrain = subsystem;
    m_motorRotations = wheelRotations * Constants.kDriveGearRatio;
    m_startEncoderValue = m_DriveTrain.getRightEncPos(); // using right arbitrarily at the momement, TODO check both or avg if more accurate

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    final double m_driveSpeed;

    if (m_motorRotations == 0) {
      end(false);
    } else {
      if (m_motorRotations > 0) { // needs to move forward
        m_driveSpeed = Constants.kAutoDriveSpeed;
      } else { // needs to move backwards
        m_driveSpeed = -1 * Constants.kAutoDriveSpeed;
      }

      m_DriveTrain.drive(m_driveSpeed, 0, Constants.kFastSquaredInputs);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveTrain.setBrakeMode(); 
    m_DriveTrain.drive(0, 0, Constants.kFastSquaredInputs); // stop
  }
  
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(m_motorRotations) > Math.abs(m_DriveTrain.getRightEncPos() - m_startEncoderValue)) { // when the robot has travelled the necessary distance
      return true;
    } else {
      return false;
    }
  }
}
