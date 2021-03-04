package frc.robot.commands.AutonomousCommands.AutoNav;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;
import frc.robot.commands.DriveTrainCommands.CalibrateNavX;

/**
 * Robot drives the Bounce Path from the AutoNav challenge
 */
public class BounceCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new BounceCommandGroup SequentialCommand Group.
     * 
     * @param driveTrain The DriveTrain to be used.
     */
    public BounceCommandGroup(DriveTrainC driveTrain) {
        addCommands(
            new CalibrateNavX(driveTrain),

            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),

            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 180, false),

            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 30),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 120),
            new PIDTurnToDegrees(driveTrain, 180, false),
            
            new DriveInches(driveTrain, 120),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 90),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 120),
            new PIDTurnToDegrees(driveTrain, 180, false),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 60)
        );
    }
}
