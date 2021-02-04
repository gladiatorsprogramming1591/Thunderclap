/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands.Rotation;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * Rotate to a specified heading, in degrees.
 */
public class TurnToDegrees extends CommandBase {
  private final DriveTrain m_DriveTrain;
  private final Boolean m_isHeadingAbsolute;
  private double m_targetHeading;

  /**
   * Rotate to a specified heading, in degrees, autonomously 
   * 
   * @param subsystem The DriveTrain subsystem to use.
   * @param targetHeading The heading in degrees that the robot should rotate to.
   */
  public TurnToDegrees(DriveTrain subsystem, double targetHeading, Boolean isHeadingAbsolute) {
    m_DriveTrain = subsystem;
    m_targetHeading = targetHeading;
    m_isHeadingAbsolute = isHeadingAbsolute;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (!m_isHeadingAbsolute) {
      m_targetHeading += m_DriveTrain.getHeading();
    }
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    final double m_rotationSpeed;

    if (m_targetHeading == m_DriveTrain.getHeading()) { // robot is already in position
      end(false);
    } else {
      // figure out which direction we need to turn to have the shortest possible path
      double m_clockwiseDistance = m_targetHeading - m_DriveTrain.getHeading();
      double m_counterClockwiseDistance = 360 - m_clockwiseDistance;
      if (m_clockwiseDistance < 0) {
        m_counterClockwiseDistance = -m_clockwiseDistance;
        m_clockwiseDistance += 360;
      }
  
      if (m_clockwiseDistance < m_counterClockwiseDistance) {
        // turn clockwise
        m_rotationSpeed = Constants.kAutoRotationSpeed;
      } else {
        // turn counter-clockwise
        m_rotationSpeed = -1 * Constants.kAutoRotationSpeed;
      }
  
      m_DriveTrain.drive(0, m_rotationSpeed, Constants.kFastSquaredInputs);
    }
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
    // this check will screw up if target is less than error(negative number) or if target + error > 360
    // shouldn't cause a crash though, will just not stop as accurately
    if ((m_targetHeading - Constants.kAutoRotationError) < m_DriveTrain.getHeading() && m_DriveTrain.getHeading() < (m_targetHeading + Constants.kAutoRotationError)) {
      return true;
    } else {
      return false;
    }
  }
}