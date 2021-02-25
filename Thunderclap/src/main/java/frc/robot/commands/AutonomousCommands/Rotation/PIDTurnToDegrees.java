package frc.robot.commands.AutonomousCommands.Rotation;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

public class PIDTurnToDegrees extends PIDCommand{
    private double m_setpoint;
    private final DriveTrainC m_DriveTrainC;
    private final double m_targetHeading;
    private final boolean m_isAbsolute;

    public PIDTurnToDegrees(DriveTrainC driveTrainC, double targetHeading, boolean isAbsolute) {
        super(
            new PIDController(Constants.kRotationP, Constants.kRotationI, Constants.kRotationD),
            driveTrainC::getHeading,
            0,
            (double out) -> {
                System.out.println("PID(" + Constants.kRotationP + ") out = " + out);
                
                if (out < Constants.kStaticPowerRequirement && out > -1 * Constants.kStaticPowerRequirement) {
                    if (out > 0) {
                        out = Constants.kStaticPowerRequirement;
                    } else {
                        out = -1 * Constants.kStaticPowerRequirement;
                    }
                }

                driveTrainC.drive(0, out, Constants.kFastSquaredInputs);
            },
            driveTrainC
        );

        m_DriveTrainC = driveTrainC;
        m_targetHeading = targetHeading;
        m_isAbsolute = isAbsolute;
    }

    /**
     * Get the setpoint for the PIDCommand constructor. This method is static so that it can be used in the constructor.
     * @param isAbsolute from PIDTurnToDegrees constructor
     * @param targetHeading from PIDTurnToDegrees constructor
     * @param driveTrainC from PIDTurnToDegrees constructor
     * @return the setpoint. should only be called once
     */
    private void setSetpoint(boolean isAbsolute, double targetHeading, DriveTrainC driveTrainC) {
        double setpoint;
        if(isAbsolute) {
            setpoint = targetHeading;
        } else {
            setpoint = targetHeading + driveTrainC.getHeading();
        }
        m_setpoint = setpoint;
    }

    @Override
    public void initialize() {
        super.initialize();
        setSetpoint(m_isAbsolute, m_targetHeading, m_DriveTrainC);
    }

    @Override
    public void execute() {
        m_useOutput.accept(
            m_controller.calculate(m_measurement.getAsDouble(), m_setpoint));    
        SmartDashboard.putNumber("SetPointDifference", m_setpoint - m_DriveTrainC.getHeading());
    }

    @Override
    public boolean isFinished() {
        PIDController con = this.getController();
        con.setTolerance(Constants.kAutoRotationError);
        return con.atSetpoint();
    }
}
