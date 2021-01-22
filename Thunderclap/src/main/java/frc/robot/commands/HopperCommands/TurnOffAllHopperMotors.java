package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class TurnOffAllHopperMotors extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

    public TurnOffAllHopperMotors(HopperSubsystem hopperSubsystem) {
        m_hopperSubsystem = hopperSubsystem;
        // This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
        addRequirements(m_hopperSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("HopperSubsystem Calling StopperOff, HopperStop, SuckerOff");
        m_hopperSubsystem.stopperOff();    
        m_hopperSubsystem.hopperOff();    
        m_hopperSubsystem.suckerOff();    
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
