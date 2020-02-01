/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.Squidward                                                      */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.Joystick;

public class DriveTrain extends SubsystemBase {
  // * Member fields

  private final DifferentialDrive m_differentialDrive;

  private final Joystick m_leftJoystick  = new Joystick(Constants.kLeftControllerPort);
  private final Joystick m_rightJoystick = new Joystick(Constants.kRightControllerPort);


  public DriveTrain(DifferentialDrive differentialDrive) {
    m_differentialDrive = differentialDrive;
  }

  public void drive() {
    double leftSpeed = m_leftJoystick.getY();
    double rightSpeed = m_rightJoystick.getY();
    // TODO: add a button for setting the squareInputs
    boolean squareInputs = true;
    m_differentialDrive.tankDrive(leftSpeed, rightSpeed, squareInputs);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // This should be used for diagnostics and not used to run motors since this is used
    // in all modes, not just teleop

  }
}
