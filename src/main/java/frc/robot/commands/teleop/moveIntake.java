package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
public class MoveIntake extends Command {
    public Intake m_intake;
    public double m_speed;
    public MoveIntake(double speed) {
        m_speed = speed;
        m_intake = Intake.getInstance();
        addRequirements(m_intake);
    }
    public void initialize() {
    }
    public void execute() {
        m_intake.setSpeed(m_speed);
    }
    public void end(boolean interrupted) {
        m_intake.stop();
    }    
    public boolean isFinished() {
        return false;
    }
    public static MoveIntake maxSpeed(){
        return new MoveIntake(1.0);
    }
}