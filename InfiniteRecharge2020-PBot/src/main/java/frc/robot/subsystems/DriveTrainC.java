// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveTrainC extends DriveTrain {

    static CANSparkMax m_frontLeftMotor;
    static CANSparkMax m_frontRightMotor;
    static CANSparkMax m_rearLeftMotor;
    static CANSparkMax m_rearRightMotor;

    public DriveTrainC(Joystick m_driverJoystick) {
        super(new DifferentialDrive(
                new SpeedControllerGroup(
                        m_frontLeftMotor = new CANSparkMax(Constants.kCbotFrontLeftMotorCANID, MotorType.kBrushless),
                        m_rearLeftMotor = new CANSparkMax(Constants.kCbotRearLeftMotorCANID, MotorType.kBrushless)
                ), 
                new SpeedControllerGroup(
                        m_frontRightMotor = new CANSparkMax(Constants.kCbotFrontRightMotorCANID, MotorType.kBrushless), 
                        m_rearRightMotor = new CANSparkMax(Constants.kCbotRearRightMotorCANID, MotorType.kBrushless)
                )
        ),
        m_driverJoystick
    );
    m_frontLeftMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_rearLeftMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_frontRightMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
    m_rearRightMotor.setOpenLoopRampRate(Constants.kDriveRampRate);
  }

}
