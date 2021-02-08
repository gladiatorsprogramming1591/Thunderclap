package frc.robot.commands.Diagnostics;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrainC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import edu.wpi.first.wpilibj.Timer;

public class SingleMotor extends CommandBase {
    private final DriveTrainC m_DriveTrainC;
    private final int m_motorID;
    private final CANSparkMax m_motor;
    private final CANEncoder m_encoder;
    
    private File m_logFile;
    private FileWriter m_writer;

    private Timer m_timer = new Timer();
    private double m_startPos;

    public SingleMotor(DriveTrainC driveTrainC, int motorID) {
        m_motor = new CANSparkMax(motorID, MotorType.kBrushless);
        m_encoder = m_motor.getEncoder();
        m_DriveTrainC = driveTrainC;
        m_motorID = motorID;
        addRequirements(driveTrainC);
    }

    @Override
    public void initialize() {
        createLogFile();
        writeLogFile("Time EncoderPos");

        m_startPos = m_encoder.getPosition();
        m_DriveTrainC.setCoastMode();
        m_motor.set(0.8);

        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        writeLogFile(String.valueOf(m_timer.get()) + " " +  String.valueOf(m_encoder.getPosition()));
    }

    @Override
    public boolean isFinished() {
        return m_timer.hasElapsed(8);
    }

    @Override
    public void end(boolean interrupted) {
        m_motor.stopMotor();

        writeLogFile("Started At:" + m_startPos);
        writeLogFile("Ended At:" + m_encoder.getPosition());
        writeLogFile("Total Distance Travelled:" + (m_encoder.getPosition() - m_startPos));
        writeLogFile("Motor ID:" + m_motorID);
        closeLogFile();
        
        m_timer.stop();
    }

    private void createLogFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String name = LocalDateTime.now().format(formatter);

        try {
            m_logFile = new File("1591logs/" + name);
            if (m_logFile.createNewFile()) {
                System.out.println("File created: " + m_logFile.getName());
            } else {
                System.out.println("File already exists.");
            }
            m_writer = new FileWriter(m_logFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void writeLogFile(String msg) {
        try {
            m_writer.write(msg + System.lineSeparator());
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void closeLogFile() {
        try {
            m_writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
