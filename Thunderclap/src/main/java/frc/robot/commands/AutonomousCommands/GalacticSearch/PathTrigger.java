package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.button.Trigger;

public class PathTrigger extends Trigger{
    private boolean m_active = false;

    @Override
    public boolean get() {
        if(m_active) {
            m_active = false;
            return true;
        } else {
            return false;
        }
    }

    public void activate(){
        m_active = true;
    }
}
