/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class LiftSubsystem extends SubsystemBase {
    private CANSparkMax m_winchMotor; 
    private DoubleSolenoid m_brakeSolenoid;
    private CANEncoder m_encoder;
    private boolean m_isBrakeEngaged = false;
    private boolean m_crankWinchActive = false;
    private boolean m_crankWinchMoving = false;

    /**
     * Creates a new ArmSubsystem.
     */
    public LiftSubsystem() {
        m_winchMotor = new CANSparkMax(Constants.kArmCANID, MotorType.kBrushless);
        m_winchMotor.setOpenLoopRampRate(Constants.kArmRampRate);
        m_brakeSolenoid = new DoubleSolenoid(Constants.kPCM_CANID, Constants.kWinchBrakeSolenoidForwardChannel, 
            Constants.kWinchBrakeSolenoidReverseChannel);
        m_encoder = m_winchMotor.getEncoder();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        crankOrReleaseWinch(); // If joystick active, crank or release winch accordingly

        // checkAndStopMotor(); seems like its not functioning currently

        // Update dashboard with important values
        SmartDashboard.putNumber("Winch Encoder", m_encoder.getPosition());
        SmartDashboard.putNumber("Winch Velocity", m_encoder.getVelocity());
        SmartDashboard.getBoolean("Winch Brake Engaged", m_isBrakeEngaged);
    }
    
    public void engageBrake() {
        m_brakeSolenoid.set(Value.kReverse);
        m_isBrakeEngaged = true;
    }

    public void disengageBrake() {
        m_brakeSolenoid.set(Value.kForward);
        m_isBrakeEngaged = false;
    }

    public void turnOffBrakeSolenoid() {
        m_brakeSolenoid.set(Value.kOff);
    }
    
    public void releaseWinch() {
        m_winchMotor.set(Constants.kWinchUpSpeed);
        SmartDashboard.putNumber("Winch Set Speed", Constants.kWinchUpSpeed);
    }

    public void stopWinchMotor() {
        m_winchMotor.set(0);
        SmartDashboard.putNumber("Winch Set Speed", 0);
    }
    public void crankWinch() {
        m_winchMotor.set(Constants.kWinchDownSpeed);
        SmartDashboard.putNumber("Winch Set Speed", Constants.kWinchDownSpeed);
        m_crankWinchActive = true;
    }

    public void brakeMode() {
        m_winchMotor.setIdleMode(CANSparkMax.IdleMode.kBrake);
    }

    public void coastMode() {
        m_winchMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    }

    private void crankOrReleaseWinch() {
        double speed = RobotContainer.m_manipulatorStick.getY();
        // Only adjust the  motor speed here if joystick is active, allowing for noise
        if(speed > 0.05 || speed < -0.05) {
            m_winchMotor.set(speed);
        } else {
            m_winchMotor.set(0);
        }
        SmartDashboard.putNumber("Winch Set Speed", speed);
    }

    private void checkAndStopMotor() {
        if(m_crankWinchActive) {
            final double kStopThreshold = 0.1; // ?????
            if(m_encoder.getVelocity() > kStopThreshold) {
                m_crankWinchMoving = true;
            }
            if(m_crankWinchMoving && (m_encoder.getVelocity() < kStopThreshold)) {
                SmartDashboard.putBoolean("Winch Speed Stop" + kStopThreshold, true);
                m_crankWinchMoving = false;
                m_crankWinchActive = false;
                stopWinchMotor();
            }
            else {
                SmartDashboard.putBoolean("Winch Speed Stop" + kStopThreshold, false);
            }
        }
    }
}
