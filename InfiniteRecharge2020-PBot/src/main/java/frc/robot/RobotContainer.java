/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.JoystickButtonConstants;

import frc.robot.subsystems.DriveTrain;
// import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.DriveTrainP;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.IntakeOn;
import frc.robot.commands.IntakeReverse;
import frc.robot.commands.IntakeStop;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.ShooterStop;
import frc.robot.commands.ShooterOn;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmStop;
import frc.robot.commands.ArmUp;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.commands.HopperOn;
import frc.robot.commands.HopperReverse;
import frc.robot.commands.HopperStop;
import frc.robot.commands.SuckerForward;
import frc.robot.commands.SuckerReverse;
import frc.robot.commands.SuckerStop;
import frc.robot.commands.MoveBall;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // ---CONTROLLERS---
  Joystick m_manipulatorStick = new Joystick(Constants.kManipulatorControllerPort);
  Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);

  // ---SUBSYSTEMS---
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  private final DriveTrain m_driveTrain = new DriveTrainP(m_driverStick);
  
  // The robot's commands are defined here...
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
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
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenPressed(new ShooterOn(m_shooterSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenReleased(new ShooterStop(m_shooterSubsystem));

    // ---ARM---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kStart) 
      .whenPressed(new ArmUp(m_armSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack)
      .whenPressed(new ArmDown(m_armSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kStart)
      .whenReleased(new ArmStop(m_armSubsystem));
      
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack)
      .whenReleased(new ArmStop(m_armSubsystem));

    // ---HOPPER---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenPressed(new HopperOn(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenPressed(new HopperReverse(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenReleased(new HopperStop(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenReleased(new HopperStop(m_hopperSubsystem));

    // ---MOVE BALL---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR1)
      .whenPressed(new MoveBall(m_hopperSubsystem).withTimeout(Constants.kMoveOneBallTimeOut));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR2)
      .whenPressed(new MoveBall(m_hopperSubsystem).withTimeout(Constants.kMoveAllBallsTimeOut));

    // ---SUCKER---
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
