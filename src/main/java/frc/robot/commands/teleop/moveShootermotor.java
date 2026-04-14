package frc.robot.commands.teleop;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterMotor;
import frc.robot.Constants.PhysicalConstants;
public class MoveShooterMotor extends Command {

public ShooterMotor shootermotor;
public double speed;
public double targetRPM;
public MoveShooterMotor(double speed) {
    shootermotor = ShooterMotor.getInstance();
    addRequirements(shootermotor);
    this.speed=speed;
    }

    public void initialize() {

    }

    public void execute() {
        shootermotor.setSpeed(speed);
    }
    public void end(boolean interrupted) {
        shootermotor.stop();
    }
    public boolean isFinished() {
        return false;
    }
}