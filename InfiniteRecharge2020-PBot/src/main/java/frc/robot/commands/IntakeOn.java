package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOn extends CommandBase {

    IntakeSubsystem m_intakeSubsystem;

	public IntakeOn(IntakeSubsystem intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;
        // This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
        addRequirements(m_intakeSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("IntakeSubsystem Calling IntakeOn");
        m_intakeSubsystem.intakeOn();    
    }

    // @Override
    // public boolean isFinished() {
    //     return true;
    // }

    // public void end(boolean interrupted) {
    //     if ( interrupted == false) {
    //         System.out.println("IntakeOn Calling IntakeStop");
    //         m_intakeSubsystem.intakeStop();   
    //     }
    //     else {
    //         System.out.println("IntakeOn interrupted");
    //     }
    // }
}
