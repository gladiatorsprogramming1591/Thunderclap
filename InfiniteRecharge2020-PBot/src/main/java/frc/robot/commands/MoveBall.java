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
        System.out.println("HopperSubsystem Calling HopperOn and StopperOn and SuckerOn");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_hopperSubsystem.hopperOn();   
        //m_hopperSubsystem.stopperOn();
        m_hopperSubsystem.suckerOn();
    }

    // @Override
    // public boolean isFinished() {
    //     return true;
    // }

    public void end(boolean interrupted) {
        System.out.println("MoveBall Calling HopperStop and StopperOff and SuckerOff");
        m_hopperSubsystem.hopperOff();
        //m_hopperSubsystem.stopperOff();
        m_hopperSubsystem.suckerOff();
    }
}
