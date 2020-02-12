/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.     Squidward                                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 * Squiward
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Speeds
    public final static int kELEVATOR_UP_SPEED = 1; 

    //Motor speed
    public final static double kHopperReverseSpeed = -0.6;
    public final static double kHopperForwardSpeed = 0.6;
    public final static double kStopperReverseSpeed = -0.6;
    public final static double kStopperForwardSpeed = 0.6;
    public final static double kIntakeForwardSpeed = 0.6;
    public final static double kIntakeReverseSpeed = -0.6;
    public final static double kSuckerForwardSpeed = 0.6;
    public final static double kSuckerReverseSpeed = -0.6;
    public final static double kShooterMotorSpeed = 1.0;
    public final static double kArmUpSpeed = 1.0;
    public final static double kArmDownSpeed = -1.0;
    
    // Controller Ports
    public final static int kDriverControllerPort = 0;
    public final static int kManipulatorControllerPort = 1;

    //CANid/channel numbers
    public final static int kLeftMotorChannel = 1;
    public final static int kRightMotorChannel = 2;
    public final static int kSuckerChannel = 3;
    public final static int kStopperChannel = 4;
    public final static int kHopperChannel = 5;
    public final static int kIntakeChannel = 6;
    public final static int kShooterMotorPort = 7;
    public final static int kArmCANID = 8;
    public final static int kPbotLeftMotorCANID = 9;
    public final static int kPbotRightMotorCANID = 10;



    //Timeouts
    public final static double kMoveOneBallTimeOut = 1.0;
    public final static double kMoveAllBallsTimeOut = 5.0;

    //Squared Inputs
    public final static boolean kFastSquaredInputs = true;
    public final static boolean kSlowSquaredInputs = false;
}
