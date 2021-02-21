/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands.Rotation;

import frc.robot.Constants;
import frc.robot.commands.AutonomousCommands.AutoMovementCommand;
import frc.robot.subsystems.DriveTrainC;

/**
 * Rotate to a specified heading, in degrees.
 */
public class TurnToDegrees extends AutoMovementCommand {
  private final DriveTrainC m_DriveTrain;
  private double m_StopTarget;
  private double m_ActualTarget;
  private final Boolean m_isHeadingAbsolute;
  private boolean m_readyToStop = false;

  /**
   * Rotate to a specified heading, in degrees, autonomously 
   * 
   * @param subsystem The DriveTrain subsystem to use.
   * @param targetHeading The heading in degrees that the robot should rotate to. CW positive
   * @param isHeadingAbsolute true = absolute, false = relative
   */
  public TurnToDegrees(DriveTrainC subsystem, double targetHeading, Boolean isHeadingAbsolute) {
    super(subsystem);
    m_DriveTrain = subsystem;
    m_isHeadingAbsolute = isHeadingAbsolute;
    m_ActualTarget = targetHeading;

    // decrease distance needed to travel by stop distance
    if (targetHeading > 0) {
      m_StopTarget = targetHeading + Constants.kRotStopDistance;
    } else {
      m_StopTarget = targetHeading - Constants.kRotStopDistance;
    }

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("------------- TurnToDegrees -----------");
    m_readyToStop = false;
    if (!m_isHeadingAbsolute) {
      m_StopTarget += m_DriveTrain.getHeading();
      m_ActualTarget += m_DriveTrain.getHeading();
    }
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (readyToStop()) {
      return;
    }

    final double m_rotationSpeed;

    if (m_StopTarget == m_DriveTrain.getHeading()) { // robot is already in position
      System.out.println("robot already in position");
      end(false);
    } else {
      // figure out which direction we need to turn to have the shortest possible path
      double m_clockwiseDistance = m_StopTarget - m_DriveTrain.getHeading();
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
      
      System.out.println("moving with rotation speed " + m_rotationSpeed);
      m_DriveTrain.drive(0, m_rotationSpeed, Constants.kFastSquaredInputs);
    }
  }
  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveTrain.setCoastMode();
    System.out.println("isStopped:" + m_DriveTrain.isStopped());
  }
  
  @Override
  public boolean readyToStop() {
    if (m_readyToStop) {
      return true;
    }
    // this check will screw up if target is less than error(negative number) or if target + error > 360
    // shouldn't cause a crash though, will just not stop as accurately
    if (((m_ActualTarget - Constants.kAutoRotationError) < m_DriveTrain.getHeading()) && (m_DriveTrain.getHeading() < (m_ActualTarget + Constants.kAutoRotationError))) {
      System.out.println("stopping");
      m_readyToStop = true;
      return true;
    } else {
      System.out.println("not ready to stop");
      return false;
    }
  }
}