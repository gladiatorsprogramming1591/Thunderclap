package frc.robot.commands.AutonomousCommands.Rotation;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainC;

public class PIDTurnToDegrees extends PIDCommand{
    public PIDTurnToDegrees(DriveTrainC driveTrainC, double targetHeading, boolean isAbsolute) {
        super(
            new PIDController(Constants.kRotationP, Constants.kRotationI, Constants.kRotationD),
            driveTrainC::getHeading,
            getSetpoint(isAbsolute, targetHeading, driveTrainC), //provide value, not function since it is called in execute. don't want to repeatedly apply relative offset
            (double out) -> {
                System.out.println("PID(" + Constants.kRotationP + ") out = " + out);
                driveTrainC.drive(0, out, Constants.kFastSquaredInputs);
            },
            driveTrainC
        );
    }

    /**
     * Get the setpoint for the PIDCommand constructor. This method is static so that it can be used in the constructor.
     * @param isAbsolute from PIDTurnToDegrees constructor
     * @param targetHeading from PIDTurnToDegrees constructor
     * @param driveTrainC from PIDTurnToDegrees constructor
     * @return the setpoint. should only be called once
     */
    private static double getSetpoint(boolean isAbsolute, double targetHeading, DriveTrainC driveTrainC) {
        double setpoint;
        if(isAbsolute) {
            setpoint = targetHeading;
        } else {
            setpoint = targetHeading + driveTrainC.getHeading();
        }
        return setpoint;
    }

    @Override
    public boolean isFinished() {
        PIDController con = this.getController();
        con.setTolerance(Constants.kAutoRotationError);
        return con.atSetpoint();
    }
}
