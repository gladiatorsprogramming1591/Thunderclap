package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class MoveBall extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

	public MoveBall(HopperSubsystem hopperSubsystem) {
        m_hopperSubsystem = hopperSubsystem;
        // This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
        addRequirements(m_hopperSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("MoveBall Calling hopperOn and stopperOn");
        m_hopperSubsystem.hopperOn();   
        m_hopperSubsystem.stopperOn();        
    }

    // @Override
    // public boolean isFinished() {
    //     return true;
    // }

     public void end(boolean interrupted) {
        System.out.println("MoveBall Calling hopperStop and stopperOff");
        m_hopperSubsystem.hopperStop();
        m_hopperSubsystem.stopperOff();
     }
}
