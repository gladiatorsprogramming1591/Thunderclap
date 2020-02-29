package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class MoveBall extends CommandBase {

    HopperSubsystem m_hopperSubsystem;
    boolean m_suckerOn;
    boolean m_stopperOn;

	public MoveBall(HopperSubsystem hopperSubsystem, boolean suckerOn, boolean stopperOn) {
        m_hopperSubsystem = hopperSubsystem;
        m_suckerOn = suckerOn;
        m_stopperOn = stopperOn;
        // Use addRequirements() here to declare subsystem dependencies.
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
        if(m_stopperOn == true) {
            m_hopperSubsystem.stopperOn();
        }
        if(m_suckerOn == true) {
            m_hopperSubsystem.suckerOn();
        }
    }

    // Called once the command ends or is interrupted.
    public void end(boolean interrupted) {
        System.out.println("MoveBall Calling HopperStop and StopperOff and SuckerOff");
        m_hopperSubsystem.hopperOff();
        if(!m_stopperOn == true) {
            m_hopperSubsystem.stopperOff();
        }
        if(!m_suckerOn == true) {
            m_hopperSubsystem.suckerOff(); 
        }
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }

}
