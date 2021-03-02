package frc.robot.commands.AutonomousCommands.AutoNav;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;

/**
 * Robot drives the Slalom Path from the AutoNav challenge
 */
public class SlalomCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new SlalomCommandGroup SequentialCommand Group.
     * 
     * @param driveTrain The DriveTrain to be used.
     */
    public SlalomCommandGroup(DriveTrainC driveTrain) {
        addCommands(
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),

            new DriveInches(driveTrain, 30),
            new PIDTurnToDegrees(driveTrain, 90, false),

            new DriveInches(driveTrain, 180),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 30),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 30),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 30),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 180),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 30),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 60)
        );
    }
}
