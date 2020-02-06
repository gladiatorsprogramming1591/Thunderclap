package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;



 public class IntakeReverse extends CommandBase {

     IntakeSubsystem m_intakeSubsystem;

 	public IntakeReverse(IntakeSubsystem intakeSubsystem) {
         m_intakeSubsystem = intakeSubsystem;
         addRequirements(m_intakeSubsystem);
        }
    
     // Called when the command is initially scheduled.
     @Override
     public void initialize() {
        System.out.println("IntakeSubsystem Calling IntakeReverse");
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


// public class IntakeReverse extends CommandBase {
//     IntakeSubsystem m_intakeSubsystem;

// 	public IntakeReverse(IntakeSubsystem intakeSubsystem, boolean intakeReverseBool) {
//         m_intakeSubsystem = intakeSubsystem;	
//         if (intakeReverseBool == true) {
//             initialize();
//         }

//         if (intakeReverseBool == false) {
//             end(intakeReverseBool);
//         }
//     }
    
//     // Called when the command is initially scheduled.
//     @Override
//     public void initialize() {
//         m_intakeSubsystem.intakeReverse();  
//     }
//     public void end(boolean interrupted) {
//         if ( interrupted == false) {
//             m_intakeSubsystem.intakeStop();   
//         }
//     }
// }
