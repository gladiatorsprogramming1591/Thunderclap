package frc.robot.commands.AutonomousCommands.AutoNav;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.IntakeArm;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;
import frc.robot.commands.DriveTrainCommands.CalibrateNavX;
import frc.robot.commands.IntakeArmCommands.LowerArm;
import frc.robot.commands.IntakeArmCommands.RaiseArm;

/**
 * Robot drives the Bounce Path from the AutoNav challenge
 */
public class BounceCommandGroup extends SequentialCommandGroup{
    private final double hitConesLess = 20;

    /**
     * Create a new BounceCommandGroup SequentialCommand Group.
     * 
     * @param driveTrain The DriveTrain to be used.
     */
    public BounceCommandGroup(DriveTrainC driveTrain, IntakeArm intakeArm) {
        addCommands(
            new CalibrateNavX(driveTrain),

            new LowerArm(intakeArm),

            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 90, true),

            new DriveInches(driveTrain, 60 - hitConesLess),
            
            new DriveInches(driveTrain, -64 + hitConesLess),
            new PIDTurnToDegrees(driveTrain, 180, true),
            
            new DriveInches(driveTrain, 32),
            new PIDTurnToDegrees(driveTrain, 270, true),
            
            new DriveInches(driveTrain, 60),
            new PIDTurnToDegrees(driveTrain, 180, true),
            
            new DriveInches(driveTrain, 58),
            new PIDTurnToDegrees(driveTrain, 90, true),
            
            new DriveInches(driveTrain, 120 - hitConesLess),
            
            new DriveInches(driveTrain, -124 + hitConesLess),
            new PIDTurnToDegrees(driveTrain, 180, true),
            
            new DriveInches(driveTrain, 90),
            new PIDTurnToDegrees(driveTrain, 90, true),
            
            new DriveInches(driveTrain, 120 - hitConesLess),
            
            new DriveInches(driveTrain, -64 + hitConesLess),
            new PIDTurnToDegrees(driveTrain, 180, true),
            
            new DriveInches(driveTrain, 60),

            new RaiseArm(intakeArm)
        );
    }
}
