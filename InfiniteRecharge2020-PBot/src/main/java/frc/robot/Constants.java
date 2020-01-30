/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Speeds
    public final static int kELEVATOR_UP_SPEED = 1; 
    public final static double kIntakeForwardSpeed = 0.6;
    public final static double kIntakeReverseSpeed = -0.6;
    public final static double kShooterMotorSpeed = 1;
    // Ports
    public static int kDriverControllerPort = 0;
    public static int kManipulatorControllerPort = 0;
    // CAN IDs
    public static int kShooterMotorPort = 4;
    public final static int kIntakeChannel = 3;
}
