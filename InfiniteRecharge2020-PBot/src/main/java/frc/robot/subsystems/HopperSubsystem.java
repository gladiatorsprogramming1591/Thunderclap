package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
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

    private final CANSparkMax m_hopperMotor;
    private final WPI_TalonSRX m_stopperMotor;
    private final WPI_TalonSRX m_suckerMotor;
    private double m_stopperForwardSpeed;
    private double m_stopperReverseSpeed;

    public HopperSubsystem(double stopperForwardSpeed, double stopperReverseSpeed) {
        m_stopperForwardSpeed = stopperForwardSpeed;
        m_stopperReverseSpeed = stopperReverseSpeed;
    
        m_hopperMotor = new CANSparkMax(Constants.kHopperChannel, MotorType.kBrushless);
        m_stopperMotor = new WPI_TalonSRX(Constants.kStopperChannel);
        m_suckerMotor = new WPI_TalonSRX(Constants.kSuckerChannel);

        m_hopperMotor.setOpenLoopRampRate(Constants.kHopperRampRate);
        m_stopperMotor.configOpenloopRamp(Constants.kStopperRampRate);
        m_suckerMotor.configOpenloopRamp(Constants.kSuckerRampRate);

        SmartDashboard.putData("Stopper Motor", m_stopperMotor);
        SmartDashboard.putData("Sucker Motor", m_suckerMotor);
        SmartDashboard.putData("Hopper Subsystem", this);
    }
    public void hopperOn() {
        m_hopperMotor.set(Constants.kHopperForwardSpeed);
    }

    public void hopperReverse() {
        m_hopperMotor.set(Constants.kHopperReverseSpeed);
    }

    public void hopperOff() {
        m_hopperMotor.set(0);
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
}