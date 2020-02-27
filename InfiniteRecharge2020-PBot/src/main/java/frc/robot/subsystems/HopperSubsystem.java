package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.BallSensor;
import frc.robot.Constants;
import frc.robot.commands.HopperOff;
import frc.robot.RobotContainer;

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
    private final BallSensor m_ballSensor;
    private int m_ballCount;
    private boolean m_isHopperOn;
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

        m_ballSensor = new BallSensor();
        m_ballCount = 0;

        m_isHopperOn = false;

        SmartDashboard.putData("Stopper Motor", m_stopperMotor);
        SmartDashboard.putData("Sucker Motor", m_suckerMotor);
        SmartDashboard.putData("Hopper Subsystem", this);
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
    public boolean intakeOneBall() {
        if ( m_ballCount < 5 ) {
            if ( m_ballSensor.IsBallPresent() ) {
                if (!m_isHopperOn) {
                    hopperOn();
                    m_ballCount++;
                }
            }
            else {
                hopperOff();
            }
        }
        else {
            // If we get to ball 5, we will still go through this else, but we need to turn the
            // hopper off once it has moved past the sensor.
            if ( !m_ballSensor.IsBallPresent() ) {
                hopperOff();
            }
        }

        SmartDashboard.putBoolean("intakeOneBall active", m_isHopperOn);
        return m_isHopperOn;
    }

    @Override
    public void periodic() {
        // TODO: Remove after done testing
        m_ballSensor.SenseColor();
        m_ballSensor.IsBallPresent();
    }

    public void setIntakeMode() {
        m_hopperMode = HopperMode.intakeMode;
        SmartDashboard.putString("Hopper Mode", "Intake");     
    }

    public void setShootingMode() {
        m_hopperMode = HopperMode.shootingMode;
        SmartDashboard.putString("Hopper Mode", "Shooting");
    }

    public void setOffMode() {
        m_hopperMode = HopperMode.offMode;
        SmartDashboard.putString("Hopper Mode", "Off");     
    }
}