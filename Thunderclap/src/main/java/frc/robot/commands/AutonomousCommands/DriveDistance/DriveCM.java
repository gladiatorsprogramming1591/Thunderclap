package frc.robot.commands.AutonomousCommands.DriveDistance;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DriveCM extends DriveEncoder{
    /**
     * Construct a DriveEncoder with centimeters translated into rotations
     * 
     * @param driveTrain The driveTrain subsystem that DriveEncoder will use
     * @param cm The distance in centimeters the robot should travel
     */
    public DriveCM(DriveTrain driveTrain, double cm) {
        super(driveTrain, cm / Constants.kWheelCircumferenceCM);
    }
}
