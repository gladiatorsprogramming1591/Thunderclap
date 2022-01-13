package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.commands.SetHopperModeCommands.SetIntakeMode;
import frc.robot.commands.SetHopperModeCommands.SetOffMode;
import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

/**
 * Robot activates intake mode, then drives the length fo the field, then activates off mode.
 */
public class OnePowerCell extends SequentialCommandGroup{
    // How far the robot should drive. Not the full field length (which is 360) because robot starts and ends inside the field.
    private final int m_length = 120; //TODO need to test what this length should be for the actual field (300?)

    /**
     * Create a new OnePowerCell SequentialCommand Group.
     * 
     * Robot will activate intake mode, drive the length of the field, activate off mode.
     * 
     * @param hopper The HopperSubsystem to be used.
     * @param intake The IntakeSubsystem to be used.
     * @param shooter The ShooterSubsystem to be used.
     * @param driveTrain The DriveTrain to be used.
     */
    public OnePowerCell(HopperSubsystem hopper, IntakeSubsystem intake, ShooterSubsystem shooter, DriveTrainC driveTrain) {
        addCommands(
            new SetIntakeMode(hopper, intake, shooter),
            new DriveInches(driveTrain, m_length),
            new SetOffMode(hopper, intake, shooter)
        );
    }
}
