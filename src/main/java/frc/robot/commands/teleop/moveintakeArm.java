package frc.robot.commands.teleop;
import frc.robot.subsystems.intakeArm;
import edu.wpi.first.wpilibj2.command.Command;


public class moveintakeArm extends Command {
        private double m_speed;
        private intakeArm m_intakeArm;
    public moveintakeArm(double speed) {
        m_speed = speed;
        m_intakeArm = intakeArm.getInstance();
        addRequirements(m_intakeArm);

    }
    public void initialize() {
    }
    public void execute() {
        m_intakeArm.setSpeed(m_speed);
    }
    public void end(boolean interrupted) {
        m_intakeArm.stop();
    }    
    public boolean isFinished() {
        return false;
    }
    public static moveintakeArm maxSpeed(){
        return new moveintakeArm(1.0);
    }
}