package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterMotor;
import frc.robot.Constants.PhysicalConstants;
public class MoveShooterMotor extends Command {

public ShooterMotor m_shooterMotor;
public double speed;
public MoveShooterMotor(double speed){
    m_shooterMotor = ShooterMotor.getInstance();
    addRequirements(m_shooterMotor);
    this.speed=speed;
    }

    public void initialize() {

    }

    public void execute() {
        m_shooterMotor.setSpeed(speed);

    }
    public void end(boolean interrupted) {
        m_shooterMotor.stop();
    }
    public boolean isFinished() {
        return false;
    }
}