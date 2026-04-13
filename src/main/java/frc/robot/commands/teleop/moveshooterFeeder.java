package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.subsystems.ShooterFeeder;
public class MoveShooterFeeder extends Command {
    private ShooterFeeder shooterFeeder;
    public double speed;
    public MoveShooterFeeder(ShooterFeeder shooterFeeder ,double speed) {
        shooterFeeder = ShooterFeeder.getInstance();
        this.speed=speed;
            addRequirements(shooterFeeder);
    }
    public void initialize() {
        
    }
    public void execute() {
        shooterFeeder.setSpeed(speed);
     }
    public void end(boolean interrupted) {
        shooterFeeder.stop();
     }    
    public boolean isFinished() {
         return false;
     }
    
}
