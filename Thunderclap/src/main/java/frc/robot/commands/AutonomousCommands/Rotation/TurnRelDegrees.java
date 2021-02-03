package frc.robot.commands.AutonomousCommands.Rotation;

import frc.robot.subsystems.DriveTrainC;

/**
 * A wrapper for TurnToDegrees that allows instruction relative to the robot's current orientation.
 * @see TurnToDegrees
 */
public class TurnRelDegrees extends TurnToDegrees {
    /**
     * Turn the robot relative to its current orientation. This a wrapper for TurnToDegrees.
     * 
     * @param driveTrainC The DriveTrainC subsystem to use.
     * @param relDegrees How many degrees to turn. Positive is CCW. //TODO test direction
     * @see TurnToDegrees
     */
    public TurnRelDegrees(DriveTrainC driveTrainC, double relDegrees) {
        super(driveTrainC, (driveTrainC.getHeading() + relDegrees));
    }
}
