/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


/**
 * The elevator subsystem
 */
public class IntakeSubsystem extends SubsystemBase {

    private SparkMax m_intakeMotor;

    public IntakeSubsystem() {
        m_intakeMotor = new SparkMax();
    }
    public void intakeOn() {
        m_intakeMotor.set(Constants.kELEVATOR_UP_SPEED);
    }

    //move elevator to the top
    public boolean elevatorTop() { //make this while held so loop doesn't continue
        if (m_elevatorTopSwitch.get() == true){ //if the top switch is not pressed
            m_elevatorMotor.set(m_ELEVATOR_UP_SPEED); //spin motor to move elevator up HOW FAST AND WHAT DIRECTION
        }
        m_isFinished = elevatorLimit();
        return m_isFinished;
    }

    public boolean elevatorLimit(){
        while(m_elevatorMotor.get() != 0) { //while the motor is in motion
            // if the either switch is pushed in
            if (m_elevatorTopSwitch.get() == false /* || elevatorBottomSwitch.get() == false */) { 
                m_elevatorMotor.set(0); //stop the motor
                m_finished = true;
            }
        }
        if (m_elevatorMotor.get() == 0){ //fixes a bug where if the elevator is already at the limit and this method is called, it returns false, even though the command should not continue
            m_finished = true;
        }
        return m_finished;
    }
}