package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.shooterFeeder;
public class moveshooterFeeder extends Command {
    private double m_speed;
    private shooterFeeder m_moveshooterFeeder;
    public moveshooterFeeder(double speed) {
        m_speed = speed;
        m_moveshooterFeeder = shooterFeeder.getInstance();
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
