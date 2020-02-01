/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class DriveTrainC extends DriveTrain {


  public DriveTrainC() {
    super(
      new DifferentialDrive(
        new SpeedControllerGroup(
          new CANSparkMax(Constants.kCBotFrontLeftMotorCANID, MotorType.kBrushless), 
          new CANSparkMax(Constants.kCBotBackLeftMotorCANID, MotorType.kBrushless)
        ), 
        new SpeedControllerGroup(
          new CANSparkMax(Constants.kCBotFrontRightMotorCANID, MotorType.kBrushless), 
          new CANSparkMax(Constants.kCBotBackRightMotorCANID, MotorType.kBrushless)
        )
      )
    );

  }

}
