package frc.robot.commands.HopperCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HopperSubsystem;

public class HopperOn extends CommandBase {

    HopperSubsystem m_hopperSubsystem;

    public HopperOn(HopperSubsystem hopperSubsystem) {
        m_hopperSubsystem = hopperSubsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(m_hopperSubsystem);
	}

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        System.out.println("HopperSubsystem Calling HopperOn");  
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_hopperSubsystem.hopperOn();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
  
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
