package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;



 public class HopperReverse extends CommandBase {

     HopperSubsystem m_hopperSubsystem;

 	public HopperReverse(HopperSubsystem hopperSubsystem) {
         m_hopperSubsystem = hopperSubsystem;
         addRequirements(m_hopperSubsystem);
        }
    
     // Called when the command is initially scheduled.
     @Override
     public void initialize() {
        System.out.println("HopperSubsystem Calling HopperReverse");
         m_hopperSubsystem.hopperReverse();  
    }
     
    //  @Override
    //  public boolean isFinished(){
    //      return true;
    //  }
     
    //  public void end(boolean interrupted) {
    //      if ( interrupted == false) {
    //          System.out.println("HopperReverse Calling HopperStop");
    //          m_hopperSubsystem.hopperStop();   
    //      }
    //  }
 }


// public class HopperReverse extends CommandBase {
//     HopperSubsystem m_hopperSubsystem;

// 	public HopperReverse(HopperSubsystem hopperSubsystem, boolean hopperReverseBool) {
//         m_hopperSubsystem = hopperSubsystem;	
//         if (hopperReverseBool == true) {
//             initialize();
//         }

//         if (hopperReverseBool == false) {
//             end(hopperReverseBool);
//         }
//     }
    
//     // Called when the command is initially scheduled.
//     @Override
//     public void initialize() {
//         m_hopperSubsystem.hopperReverse();  
//     }
//     public void end(boolean interrupted) {
//         if ( interrupted == false) {
//             m_hopperSubsystem.hopperStop();   
//         }
//     }
// }
