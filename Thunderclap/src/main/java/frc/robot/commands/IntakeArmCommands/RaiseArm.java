package frc.robot.commands.IntakeArmCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeArm;

public class RaiseArm extends CommandBase{
    private final IntakeArm m_intakeArm;

    public RaiseArm(IntakeArm intakeArm) {
        m_intakeArm = intakeArm;
        addRequirements(intakeArm);
    }

    @Override
    public void initialize(){
        m_intakeArm.raiseArm();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
