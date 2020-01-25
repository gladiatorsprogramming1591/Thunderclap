package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeReverse extends CommandBase {
    IntakeSubsystem m_intakeSubsystem;
	public IntakeReverse(IntakeSubsystem intakeSubsystem) {
        m_intakeSubsystem = intakeSubsystem;	
    }
    
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_intakeSubsystem.intakeReverse();  
    }
}

