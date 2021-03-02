package frc.robot.commands.AutonomousCommands.AutoNav;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;

/**
 * Robot drives the Barrel Racing Path from the AutoNav challenge
 */
public class BarrelRacingCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new BarrelRacingCommandGroup SequentialCommand Group.
     * 
     * @param driveTrain The DriveTrain to be used.
     */
    public BarrelRacingCommandGroup(DriveTrainC driveTrain) {
        addCommands(
            new DriveInches(driveTrain, 180),
            new PIDTurnToDegrees(driveTrain, 90, false),

            new DriveInches(driveTrain, 80),
            new PIDTurnToDegrees(driveTrain, 90, false),

            new DriveInches(driveTrain, 80),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 70),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 150),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 80),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 80),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 85),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 150),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 80),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 80),
            new PIDTurnToDegrees(driveTrain, 90, false),
            
            new DriveInches(driveTrain, 90),
            new PIDTurnToDegrees(driveTrain, -90, false),
            
            new DriveInches(driveTrain, 210)
        );
    }
}
