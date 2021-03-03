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
 * Line up hopper with first ball, not robot center
 */
public class BBlueCommandGroup extends SequentialCommandGroup{

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
    public BBlueCommandGroup(HopperSubsystem hopper, IntakeSubsystem intake, ShooterSubsystem shooter, DriveTrainC driveTrain, IntakeArm intakeArm) {
        addCommands(
            new LowerArm(intakeArm),
            new SetIntakeMode(hopper, intake, shooter),
            new DriveInches(driveTrain, 126),
            new PIDTurnToDegrees(driveTrain, -36, false),
            new DriveInches(driveTrain, 84.852),
            new PIDTurnToDegrees(driveTrain, 73, false),
            new DriveInches(driveTrain, 120.852),
            new SetOffMode(hopper, intake, shooter),
            new RaiseArm(intakeArm)
        );
    }
}
