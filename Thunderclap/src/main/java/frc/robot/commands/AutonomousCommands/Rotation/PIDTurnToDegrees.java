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
            () -> {
                double setpoint;
                if(isAbsolute) {
                    setpoint = targetHeading;
                } else {
                    setpoint = targetHeading + driveTrainC.getHeading();
                }
                return setpoint;
            },
            (double o) -> {
                driveTrainC.drive(0, o, Constants.kFastSquaredInputs);
            },
            driveTrainC
        );
    }

    @Override
    public boolean isFinished() {
        return getController().atSetpoint();
    }
}
