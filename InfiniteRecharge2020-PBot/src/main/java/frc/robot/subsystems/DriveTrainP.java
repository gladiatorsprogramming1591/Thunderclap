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


public class DriveTrainP extends DriveTrain {

  public DriveTrainP() {
    super(
      new DifferentialDrive(
        new WPI_TalonSRX(Constants.kPbotLeftMotorCANID), 
        new WPI_TalonSRX(Constants.kPbotRightMotorCANID)
      )
    );

  }

}
