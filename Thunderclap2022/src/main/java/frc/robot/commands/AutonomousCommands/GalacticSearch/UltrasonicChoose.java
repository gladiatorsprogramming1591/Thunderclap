package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.AutonomousCommands.GalacticSearch.PathTrigger;
import frc.robot.subsystems.DriveTrainC;

/**
 * Use the DriveTrain's ultrasonic sensor to determine which starting position the robot is in,
 * and therefore what path command to run. Then, use a custom trigger to schedule the selected command.
 */
public class UltrasonicChoose extends CommandBase{
    private DriveTrainC m_DriveTrainC;
    private final PathTrigger m_ARed, m_ABlue, m_BRed, m_BBlue;
    
    /**
     * Create a new UltrasonicChoose. Should be used as the autonomous command in RobotContainer.
     * 
     * @param driveTrainC The DriveTrainC subsystem to be used.
     * @param ARed The PathTrigger for the ARed path command.
     * @param ABlue The PathTrigger for the ABlue path command.
     * @param BRed The PathTrigger for the BRed path command.
     * @param BBlue The PathTrigger for the BBlue path command.
     */
    public UltrasonicChoose(DriveTrainC driveTrainC, PathTrigger ARed, PathTrigger ABlue, PathTrigger BRed, PathTrigger BBlue) {
        m_DriveTrainC = driveTrainC;

        m_ARed = ARed;
        m_ABlue = ABlue;
        m_BRed = BRed;
        m_BBlue = BBlue;
    }

    @Override
    public void initialize() {
        switch (m_DriveTrainC.getPathToStart()) {
            case ARed:
                m_ARed.activate();
                break;
            
            case ABlue:
                m_ABlue.activate();
                break;

            case BRed:
                m_BRed.activate();
                break;

            case BBlue:
                m_BBlue.activate();
                break;

            case NONE:
                System.out.println("UltrasonicChoose: Robot not in a valid starting position.");
                break;

            default:
                System.out.println("ERROR in initalize of UltrasonicChoose");
                break;
        }
    }
}
