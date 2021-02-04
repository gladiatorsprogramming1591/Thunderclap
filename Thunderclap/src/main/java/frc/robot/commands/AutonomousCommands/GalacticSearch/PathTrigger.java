package frc.robot.commands.AutonomousCommands.GalacticSearch;

import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * A custom trigger to be used with path commands for the Galactic Search Challenge.
 * Pass to the constructor of UltrasonicChoose for activation.
 */
public class PathTrigger extends Trigger{
    private boolean m_active = false;

    @Override
    public boolean get() {
        if(m_active) {
            m_active = false; // reset trigger state
            return true; // cause command to be scheduled
        } else {
            return false; // do nothing
        }
    }

    public void activate(){
        m_active = true;
    }
}
