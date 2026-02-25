package frc.robot.commands.auto;

import java.time.chrono.JapaneseChronology;

import edu.wpi.first.units.measure.Time;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.ShooterMotorConstants;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.ShooterFeeder;
import frc.robot.subsystems.ShooterMotor;
import frc.robot.subsystems.Intake;
public class TimedIntake extends Command {
    public Intake intake;
    public Timer timer;
    public double speed;
    public double time;
    public TimedIntake(double time, double m_speed3) {
        timer=new Timer();
        intake=Intake.getInstance();
        addRequirements(intake);
        speed=m_speed3;
        this.time=time;
    }
    public void initialize() {
        timer.reset();
        timer.start();
    }
    public void execute() {
        intake.setSpeed(speed);
    }
    public void end(boolean interrupted) {
        intake.setSpeed(0);
        timer.stop();
    }
    public boolean isFinished() {
        return timer.hasElapsed(time);
    }
}
