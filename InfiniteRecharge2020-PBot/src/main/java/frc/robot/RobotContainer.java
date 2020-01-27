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
import frc.robot.commands.IntakeStop;
import frc.robot.commands.IntakeOn;
import frc.robot.commands.IntakeReverse;
import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.subsystems.JoystickButtonConstants;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here...
   private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  
  // The robot's commands are defined here...
  private final IntakeOn m_autoCommand = new IntakeOn(m_intakeSubsystem);
  

  // The driver's controller
  Joystick m_manipulatorStick = new Joystick(Constants.kManipulatorControllerPort);


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
    // Grab the hatch when the 'A' button is pressed.
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
        .whenPressed(new IntakeOn(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kCircle)
        .whenPressed(new IntakeReverse(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kTriangle)
        .whenPressed(new IntakeStop(m_intakeSubsystem));



  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
