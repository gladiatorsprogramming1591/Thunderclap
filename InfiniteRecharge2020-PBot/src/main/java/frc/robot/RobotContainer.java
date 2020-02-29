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

import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.DriveTrainC;
// import frc.robot.subsystems.DriveTrainC;
import frc.robot.subsystems.DriveTrainP;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.commands.DriveTrainCommands.FastDrive;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.IntakeCommands.IntakeOn;
import frc.robot.commands.IntakeOneExtraBall;
import frc.robot.commands.IntakeCommands.IntakeReverse;
import frc.robot.commands.IntakeCommands.IntakeOff;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.intakeArm;
import frc.robot.commands.ShooterCommands.ShooterOff;
import frc.robot.commands.ShooterCommands.ShooterOn;

import frc.robot.commands.RobotModeCommands.SetOffMode;
import frc.robot.commands.RobotModeCommands.SetShootingMode;
import frc.robot.commands.ShootAllBalls;
import frc.robot.commands.ShootOneBall;
import frc.robot.commands.RobotModeCommands.SetIntakeMode;

import frc.robot.subsystems.ArmSubsystem;
import frc.robot.commands.ArmCommands.ArmDown;
import frc.robot.commands.ArmCommands.ArmOff;
import frc.robot.commands.ArmCommands.ArmUp;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.HopperCommands.HopperReverse;
import frc.robot.commands.HopperCommands.HopperOff;

import frc.robot.commands.HopperCommands.StopperOn;
import frc.robot.commands.HopperCommands.StopperReverse;
import frc.robot.commands.HopperCommands.StopperOff;

import frc.robot.commands.HopperCommands.SuckerOn;
import frc.robot.commands.HopperCommands.SuckerReverse;
import frc.robot.commands.HopperCommands.SuckerOff;

import frc.robot.commands.CommandGroups.TurnOnAllMotors;
import frc.robot.commands.CommandGroups.ReverseAllMotorsExceptShooter;
import frc.robot.commands.CommandGroups.TurnOffAllMotors;
import frc.robot.commands.CommandGroups.IntakeandSuckerOn;
import frc.robot.commands.IntakeArmCommands.RaiseOrLowerArm;
import frc.robot.commands.CommandGroups.IntakeandSuckerOff;
import frc.robot.commands.CommandGroups.StopperandShooterOn;
import frc.robot.commands.CommandGroups.StopperandShooterOff;

// import frc.robot.commands.BallLoadingCommandGroup;
// import frc.robot.commands.BallLoadingCommandGroupStop;
// import frc.robot.commands.MoveBall;

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
  private final intakeArm m_intakeArmSubsystem = new intakeArm();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
  // private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem(Constants.kPbotStopperForwardSpeed, Constants.kPbotStopperReverseSpeed);
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem(Constants.kCbotStopperForwardSpeed, Constants.kCbotStopperReverseSpeed);
  // private final DriveTrain m_driveTrain = new DriveTrainP(m_driverStick);
  private final DriveTrain m_driveTrain = new DriveTrainC(m_driverStick);
  
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
    // ---INTAKE SECTION---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kA) 
      .whenPressed(new IntakeOn(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kB)
      .whenPressed(new IntakeReverse(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kA) 
      .whenReleased(new IntakeOff(m_intakeSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kB)
      .whenReleased(new IntakeOff(m_intakeSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kR3) 
      .whenPressed(new RaiseOrLowerArm(m_intakeArmSubsystem));

      
    // ---SHOOTER SECTION---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack) 
      .whenPressed(new ShooterOn(m_shooterSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack) 
      .whenReleased(new ShooterOff(m_shooterSubsystem));

    // ---ARM---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL3) 
      .whenPressed(new ArmUp(m_armSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR3)
      .whenPressed(new ArmDown(m_armSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL3)
      .whenReleased(new ArmOff(m_armSubsystem));
      
    // new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kBack)
    //   .whenReleased(new ArmOff(m_armSubsystem));

    // ---HOPPER---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenPressed(new HopperOn(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenPressed(new HopperReverse(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kY) 
      .whenReleased(new HopperOff(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kX)
      .whenReleased(new HopperOff(m_hopperSubsystem));

    // ---MOVE BALL---
    // new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR1)
    //   .whenPressed(new MoveBall(m_hopperSubsystem).withTimeout(Constants.kMoveOneBallTimeOut));

    // new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR2)
    //   .whenPressed(new MoveBall(m_hopperSubsystem).withTimeout(Constants.kMoveAllBallsTimeOut));

    // ---SUCKER(SUCKS BALLS INTO HOPPER)---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL1) 
      .whenPressed(new SuckerOn(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL2)
      .whenPressed(new SuckerReverse(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL1) 
      .whenReleased(new SuckerOff(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kL2)
      .whenReleased(new SuckerOff(m_hopperSubsystem));

    // ---STOPPER---
    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR1)
      .whenPressed(new StopperOn(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR1)
      .whenReleased(new StopperOff(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR2)
      .whenPressed(new StopperReverse(m_hopperSubsystem));

    new JoystickButton(m_manipulatorStick, JoystickButtonConstants.kR2)
      .whenReleased(new StopperOff(m_hopperSubsystem));

    // ---HOPPER MODES---
    final int LEFT = 270;
    final int RIGHT = 90;
    final int DOWN = 180;
    new POVButton(m_manipulatorStick, LEFT).whenPressed(new SetShootingMode(m_hopperSubsystem, m_shooterSubsystem, m_intakeSubsystem));
    new POVButton(m_manipulatorStick, RIGHT).whenPressed(new SetIntakeMode(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));
    new POVButton(m_manipulatorStick, DOWN).whenPressed(new SetOffMode(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));

    // ---DRIVE TRAIN--- 
    new JoystickButton(m_driverStick, JoystickButtonConstants.kL3)
      .whenPressed(new SlowDrive(m_driveTrain));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kL3)
      .whenReleased(new FastDrive(m_driveTrain));

    // ---COMBINATION BUTTONS---
    new JoystickButton(m_driverStick, JoystickButtonConstants.kA)
      .whenPressed(new TurnOnAllMotors(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kA)
      .whenReleased(new TurnOffAllMotors(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kB)
      .whenPressed(new IntakeandSuckerOn(m_intakeSubsystem, m_hopperSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kB)
      .whenReleased(new IntakeandSuckerOff(m_intakeSubsystem, m_hopperSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kY)
      .whenPressed(new StopperandShooterOn(m_hopperSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kY)
      .whenReleased(new StopperandShooterOff(m_hopperSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kX)
      .whenPressed(new ReverseAllMotorsExceptShooter(m_hopperSubsystem, m_intakeSubsystem));
    
    new JoystickButton(m_driverStick, JoystickButtonConstants.kR1)
      .whenReleased(new TurnOffAllMotors(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kL1)
      .whenPressed(new ShootOneBall(m_hopperSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kL2)
      .whenPressed(new ShootAllBalls(m_hopperSubsystem, m_shooterSubsystem));

    new JoystickButton(m_driverStick, JoystickButtonConstants.kR2)
      .whenPressed(new IntakeOneExtraBall(m_hopperSubsystem, m_intakeSubsystem));    
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
