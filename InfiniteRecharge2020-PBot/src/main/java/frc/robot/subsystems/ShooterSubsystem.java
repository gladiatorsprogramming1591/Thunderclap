/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;

enum DutyCycleOrRPM {
  DUTY_CYCLE,
  RPM
}

public class ShooterSubsystem extends SubsystemBase {
  CANSparkMax m_shooterMotor;
  CANEncoder m_encoder;
  double m_targetVelocity;
  DutyCycleOrRPM m_rpmOrDutyCycle = DutyCycleOrRPM.RPM;
  private double m_maxSpeed;
  private double m_minSpeed;
  
    /**
   * Creates a new ExampleSubsystem.
   */
  public ShooterSubsystem() {
    m_shooterMotor = new CANSparkMax(Constants.kShooterMotorPort, MotorType.kBrushless);
    m_shooterMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);
    // These PIDF values were taken from GRR, need to tweak
    m_shooterMotor.getPIDController().setP(0.00075);
    m_shooterMotor.getPIDController().setI(0.0);
    m_shooterMotor.getPIDController().setD(2.0);
    m_shooterMotor.getPIDController().setFF(0.00019);

    m_encoder = new CANEncoder(m_shooterMotor);
    m_shooterMotor.setOpenLoopRampRate(Constants.kShooterRampRate);

    if (m_rpmOrDutyCycle == DutyCycleOrRPM.RPM) {
      m_maxSpeed = Constants.kNeoMaxSpeed;
      m_minSpeed = -Constants.kNeoMaxSpeed;
    }
    else {
      m_maxSpeed = 1;
      m_minSpeed = -1;
    }
  }

  public double getShooterVelocity() {
    return m_encoder.getVelocity();
  }

  public void setShooterSpeed(double speed) {
    if (speed < m_minSpeed || speed > m_maxSpeed) {
      System.out.println("Invalid speed set for shooter, " + speed);
      m_shooterMotor.setVoltage(0);
    } else {
      // m_shooterMotor.getPIDController().setFF(speed * 1 + 0);
      m_targetVelocity = speed;
      System.out.println("Setting shooter speed to " + speed);
      m_shooterMotor.getPIDController().setReference(speed, ControlType.kVelocity);
      // m_shooterMotor.set(speed);
    }
  }

  public boolean isShooterAtSpeed() {
    if (m_rpmOrDutyCycle == DutyCycleOrRPM.RPM) {
      return ((m_encoder.getVelocity() >= (m_targetVelocity * 1) - 25)
        && (m_encoder.getVelocity() <= (m_targetVelocity * 1) + 25));
    }
    else {
      return true; // Target velocity only supported for RPM  mode
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putString("Shooter Speed", "" + Math.round(m_encoder.getVelocity()));
  }

  public void shooterOn() {
    //Turns on the shooter motor
    System.out.println("Turning shooter on");
    if (m_rpmOrDutyCycle == DutyCycleOrRPM.RPM) {
      setShooterSpeed(Constants.kShooterMotorSpeedRPM);
    }
    else {
      setShooterSpeed(Constants.kShooterMotorSpeed);
    }
  }

  public void shooterOff() {
    //Turns off the shooter motor
    System.out.println("Turning shooter off");
    m_shooterMotor.set(0);
  }
}
