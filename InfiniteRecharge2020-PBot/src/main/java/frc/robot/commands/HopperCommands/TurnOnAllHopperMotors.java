package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class TurnOnAllHopperMotors extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

    public TurnOnAllHopperMotors(HopperSubsystem hopperSubsystem) {
        m_hopperSubsystem = hopperSubsystem;
        // This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
        addRequirements(m_hopperSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("hopperSubsystem Calling stopperOn, hopperOn, suckerOn");
        m_hopperSubsystem.stopperOn();    
        m_hopperSubsystem.hopperOn();    
        m_hopperSubsystem.suckerOn();    
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
