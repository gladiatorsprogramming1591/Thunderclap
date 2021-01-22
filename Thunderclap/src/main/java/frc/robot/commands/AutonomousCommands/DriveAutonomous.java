/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.AutonomousCommands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class DriveAutonomous extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_driveTrain;
  private double m_AxisForward;
  private double m_AxisTurning;
  private String m_name;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveAutonomous(DriveTrain driveTrain, double forwardSpeed, double rotationSpeed, String name) {
    m_driveTrain = driveTrain;
    m_AxisForward = forwardSpeed;
    m_AxisTurning = rotationSpeed;
    m_name = name;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_driveTrain.setBrakeMode();
    SmartDashboard.putNumber(m_name+" Left Enc Start", m_driveTrain.getLeftEncPos());
    SmartDashboard.putNumber(m_name+" Right Enc Start", m_driveTrain.getRightEncPos());
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // System.out.println("DriveAuto Calling Drive. Fwdspeed = " + m_AxisForward + "; TurnSpeed = " + m_AxisTurning);
    m_driveTrain.drive(m_AxisForward, m_AxisTurning, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.setCoastMode();
    SmartDashboard.putNumber(m_name+" Left Enc End", m_driveTrain.getLeftEncPos());
    SmartDashboard.putNumber(m_name+" Right Enc End", m_driveTrain.getRightEncPos());
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
