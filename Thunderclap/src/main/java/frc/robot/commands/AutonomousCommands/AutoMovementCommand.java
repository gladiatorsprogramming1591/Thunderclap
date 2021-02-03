package frc.robot.commands.AutonomousCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

/**
 * An abstract class which provides functionality for all autonomous commands which move the robot (rotation or translation)
 * 
 * How to extend this class in an actual movement command:
 * 
 * public void execute() {
 *  if (reachedDestination) {
 *    stop();
 *  } else {
 *    // move to destination
 *  }
 * }
 */
public abstract class AutoMovementCommand extends CommandBase {
  private boolean m_startedStopping = false;

  private DriveTrainC m_DriveTrain;

  public AutoMovementCommand(DriveTrainC driveTrain) {
    m_DriveTrain = driveTrain;
  }

  @Override
  /**
   * DO NOT IMPLEMENT THIS METHOD IN CHILD CLASS
   */
  public boolean isFinished() {
    if (m_startedStopping && m_DriveTrain.isStopped()) {
      return true;
    } else {
      return false;
    }
  }

  public void stop() {
    m_DriveTrain.drive(0, 0, Constants.kFastSquaredInputs);
    m_startedStopping = true;
  }
}
