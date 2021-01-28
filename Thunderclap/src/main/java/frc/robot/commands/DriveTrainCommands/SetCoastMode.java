package frc.robot.commands.DriveTrainCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class SetCoastMode extends CommandBase{
    private final DriveTrain m_DriveTrain;
    
    public SetCoastMode(DriveTrain drivetrain) {
        m_DriveTrain = drivetrain;
    }

    @Override
    public void initialize() {
        m_DriveTrain.setCoastMode();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
