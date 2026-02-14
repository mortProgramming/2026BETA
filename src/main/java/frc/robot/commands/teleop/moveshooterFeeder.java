package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterFeeder;
public class MoveShooterFeeder extends Command {
    private double m_speed;
    private ShooterFeeder m_moveshooterFeeder;
    public MoveShooterFeeder(double speed) {
        m_speed = speed;
        m_moveshooterFeeder = ShooterFeeder.getInstance();
            addRequirements(m_moveshooterFeeder);
    }
     public void initialize() {

     }
     public void execute() {
        m_moveshooterFeeder.setSpeed(m_speed);
     }
     public void end(boolean interrupted) {
        m_moveshooterFeeder.stop();
     }    
     public boolean isFinished() {
         return false;
     }
    
}
