/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.CompressorSS;
import frc.robot.subsystems.DriveTrainC;
// import frc.robot.subsystems.DriveTrainP;
import frc.robot.commands.DriveTrainCommands.SlowDrive;
import frc.robot.commands.HopperCommands.HopperOff;
import frc.robot.commands.HopperCommands.HopperOn;
import frc.robot.commands.HopperCommands.HopperReverse;
import frc.robot.commands.HopperCommands.SuckerOff;
import frc.robot.commands.HopperCommands.SuckerOn;
import frc.robot.commands.DriveTrainCommands.CalibrateNavX;
import frc.robot.commands.DriveTrainCommands.FastDrive;
import frc.robot.commands.DriveTrainCommands.SetCoastMode;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.IntakeCommands.IntakeReverse;
import frc.robot.commands.IntakeCommands.IntakeOff;

import frc.robot.subsystems.IntakeArm;
import frc.robot.commands.IntakeArmCommands.RaiseOrLowerArm;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.commands.UseHopperModeCommands.ShootAllBalls;

import frc.robot.subsystems.HopperSubsystem;
import frc.robot.commands.SetHopperModeCommands.SetIntakeMode;
import frc.robot.commands.SetHopperModeCommands.SetOffMode;
import frc.robot.commands.SetHopperModeCommands.SetShootingMode;
import frc.robot.commands.UseHopperModeCommands.ResetBallCount;

import frc.robot.commands.CombinationCommandGroups.ReverseAllMotorsExceptShooter;
import frc.robot.commands.CombinationCommandGroups.ShootOneBallWrapped;
import frc.robot.commands.AutonomousCommands.AutoNav.BarrelRacingCommandGroup;
import frc.robot.commands.AutonomousCommands.AutoNav.BounceCommandGroup;
import frc.robot.commands.AutonomousCommands.AutoNav.SlalomCommandGroup;
import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
// import frc.robot.commands.AutonomousCommands.DriveStraightAutonomous;
import frc.robot.commands.AutonomousCommands.GalacticSearch.ABlueCommandGroup;
import frc.robot.commands.AutonomousCommands.GalacticSearch.ARedCommandGroup;
import frc.robot.commands.AutonomousCommands.GalacticSearch.BBlueCommandGroup;
import frc.robot.commands.AutonomousCommands.GalacticSearch.BRedCommandGroup;
import frc.robot.commands.AutonomousCommands.GalacticSearch.DriveTurnGroup;
// import frc.robot.commands.AutonomousCommands.GalacticSearch.ARedCommandGroup;
// import frc.robot.commands.AutonomousCommands.GalacticSearch.DriveTurnGroup;
// import frc.robot.commands.AutonomousCommands.GalacticSearch.OnePowerCell;
// import frc.robot.commands.AutonomousCommands.Rotation.TurnToDegrees;
// import frc.robot.commands.AutonomousCommands.DriveLeftAutonomous;
// import frc.robot.commands.AutonomousCommands.DriveRightAutonomous;
// import frc.robot.commands.AutonomousCommands.DriveDistance.DriveInches;
// import frc.robot.commands.DriveTrainCommands.CalibrateNavX;
import frc.robot.commands.AutonomousCommands.GalacticSearch.UltrasonicChoose;
import frc.robot.commands.AutonomousCommands.PIDDriveDistance.PIDDriveInches;
import frc.robot.commands.AutonomousCommands.Rotation.PIDTurnToDegrees;
import frc.robot.commands.AutonomousCommands.GalacticSearch.PathTrigger;

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
  // private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem(Constants.kPbotStopperForwardSpeed, Constants.kPbotStopperReverseSpeed);
  private final HopperSubsystem m_hopperSubsystem = new HopperSubsystem(Constants.kCbotStopperForwardSpeed, Constants.kCbotStopperReverseSpeed);
  // private final DriveTrain m_driveTrain = new DriveTrainP(m_driverStick);
  private final DriveTrainC m_driveTrain = new DriveTrainC(m_driverStick);
  private final CompressorSS m_compressor = new CompressorSS();
  
  // The robot's commands are defined here...
  // private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  // private final DriveStraightAutonomous m_driveStraightAutonomous = new DriveStraightAutonomous(m_driveTrain);
  // private final DriveLeftAutonomous m_driveLeftAutonomous = new DriveLeftAutonomous(m_driveTrain);
  // private final DriveRightAutonomous m_driveRightAutonomous = new DriveRightAutonomous(m_driveTrain);
  private final CalibrateNavX m_calibrateNavX = new CalibrateNavX(m_driveTrain);
  // private final ARedCommandGroup m_ARedCommandGroup = new ARedCommandGroup(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem, m_driveTrain);
  private final PathTrigger m_ARedTrigger = new PathTrigger();
  private final PathTrigger m_ABlueTrigger = new PathTrigger();
  private final PathTrigger m_BRedTrigger = new PathTrigger();
  private final PathTrigger m_BBlueTrigger = new PathTrigger();
  private final UltrasonicChoose m_ultrasonicChoose = new UltrasonicChoose(m_driveTrain, m_ARedTrigger, m_ABlueTrigger, m_BRedTrigger, m_BBlueTrigger);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    SmartDashboard.putData("Set Coast Mode", new SetCoastMode(m_driveTrain));

    SmartDashboard.putNumber(Constants.kInchesKey, 60);
    SmartDashboard.putData("Calibrate NavX", m_calibrateNavX);
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
    System.out.println("Autonomous init");
    // An ExampleCommand will run in autonomous
    // return m_driveStraightAutonomous;
    // return m_driveLeftAutonomous;
    // return m_driveRightAutonomous;

    // return new DriveTurnGroup(m_driveTrain);
    // return new DriveInches(m_driveTrain, 45);
    // return new PIDTurnToDegrees(m_driveTrain, 90, false);
    // return new BBlueCommandGroup(m_hopperSubsystem, m_intakeSubsystem, m_shooterSubsystem, m_driveTrain, m_intakeArmSubsystem);
    // return new BarrelRacingCommandGroup(m_driveTrain, m_intakeArmSubsystem); 
    // return new BounceCommandGroup(m_driveTrain, m_intakeArmSubsystem);
    return new SlalomCommandGroup(m_driveTrain, m_intakeArmSubsystem);

    // return new PIDDriveInches(m_driveTrain, 265);
  }
}
