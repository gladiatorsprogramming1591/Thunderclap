package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.BallOutputSensor;
import frc.robot.BallSensor;
import frc.robot.Constants;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * The hopper subsystem
 */
public class HopperSubsystem extends SubsystemBase {

    enum HopperMode {intakeMode, shootingMode, advancingMode, offMode}

    private final CANSparkMax m_hopperMotor;
    private final WPI_TalonSRX m_stopperMotor;
    private final WPI_TalonSRX m_suckerMotor;
    private final double m_stopperForwardSpeed;
    private final double m_stopperReverseSpeed;
    // private final BallSensor m_ballSensor;
    // private final BallOutputSensor m_ballOutputSensor;
    // private final BallOutputSensor2m m_ballOutputSensor;
    private final DigitalInput m_bumperSwitch;
    private int m_ballCount = 0;
    private boolean m_isHopperOn = false;
    private boolean m_ballOutputSensorTriggered = false;
    private HopperMode m_hopperMode = HopperMode.offMode;

    public HopperSubsystem(final double stopperForwardSpeed, final double stopperReverseSpeed) {
        m_stopperForwardSpeed = stopperForwardSpeed;
        m_stopperReverseSpeed = stopperReverseSpeed;
    
        m_hopperMotor = new CANSparkMax(Constants.kHopperChannel, MotorType.kBrushless);
        m_stopperMotor = new WPI_TalonSRX(Constants.kStopperChannel);
        m_suckerMotor = new WPI_TalonSRX(Constants.kSuckerChannel);

        m_hopperMotor.setOpenLoopRampRate(Constants.kHopperRampRate);
        m_stopperMotor.configOpenloopRamp(Constants.kStopperRampRate);
        m_suckerMotor.configOpenloopRamp(Constants.kSuckerRampRate);

        // m_ballSensor = new BallSensor();
        // m_ballOutputSensor = new BallOutputSensor();
        // m_ballOutputSensor = new BallOutputSensor2m();
        m_bumperSwitch = new DigitalInput(Constants.kBumperSwitchChannel);

        // SmartDashboard.putData("Stopper Motor", m_stopperMotor);
        // SmartDashboard.putData("Sucker Motor", m_suckerMotor);
        SmartDashboard.putNumber("Ball Count", m_ballCount);
    }
    public void hopperOn() {
        m_hopperMotor.set(Constants.kHopperForwardSpeed);
        m_isHopperOn = true;
    }

    public void hopperReverse() {
        m_hopperMotor.set(Constants.kHopperReverseSpeed);
    }

    public void hopperOff() {
        m_hopperMotor.set(0);
        m_isHopperOn = false;
    }

    public void stopperOn() {
        m_stopperMotor.set(m_stopperForwardSpeed);
    }

    public void stopperOff() {
        m_stopperMotor.set(0);
    }

    public void stopperReverse() {
        m_stopperMotor.set(m_stopperReverseSpeed);
    }

    public void suckerOn() {
        m_suckerMotor.set(Constants.kSuckerForwardSpeed);
    }   

    public void suckerOff() {
        m_suckerMotor.set(0);
    }   

    public void suckerReverse() {
        m_suckerMotor.set(Constants.kSuckerReverseSpeed);
    }

    /**
     * This method assumes that the ball sensor is located right behind the sucker
     * and that once the ball is no longer detected, it will turn off since it is done moving the ball.
     * This method also assumes tha the caller will call it repeatedly while it is intaking.
     * Returns:
     *   - True if intaking a ball
     *   - False if not intaking a ball
     */
    public boolean intakeOneBallNoCountCheck() {
        // if ( m_ballSensor.IsBallPresent() ) {
        if ( IsBallPresent() ) {
            if (!m_isHopperOn) {
                hopperOn();
                m_ballCount++;
                SmartDashboard.putNumber("Ball Count", m_ballCount);
            }
        }
        else {
            hopperOff();
        }

        SmartDashboard.putBoolean("Hopper active", m_isHopperOn);
        return m_isHopperOn;
    }

    /**
     * This method assumes that the ball sensor is located right behind the sucker
     * and that once the ball is no longer detected, it will turn off since it is done moving the ball.
     * This method also assumes tha the caller will call it repeatedly while it is intaking.
     * Returns:
     *   - True if intaking a ball
     *   - False if not intaking a ball
     */
    public boolean intakeOneBall() {
        if ( m_ballCount < 5 ) {
            intakeOneBallNoCountCheck();
        }
        else {
            // If we get to ball 5, we will still go through this else, but we need to turn the
            // hopper off once it has moved past the sensor.
            // if ( !m_ballSensor.IsBallPresent() ) {
            if ( !IsBallPresent() ) {
                hopperOff();
            }
        }

        SmartDashboard.putBoolean("Hopper active", m_isHopperOn);
        return m_isHopperOn;
    }

    @Override
    public void periodic() {
        // TODO: Remove after done testing
        // m_ballSensor.SenseColor();
        // m_ballSensor.IsBallPresent();
        // m_ballOutputSensor.IsBallPresent();
        SmartDashboard.putBoolean("Bumper Switch", m_bumperSwitch.get());
    }

    public void setIntakeMode() {
        m_hopperMode = HopperMode.intakeMode;
        SmartDashboard.putString("Hopper Mode", "Intake");     
        suckerOn();
        stopperOff();
    }

    public void setShootingMode() {
        m_hopperMode = HopperMode.shootingMode;
        SmartDashboard.putString("Hopper Mode", "Shooting");
        suckerOff();
        // stopperOn();
    }

    public void setOffMode() {
        m_hopperMode = HopperMode.offMode;
        SmartDashboard.putString("Hopper Mode", "Off");  
        suckerOff();
        stopperOff();   
        hopperOff();
    }

    public boolean IsBallPresent() {
        return !m_bumperSwitch.get();  
    }

    public void outputOneBall() {
        // Until sensor is in place, assume ball is at stopper and just turn stopper on to advance ball to shooter
        stopperOn(); 

        /* Temp remove until sensor is hooked up
        // If we are just starting and the ball hasn't triggered the sensor, turn on the hopper and wait for trigger
        if ( !m_ballOutputSensorTriggered ) {
            hopperOn();  // Make sure hopper is on
            if( m_ballOutputSensor.IsBallPresent() ) {
                m_ballOutputSensorTriggered = true;
            }
        }
        // Once the ouptut sensor has been triggered, when we can't sense a ball anymore, turn the hopper off and decrement ball count
        if ( m_ballOutputSensorTriggered && !m_ballOutputSensor.IsBallPresent() ) {
            hopperOff();
            m_ballCount--;
            SmartDashboard.putNumber("Ball Count", m_ballCount);
        }
        */

        SmartDashboard.putBoolean("Hopper active", m_isHopperOn);
    }

    public void outputAllBalls() {
        stopperOn();  // Make sure stopper is on first so balls don't get wedged
        hopperOn();  // Now turn on hopper to advance all balls
        m_ballCount = 0;
        SmartDashboard.putNumber("Ball Count", m_ballCount);
    }

    public void resetBallCount() {
        m_ballCount = 0;
        SmartDashboard.putNumber("Ball Count", m_ballCount);
    }

}