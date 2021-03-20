package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.driveTrain;
import com.ctre.phoenix.motorcontrol.SensorCollection;

public class VerKuskuyu extends CommandBase {
  
  public double goal = 600.0;
  public double currentDistance;
  public double error;
  public double kP = 0.01;
  public double rotation;
  public double startingDistance;
  public double speed;
  public driveTrain dt;

  //GYRO CODES
  private double relativeAngle;
  private double startingAngle;
  private double gyroGoal = 0;
  private double gyroError;
  private double gyrokP = 0.004;
  

  public VerKuskuyu(driveTrain dt){ 
    this.dt = dt;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startingDistance = dt.getAverageDistance();
    startingAngle = dt.getAngle();
    //starting distancei burada hesaplayın
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //GYRO codes
    relativeAngle = dt.getAngle() - startingAngle;
    gyroError = 1 * (gyroGoal - relativeAngle);
    dt.verCoskuyu(0.0, Constants.DriveStraightSpeed, gyroError * gyrokP);


    
    




    currentDistance = dt.getAverageDistance() - startingDistance; 
    //currentDistance'ı niye yeniden hesaplıyorsunuz aq
    //error'u hesaplayın
   error = goal - currentDistance;
   //error * kp ile robotu hareket ettirin
   speed = error * kP;
   System.out.println(speed);
   dt.verCoskuyu(0.0,speed,0.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dt.verCoskuyu(0,0,0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}