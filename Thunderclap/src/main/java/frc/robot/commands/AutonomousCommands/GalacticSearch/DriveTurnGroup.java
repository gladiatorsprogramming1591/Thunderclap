package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;
import frc.robot.subsystems.DriveTrainC;

public class DriveTurnGroup extends SequentialCommandGroup{
    public DriveTurnGroup(DriveTrainC driveTrainC) {
        addCommands(
            new DriveInches(driveTrainC, 45),
            new PIDTurnToDegrees(driveTrainC, 90, false),
            new DriveInches(driveTrainC, 45),
            new PIDTurnToDegrees(driveTrainC, 90, false),
            new DriveInches(driveTrainC, 45),
            new PIDTurnToDegrees(driveTrainC, 90, false),
            new DriveInches(driveTrainC, 45),
            new PIDTurnToDegrees(driveTrainC, 90, false)
        );
    }
}
