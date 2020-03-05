/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Rev2mDistanceSensor;

/**
 * Class for using color sensor to sense ball
 */
public class BallOutputSensor2m{
  
  /**
   * Change the I2C port below to match the connection of your color sensor
   */
  private final Rev2mDistanceSensor.Port i2cPort = Rev2mDistanceSensor.Port.kOnboard;

  /**
   * A Rev Color Sensor V3 object is constructed with an I2C port as a 
   * parameter. The device will be automatically initialized with default 
   * parameters.
   */
  private final Rev2mDistanceSensor m_distanceSensor = new Rev2mDistanceSensor(i2cPort);

  public BallOutputSensor2m() {
  }

  public double SenseDistance() {
    double range = m_distanceSensor.getRange();
    boolean rangeValid = m_distanceSensor.isRangeValid();

    SmartDashboard.putNumber("Output Range", range);
    SmartDashboard.putBoolean("Output Range Valid", rangeValid);

    return range;
  }

  public boolean IsBallPresent() {
    double range = SenseDistance();
    boolean isPresent = range < 4.0;
    SmartDashboard.putBoolean("Output BallPresent", isPresent);
    return isPresent;
  }

}
