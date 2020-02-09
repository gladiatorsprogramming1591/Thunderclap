package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;

public class HopperStop extends CommandBase {

	HopperSubsystem m_hopperSubsystem;
	 
	public HopperStop(HopperSubsystem hopperSubsystem) {
		m_hopperSubsystem = hopperSubsystem;	
		// This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
		addRequirements(m_hopperSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		System.out.println("HopperSubsystem Calling HopperStop");
		m_hopperSubsystem.stopperOff();;    
		// m_hopperSubsystem.hopperStop();;    
	}

	// Implement this if you want to act like an InstantCommand,
	// which means this command executes its complete lifecycle once and exits
	// (the execute() method will only execute once)
	// @Override
    // public boolean isFinished() {
    //     return true;
	// }
}
