package frc.robot.commands.teleop;
import static edu.wpi.first.units.Units.RPM;

import edu.wpi.first.wpilibj2.command.Command; 
import frc.robot.subsystems.ShooterMotor;
import frc.robot.Constants.PhysicalConstants;
import frc.robot.Constants.PhysicalConstants.IntakeArmConstants;

public class SetShooterVelocity extends Command {
    public ShooterMotor shootermotor;
    public double velocity;
    public double RPM; 
    public SetShooterVelocity(double velocity) {
        shootermotor = ShooterMotor.getInstance();
        RPM=velocity;

        addRequirements(shootermotor);
    }
    public void initialize(){
        shootermotor.getPidController().reset();
    }
    public void execute(){
        shootermotor.setShooterSpeedRPM(RPM);
    }
    public void end(boolean interupted) { 
        shootermotor.stop();
    }
    public boolean isFinished() {
        return false;
    }
}