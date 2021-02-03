package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;

public class UltrasonicChoose extends CommandBase{
    private DriveTrainC m_DriveTrainC;
    private final double m_range;
    private final PathTrigger m_ARed, m_ABlue, m_BRed, m_BBlue;
    
    public UltrasonicChoose(DriveTrainC driveTrainC, PathTrigger ARed, PathTrigger ABlue, PathTrigger BRed, PathTrigger BBlue) {
        m_DriveTrainC = driveTrainC;
        m_range = m_DriveTrainC.getRangeInches();

        m_ARed = ARed;
        m_ABlue = ABlue;
        m_BRed = BRed;
        m_BBlue = BBlue;
    }

    @Override
    public void initialize() {
        
    }
}
