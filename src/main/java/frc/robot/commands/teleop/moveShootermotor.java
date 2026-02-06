package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooterMotor;
public class moveShooterMotor extends Command {

public shooterMotor m_shootermotor;
public double m_speed;

public moveShooterMotor(double speed){
    m_speed = speed;
    m_shootermotor = shooterMotor.getInstance();
    }
    public void initialize() {

    }

    public void execute() {
        m_shootermotor.setSpeed(m_speed);
    }
    public void end(boolean interrupted) {
        m_shootermotor.stop();
    }
    public boolean isFinished() {
        return false;
    }
}