package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

public class IntakeOn extends CommandBase {

   IntakeSubsystem m_intakeSubsystem;

   public IntakeOn(IntakeSubsystem intakeSubsystem) {
      m_intakeSubsystem = intakeSubsystem;
      addRequirements(m_intakeSubsystem);
   }
    
   // Called when the command is initially scheduled.
   @Override
   public void initialize() {
      System.out.println("IntakeSubsystem Calling IntakeReverse");
   }

   // Called every time the scheduler runs while the command is scheduled.
   @Override
   public void execute() {
      m_intakeSubsystem.intakeReverse();
   }
     
    //  @Override
    //  public boolean isFinished(){
    //      return true;
    //  }
     
    //  public void end(boolean interrupted) {
    //      if ( interrupted == false) {
    //          System.out.println("IntakeReverse Calling IntakeStop");
    //          m_intakeSubsystem.intakeStop();   
    //      }
    //  }
}

