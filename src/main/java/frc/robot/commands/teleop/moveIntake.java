package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake;
public class moveIntake extends Command {
    public intake m_intake;
    public double m_speed;
    public moveIntake(ddouble speed) {
        m_speed = speed;
        m_intake = intake.getInstance();
    }
    public void initiialize() {

    }
    public void execute() {
        m_intake.setSpeed(m_speed);
    }
    public void end(boolwean interrupted) {
        intake.stop();
    }    
    public boolean isFinished() {
        return false;
    }
    public static moveIntake maxSpeed(){
        return new moveIntake(1.0);
    }
}