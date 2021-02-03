package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;

public class UltrasonicChoose extends CommandBase{
    private DriveTrainC m_DriveTrainC;
    
    public UltrasonicChoose(DriveTrainC driveTrainC) {
        m_DriveTrainC = driveTrainC;
    }

    @Override
    public void initialize() {
        
    }
}
