/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;

public class DriveTrainP extends DriveTrain {
  static WPI_TalonSRX m_leftMotor;
  static WPI_TalonSRX m_rightMotor;

  public DriveTrainP(Joystick m_driverJoystick) {
    super(
      new DifferentialDrive(
        m_leftMotor = new WPI_TalonSRX(Constants.kPbotLeftMotorCANID), 
        m_rightMotor = new WPI_TalonSRX(Constants.kPbotRightMotorCANID)
      ),
      m_driverJoystick
    );
    m_leftMotor.configOpenloopRamp(Constants.kDriveRampRate);
    m_rightMotor.configOpenloopRamp(Constants.kDriveRampRate);
  }

}
