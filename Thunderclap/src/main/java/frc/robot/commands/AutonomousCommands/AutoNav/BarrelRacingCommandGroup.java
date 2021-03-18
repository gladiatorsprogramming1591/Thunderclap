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
 * Robot drives the Barrel Racing Path from the AutoNav challenge
 */
public class BarrelRacingCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new BarrelRacingCommandGroup SequentialCommand Group.
     * 
     * @param driveTrain The DriveTrain to be used.
     */
    public BarrelRacingCommandGroup(DriveTrainC driveTrain, IntakeArm intakeArm) {
        addCommands(
            new CalibrateNavX(driveTrain),

            new RaiseArm(intakeArm),

            new DriveInches(driveTrain, 154),
            new PIDTurnToDegrees(driveTrain, 270, true),

            new DriveInches(driveTrain, 72),
            new PIDTurnToDegrees(driveTrain, 360, true),

            new DriveInches(driveTrain, 70),
            new PIDTurnToDegrees(driveTrain, 450, true),
            
            new DriveInches(driveTrain, 54),
            new TurnToDegrees(driveTrain, 520, true),
            
            new DriveInches(driveTrain, 145),
            new PIDTurnToDegrees(driveTrain, 450, true),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 360, true),
            
            new DriveInches(driveTrain, 75),
            new PIDTurnToDegrees(driveTrain, 270, true),
            
            new DriveInches(driveTrain, 112),
            new PIDTurnToDegrees(driveTrain, 180, true),
            
            new DriveInches(driveTrain, 115),
            new PIDTurnToDegrees(driveTrain, 90, true),
            
            new DriveInches(driveTrain, 60),
            new TurnToDegrees(driveTrain, 18, true),
            
            new PIDDriveInches(driveTrain, 265)
        );
    }
}
