package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class ReverseAllHopperMotors extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

    public ReverseAllHopperMotors(HopperSubsystem hopperSubsystem) {
        m_hopperSubsystem = hopperSubsystem;
        // This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
        addRequirements(m_hopperSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("hopperSubsystem Calling stopperReverse, hopperReverse, suckerReverse");
        m_hopperSubsystem.setOffMode();  // Make sure our modes don't try to count balls
        m_hopperSubsystem.stopperReverse();    
        m_hopperSubsystem.hopperReverse();    
        m_hopperSubsystem.suckerReverse();    
    }

    // Called once the command ends or is interrupted.
    @Override
        public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
