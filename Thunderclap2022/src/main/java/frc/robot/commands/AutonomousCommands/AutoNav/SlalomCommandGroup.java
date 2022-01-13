package frc.robot.commands.AutonomousCommands.AutoNav;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.commands.AutonomousCommands.PIDDriveDistance.PIDDriveInches;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.IntakeArm;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;
import frc.robot.commands.AutonomousCommands.Rotation.TurnToDegrees;
import frc.robot.commands.DriveTrainCommands.CalibrateNavX;
import frc.robot.commands.IntakeArmCommands.LowerArm;
import frc.robot.commands.IntakeArmCommands.RaiseArm;

/**
 * Robot drives the Slalom Path from the AutoNav challenge
 */
public class SlalomCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new SlalomCommandGroup SequentialCommand Group.
     * 
     * @param driveTrain The DriveTrain to be used.
     */
    public SlalomCommandGroup(DriveTrainC driveTrain, IntakeArm intakeArm) {
        addCommands(
            new CalibrateNavX(driveTrain),

            new RaiseArm(intakeArm),

            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 90, true),

            new DriveInches(driveTrain, 56),
            new PIDTurnToDegrees(driveTrain, 180, true),

            new PIDDriveInches(driveTrain, 167),
            new PIDTurnToDegrees(driveTrain, 270, true),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 180, true),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 90, true),
            
            new DriveInches(driveTrain, 60),
            new TurnToDegrees(driveTrain, 12, true),
            
            new DriveInches(driveTrain, 54),
            new PIDTurnToDegrees(driveTrain, -90, true),
            
            new DriveInches(driveTrain, 57),
            new PIDTurnToDegrees(driveTrain, 1, true),
            
            new PIDDriveInches(driveTrain, 185),
            new PIDTurnToDegrees(driveTrain, 90, true),
            
            new DriveInches(driveTrain, 64),
            new PIDTurnToDegrees(driveTrain, 0, true),
            
            new DriveInches(driveTrain, 60),

            new RaiseArm(intakeArm)
        );
    }
}
