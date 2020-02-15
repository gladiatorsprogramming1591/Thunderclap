/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmStop;
import frc.robot.commands.ArmUp;
import frc.robot.commands.BallLoadingCommandGroup;
import frc.robot.commands.BallLoadingCommandGroupStop;
import frc.robot.commands.IntakeOn;
import frc.robot.commands.IntakeReverse;
import frc.robot.commands.IntakeStop;
import frc.robot.commands.MoveBall;
import frc.robot.commands.SuckerForward;
import frc.robot.commands.SuckerReverse;
import frc.robot.commands.SuckerStop;
import frc.robot.subsystems.IntakeSubsystem;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.ShooterStop;
import frc.robot.commands.StopperForward;
import frc.robot.commands.StopperReverse;
import frc.robot.commands.StopperStop;
import frc.robot.commands.ShooterOn;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.HopperOn;
import frc.robot.commands.HopperReverse;
import frc.robot.commands.HopperStopAll;
import frc.robot.subsystems.HopperSubsystem;

import frc.robot.JoystickButtonConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here...
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  
  // The robot's commands are defined here...
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  

  // The manipulator's controller
  Joystick m_manipulatorStick = new Joystick(Constants.kManipulatorControllerPort);
  Joystick m_driveStick = new Joystick(Constants.kDriverControllerPort);


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    SmartDashboard.putData("intakeMotor", m_intakeSubsystem);
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    // ---INTAKE SECTION---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kA) 
      .whenPressed(new IntakeOn(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kB)
      .whenPressed(new IntakeReverse(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kA) 
      .whenReleased(new IntakeStop(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kB)
      .whenReleased(new IntakeStop(m_intakeSubsystem));

    // ---SHOOTER SECTION---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack) 
      .whenPressed(new ShooterOn(m_shooterSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack) 
      .whenReleased(new ShooterStop(m_shooterSubsystem));

    // ---ARM---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL3) 
      .whenPressed(new ArmUp(m_armSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack)
      .whenPressed(new ArmDown(m_armSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL3)
      .whenReleased(new ArmStop(m_armSubsystem));
      
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack)
      .whenReleased(new ArmStop(m_armSubsystem));

    // ---HOPPER---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenPressed(new HopperOn(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenPressed(new HopperReverse(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenReleased(new HopperStopAll(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenReleased(new HopperStopAll(m_hopperSubsystem));

    // ---MOVE BALL---
    // new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR1)
    //   .whenPressed(new MoveBall(m_hopperSubsystem).withTimeout(Constants.kMoveOneBallTimeOut));

    // new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR2)
    //   .whenPressed(new MoveBall(m_hopperSubsystem).withTimeout(Constants.kMoveAllBallsTimeOut));

    // ---SUCKER(SUCKS BALLS INTO HOPPER)---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL1) 
      .whenPressed(new SuckerForward(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL2)
      .whenPressed(new SuckerReverse(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL1) 
      .whenReleased(new SuckerStop(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL2)
      .whenReleased(new SuckerStop(m_hopperSubsystem));



      
  }
  


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
}
