package frc.robot.commands.AutonomousCommands.DriveDistance;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveInches extends DriveEncoder{
    /**
     * Construct a DriveEncoder with inches translated into rotations
     * 
     * @param driveTrain The driveTrain subsystem that DriveEncoder will use
     * @param inches The disance in inches the robot should travel
     */
    public DriveInches(DriveTrain driveTrain, double inches) {
        super(driveTrain, inches / Constants.kWheelCircumferenceInches);
    }
}
