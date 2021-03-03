package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.commands.SetHopperModeCommands.SetIntakeMode;
import frc.robot.commands.SetHopperModeCommands.SetOffMode;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeArm;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;
import frc.robot.commands.IntakeArmCommands.LowerArm;
import frc.robot.commands.IntakeArmCommands.RaiseArm;

/**
 * Robot drives to the locations of the three balls, then to the end zone
 */
public class ABlueCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new ARedCommandGroup SequentialCommand Group.
     * 
     * Robot will activate intake mode (I forgot exactly how intake mode works so its only called once at the beginning), 
     * drive to the coordinates of the balls, drive to the endzone, and activate off mode. Squidward!
     * @param hopper The HopperSubsystem to be used.
     * @param intake The IntakeSubsystem to be used.
     * @param shooter The ShooterSubsystem to be used.
     * @param driveTrain The DriveTrain to be used.
     */
    public ABlueCommandGroup(HopperSubsystem hopper, IntakeSubsystem intake, ShooterSubsystem shooter, DriveTrainC driveTrain, IntakeArm intakeArm){
        addCommands(
            new LowerArm(intakeArm),
            new SetIntakeMode(hopper, intake, shooter),
            new DriveInches(driveTrain, 130),
            new PIDTurnToDegrees(driveTrain, -61.57, false),
            new DriveInches(driveTrain, 94.87),
            new PIDTurnToDegrees(driveTrain, 95.14, false),
            new DriveInches(driveTrain, 59.08),
            new PIDTurnToDegrees(driveTrain, -31.57, false),
            new DriveInches(driveTrain, 64),
            new SetOffMode(hopper, intake, shooter),
            new RaiseArm(intakeArm)
        );
    }
}
