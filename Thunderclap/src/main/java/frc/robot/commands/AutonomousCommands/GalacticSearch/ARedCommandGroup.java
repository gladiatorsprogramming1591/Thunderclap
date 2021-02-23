package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.SetHopperModeCommands.SetIntakeMode;
import frc.robot.commands.SetHopperModeCommands.SetOffMode;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;


/**
 * Robot drives to the locations of the three balls, then to the end zone
 */
public class ARedCommandGroup extends SequentialCommandGroup{

    /**
     * Create a new ARedCommandGroup SequentialCommand Group.
     * 
     * Robot will activate intake mode (I forgot exactly how intake mode works so its only called once at the beginning), 
     * drive to the coordinates of the balls, drive to the endzone, and activate off mode.
     * @param hopper The HopperSubsystem to be used.
     * @param intake The IntakeSubsystem to be used.
     * @param shooter The ShooterSubsystem to be used.
     * @param driveTrain The DriveTrain to be used.
     * @param relDegrees 
     */
    public ARedCommandGroup(HopperSubsystem hopper, IntakeSubsystem intake, ShooterSubsystem shooter, DriveTrainC driveTrain) {
        addCommands(
            new SetIntakeMode(hopper, intake, shooter),
            new DriveInches(driveTrain, 36),
            new PIDTurnToDegrees(driveTrain, 31.57, false),
            new DriveInches(driveTrain, 68),
            new PIDTurnToDegrees(driveTrain, -78, false),
            new DriveInches(driveTrain, 76),
            new PIDTurnToDegrees(driveTrain, 70, false),
            new DriveInches(driveTrain, 160),
            new SetOffMode(hopper, intake, shooter)
        );
    }
}
