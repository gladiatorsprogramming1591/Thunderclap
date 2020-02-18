package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class HopperReverse extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

    public HopperReverse(HopperSubsystem hopperSubsystem) {
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
        m_hopperSubsystem.stopperReverse();    
        m_hopperSubsystem.hopperReverse();    
        m_hopperSubsystem.suckerReverse();    
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
