/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.JoystickButtonConstants;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.CompressorSS;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrainC;
// import frc.robot.subsystems.DriveTrainP;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.commands.HopperCommands.HopperOff;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.HopperCommands.HopperReverse;
import frc.robot.commands.HopperCommands.SuckerOff;
import frc.robot.commands.HopperCommands.SuckerOn;
import frc.robot.commands.DriveTrainCommands.FastDrive;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.IntakeCommands.IntakeReverse;
import frc.robot.commands.IntakeCommands.IntakeOff;
import frc.robot.commands.UseHopperModeCommands.IntakeOneExtraBall;

import frc.robot.subsystems.IntakeArm;
import frc.robot.commands.IntakeArmCommands.RaiseOrLowerArm;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.UseHopperModeCommands.ShootAllBalls;
import frc.robot.commands.UseHopperModeCommands.ShootOneBall;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.commands.SetHopperModeCommands.SetIntakeMode;
import frc.robot.commands.SetHopperModeCommands.SetOffMode;
import frc.robot.commands.SetHopperModeCommands.SetShootingMode;
import frc.robot.commands.UseHopperModeCommands.ResetBallCount;

import frc.robot.commands.CombinationCommandGroups.ReverseAllMotorsExceptShooter;
import frc.robot.commands.CombinationCommandGroups.ShootOneBallWrapped;
import frc.robot.subsystems.LiftSubsystem;
import frc.robot.commands.LiftCommands.CrankWinch;
import frc.robot.commands.LiftCommands.DisengageWinchBrake;
import frc.robot.commands.LiftCommands.EngageBrakeAndStopWinch;
import frc.robot.commands.LiftCommands.EngageWinchBrake;
import frc.robot.commands.LiftCommands.StopWinchMotor;
import frc.robot.commands.LiftCommands.ReleaseWinch;

import frc.robot.commands.AutonomousCommands.DriveStraightAutonomous;
import frc.robot.commands.AutonomousCommands.DriveLeftAutonomous;
import frc.robot.commands.AutonomousCommands.DriveRightAutonomous;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // ---CONTROLLERS---
  public final static Joystick m_manipulatorStick = new Joystick(Constants.kManipulatorControllerPort);
  public final static Joystick m_driverStick = new Joystick(Constants.kDriverControllerPort);

  // ---SUBSYSTEMS---
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IntakeArm m_intakeArmSubsystem = new IntakeArm();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final LiftSubsystem m_liftSubsystem = new LiftSubsystem();
  // private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem(Constants.kPbotStopperForwardSpeed, Constants.kPbotStopperReverseSpeed);
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem(Constants.kCbotStopperForwardSpeed, Constants.kCbotStopperReverseSpeed);
  // private final DriveTrain m_driveTrain = new DriveTrainP(m_driverStick);
  private final DriveTrain m_driveTrain = new DriveTrainC(m_driverStick);
  private final CompressorSS m_compressor = new CompressorSS();
  
  // The robot's commands are defined here...
  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final DriveStraightAutonomous m_driveStraightAutonomous = new DriveStraightAutonomous(m_driveTrain);
  private final DriveLeftAutonomous m_driveLeftAutonomous = new DriveLeftAutonomous(m_driveTrain);
  private final DriveRightAutonomous m_driveRightAutonomous = new DriveRightAutonomous(m_driveTrain);
  


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
    // ---DRIVE TRAIN--- 
    new JoystickButton(m_driverStick, JoystickButtonConstants.kL2)
      .whenPressed(new SlowDrive(m_driveTrain));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kL2)
      .whenReleased(new FastDrive(m_driveTrain));

    // ---INTAKE SECTION---

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL1)
      .whenPressed(new IntakeReverse(m_intakeSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL1)
      .whenReleased(new IntakeOff(m_intakeSubsystem));

    // ---INTAKE ARM SECTION---

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR1) 
      .whenPressed(new RaiseOrLowerArm(m_intakeArmSubsystem));
      
    // ---SHOOTER SECTION---

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL2)
      .whenPressed(new ShootOneBallWrapped(m_shooterSubsystem, m_hopperSubsystem, m_intakeSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR2)
      .whenPressed(new ShootAllBalls(m_hopperSubsystem, m_shooterSubsystem));

    // ---HOPPER MODES---
    final int LEFT = 270;
    final int RIGHT = 90;
    final int DOWN = 180;
    new POVButton(m_manipulatorStick, LEFT).whenPressed(new SetShootingMode(m_hopperSubsystem, m_shooterSubsystem, m_intakeSubsystem));
    new POVButton(m_manipulatorStick, RIGHT).whenPressed(new SetIntakeMode(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));
    new POVButton(m_manipulatorStick, DOWN).whenPressed(new SetOffMode(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kStart)
      .whenPressed(new ResetBallCount(m_hopperSubsystem)); 

    // ---COMBINATION BUTTONS---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack)
      .whenPressed(new ReverseAllMotorsExceptShooter(m_hopperSubsystem, m_intakeSubsystem));

    // ---LIFT SECTION---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kA) 
      .whenPressed(new ReleaseWinch(m_liftSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenPressed(new StopWinchMotor(m_liftSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kB)
      .whenPressed(new CrankWinch(m_liftSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL3)
      .whenPressed(new EngageWinchBrake(m_liftSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR3)
      .whenPressed(new DisengageWinchBrake(m_liftSubsystem));
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY)
      .whenPressed(new EngageBrakeAndStopWinch(m_liftSubsystem));
    
    // ---INDIVIDUAL MOTORS---
    new JoystickButton(m_driverStick, JoystickButtonConstants.kA)
      .whenPressed(new HopperOn(m_hopperSubsystem));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kA)
      .whenReleased(new HopperOff(m_hopperSubsystem));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kB)
      .whenPressed(new HopperReverse(m_hopperSubsystem));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kB)
      .whenReleased(new HopperOff(m_hopperSubsystem));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kY)
      .whenPressed(new SuckerOn(m_hopperSubsystem));
    new JoystickButton(m_driverStick, JoystickButtonConstants.kY)
      .whenReleased(new SuckerOff(m_hopperSubsystem));
    
    }
    
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    // return m_driveStraightAutonomous;
    return m_driveLeftAutonomous;
    // return m_driveRightAutonomous;
  }
}
