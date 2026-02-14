package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterMotor;
public class MoveShooterMotor extends Command {

public ShooterMotor m_shooterMotor;
public double m_speed;

public MoveShooterMotor(double speed){
    m_speed = speed;
    m_shooterMotor = ShooterMotor.getInstance();
    addRequirements(m_shooterMotor);
    }

    public void initialize() {

    }

    public void execute() {
        m_shooterMotor.setSpeed(m_speed);

    }
    public void end(boolean interrupted) {
        m_shooterMotor.stop();
    }
    public boolean isFinished() {
        return false;
    }
}