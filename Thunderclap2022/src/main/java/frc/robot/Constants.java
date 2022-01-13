/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                    */
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

    //Motor speeds in duty cycle unless otherwise specified, e.g. RPM
    public final static double kHopperReverseSpeed = -0.6;
    public final static double kHopperForwardSpeed = 0.6;
    public final static double kCbotStopperReverseSpeed = -0.6;
    public final static double kPbotStopperReverseSpeed = 1.0;
    public final static double kCbotStopperForwardSpeed = 0.6;
    public final static double kPbotStopperForwardSpeed = -1.0;
    public final static double kIntakeForwardSpeed = 0.5;
    public final static double kIntakeReverseSpeed = -0.5;
    public final static double kSuckerForwardSpeed = 0.4;
    public final static double kSuckerReverseSpeed = -0.4;
    public final static double kShooterMotorSpeed = -0.3;
    public final static double kShooterGearRatio = 7/6;
    public final static double kNeoMaxSpeed = 5676;
    public final static double kShooterMotorSpeedRPM = kShooterMotorSpeed*kNeoMaxSpeed*kShooterGearRatio;
    public final static double kWinchUpSpeed = -1.0;  // Release winch slowly as linear motion system extends
    public final static double kWinchDownSpeed = 0.4;  // Lift bot at full speed (temp slower for testing)

    // Motor Ramp Rates (HOW LONG IN SECONDS FROM NEUTRAL TO FULL THROTTLE)
    public final static double kHopperRampRate = 0.5;
    public final static double kStopperRampRate = 0.5;
    public final static double kSuckerRampRate = 0.5;
    public final static double kIntakeRampRate = 0.5;
    public final static double kShooterRampRate = 0.5;
    public final static double kArmRampRate = 0.5;
    public final static double kDriveRampRate = 0.1;
    
    // Controller Ports
    public final static int kDriverControllerPort = 0;
    public final static int kManipulatorControllerPort = 1;

    //CANid/channel numbers
    public final static int kPbotLeftMotorCANID = 1;
    public final static int kPbotRightMotorCANID = 2;
    public final static int kCbotFrontLeftMotorCANID = 1;
    public final static int kCbotRearLeftMotorCANID = 2;
    public final static int kCbotRearRightMotorCANID = 3;
    public final static int kCbotFrontRightMotorCANID = 4;
   
    public final static int kHopperChannel = 5;
    public final static int kShooterMotorPort = 6;
    public final static int kIntakeChannel = 7;
    public final static int kSuckerChannel = 8;
    public final static int kStopperChannel = 9;
    public final static int kPCM_CANID = 11;
    public final static int kArmCANID = 12;

    //Digital Inputs
    public final static int kBumperSwitchChannel = 0;

    //Solenoids
    public final static int kArmSolenoidForwardChannel = 0;
    public final static int kArmSolenoidReverseChannel = 1;
    public final static int kWinchBrakeSolenoidForwardChannel = 2;
    public final static int kWinchBrakeSolenoidReverseChannel = 3;

    //Relays
    public final static int kCompressorRelay = 0;
    
    //Timeouts
    public final static double kMoveOneBallTimeOut = 1.0;
    public final static double kMoveAllBallsTimeOut = 5.0;
    public final static double kStoppertoShooterTimeout = 0.5;
    public final static double kBrakeTimeout = 0.25;

    //Drive train constants
    public final static boolean kFastSquaredInputs = true;
    public final static boolean kSlowSquaredInputs = true;
    public final static double kSlowDriveScalar = 0.7;  // with squared inputs, sets max speed to 49%

    //Encoder 
    public final static int kEncoderResolution = 42;

    // Autonomous
    public final static double kAutoDriveSpeed = 0.4;
    public final static double kAutoRotationSpeed = 0.4;
    public static enum GSPath{
        ARed,
        ABlue,
        BRed,
        BBlue,
        NONE
    }
    
    // Robot measurements
    public final static double kWheelDiameterInches = 8;
    public final static double kWheelDiameterCM = kWheelDiameterInches * 2.54; // derived from above
    public final static double kWheelCircumferenceInches = kWheelDiameterInches * Math.PI; // derived from above
    public final static double kWheelCircumferenceCM = kWheelDiameterCM * Math.PI; // derived from above
    public final static double kDriveGearRatio = 11.1; // measured as 11.34851783, 10.5 on first day
    /*
    (implemented as: for every one rotation of the wheel, how many rotations of the motor?)
    Ex: for every 1 wheel rotation, motor rotates 2 rotations, this vaiable would be 2
    */
    public final static double kStopDistance = 0.25; // in wheel rotations .275
    public final static double kRotStopDistance = 0; // in degrees
    public final static double kP_DriveStraight = 0.01; // Proportional error gain for driving straight
	public static final double kI_DriveStraight = 0;
	public static final double kD_DriveStraight = 0;
    public final static String kInchesKey = "DriveInches input";
    
    
    // Ultrasonic
    public final static int kUltrasonicInputPort = 1;
    public final static int kUltrasonicOutputPort = 2;
    public final static double kUltraOffset = 8; // ultra is mounted on the right side of the robot this far away from the center (on the axis it is measuring)
    public final static double kUltraError = 2; // space on either side in which the robot
    
    // Galactic Search
    // starting positions (based on Pat's diagrams)
    public final static double kARedPos = 90 - kUltraOffset;
    public final static double kABluePos = 30 - kUltraOffset;
    public final static double kBRedPos = 120 - kUltraOffset;
    public final static double kBBluePos = 60 - kUltraOffset;
    
    // PIDTurnToDegrees
    public final static double kRotationP = 0.011;
    // public final static double kRotationP = 0.01522;
    public final static double kRotationI = 0;
    public final static double kRotationD = 0;
    // public final static double kRotationD = 0.00045;
    public final static double kAutoRotationError = 2.0; // stop rotating if bot is x degrees away from target
    public final static double kStaticPowerRequirement = 0.35;
    public final static double kAutoRotationVelocityError = 150.0;

    // PIDDriveEncoder
    public final static double kPIDDriveRotP = 0.015;
    public final static double kPIDDriveRotI = 0;
    public final static double kPIDDriveRotD = 0;
    public final static double kPIDDriveP = 0.03;
    public final static double kPIDDriveI = 0;
    public final static double kPIDDriveD = 0;
    public final static double kPIDDrivePosTol = 0.5;
    public final static double kPIDDriveVelTol = 10;
    public final static double kCustomPower = 0.17;
    public final static double kCustomPowerMin = 0.08;
}
