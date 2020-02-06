package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeStop extends CommandBase {

	IntakeSubsystem m_intakeSubsystem;
	 
	public IntakeStop(IntakeSubsystem intakeSubsystem) {
		m_intakeSubsystem = intakeSubsystem;	
		// This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
		addRequirements(m_intakeSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		System.out.println("IntakeSubsystem Calling IntakeStop");
		m_intakeSubsystem.intakeStop();;    
	}

	// Implement this if you want to act like an InstantCommand,
	// which means this command executes its complete lifecycle once and exits
	// (the execute() method will only execute once)
	// @Override
    // public boolean isFinished() {
    //     return true;
	// }
}
