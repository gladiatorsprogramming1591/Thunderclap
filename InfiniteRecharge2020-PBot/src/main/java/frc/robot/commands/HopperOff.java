package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class HopperOff extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

    public HopperOff(HopperSubsystem hopperSubsystem) {
        m_hopperSubsystem = hopperSubsystem;
        // This makes the command interruptible by other 
		// commands that add this same subsystem as a requirement 
		// which eliminates the need to implement the isFinished() method
        addRequirements(m_hopperSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("HopperSubsystem Calling HopperOff");
        m_hopperSubsystem.hopperOff();    
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
