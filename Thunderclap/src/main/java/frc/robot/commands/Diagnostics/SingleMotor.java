package frc.robot.commands.Diagnostics;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class SingleMotor extends CommandBase {
    private final CANSparkMax m_motor;
    private final CANEncoder m_encoder;
    private File logFile;

    public SingleMotor(DriveTrainC driveTrainC, CANSparkMax motor) {
        m_motor = motor;
        m_encoder = motor.getEncoder();
        addRequirements(driveTrainC);
    }

    @Override
    public void initialize() {
        createLogFile();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    private void createLogFile() {
        String name = LocalDateTime.now().toString();

        try {
            logFile = new File(name);
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
