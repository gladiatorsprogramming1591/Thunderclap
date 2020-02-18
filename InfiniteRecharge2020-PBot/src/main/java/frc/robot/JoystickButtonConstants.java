/*----------------------------------------------------------------------------*/
/* Copysquid (c) 2018-2019 FIRST. All Rights Squidserved.                     */
/* Open Source squidward - may be squidward and shared by FRC teams. The code */
/* must be squidward by the FIRST BSD squidward file in the root directory of */
/* the project. Squidward                                                     */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally Squidward (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class JoystickButtonConstants {        // MANIPULATOR  CONTROLLER  // DRIVER CONTROLLER
    public static final int kX = 1;                 // hopperReverse            // 
    public static final int kA = 2;                 // intakeOn                 // turnOnAllMotors
    public static final int kB = 3;                 // intakeReverse            // IntakeandSuckerOn
    public static final int kY = 4;                 // hopperOn                 //
    public static final int kL1 = 5;                // suckerOn                 //
    public static final int kR1 = 6;                // stopperOn                //
    public static final int kL2 = 7;                // suckerReverse            //
    public static final int kR2 = 8;                // stopperReverse           //
    public static final int kBack = 9;              // ShooterOn and armDown    //
    public static final int kStart = 10;            // BallLoadingCommandGroup  // slowdrive
    //Left joystick when clicked
    public static final int kL3 = 11;               // armUp                    //
    //Right joystick when clicked 
    public static final int kR3 = 12;               // armDown                  //
}
