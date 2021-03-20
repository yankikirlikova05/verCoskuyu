package frc.robot.subsystems;

import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants;

public class driveTrain extends SubsystemBase {
  public static WPI_TalonSRX frontLeft;
  public static WPI_TalonSRX frontRight;
  public static WPI_TalonSRX backLeft;
  public static WPI_TalonSRX backRight;
  static DifferentialDrive drive;
  static MecanumDrive m_robotDrive;
  private AHRS gyro;

  /**
   * Creates a new driveTrain.
   */
  public driveTrain(AHRS gyro) {
    this.gyro = gyro;
    frontLeft = new WPI_TalonSRX(Constants.LEFT_FRONT);
    frontLeft.setInverted(false);
    frontLeft.setNeutralMode(NeutralMode.Coast);

    frontRight = new WPI_TalonSRX(Constants.RIGHT_FRONT);
    frontRight.setInverted(false);
    frontRight.setNeutralMode(NeutralMode.Coast);

    backLeft = new WPI_TalonSRX(Constants.LEFT_BACK);
    backLeft.setInverted(false);
    backLeft.setNeutralMode(NeutralMode.Coast);

    backRight = new WPI_TalonSRX(Constants.RIGHT_BACK);
    backRight.setInverted(false);
    backRight.setNeutralMode(NeutralMode.Coast);

    m_robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRight, backRight);
    drive = new DifferentialDrive(leftMotors, rightMotors);

  }

  @Override
  public void periodic() {
    System.out.println("left" +getLeftDistance());
    System.out.println("right" + getRightDistance());
    // This method will be called once per scheduler run
  }
  public static double getLeftDistance(){
    return ((-driveTrain.frontLeft.getSelectedSensorPosition()/4096.0) * 2.0 * Math.PI * (3.0 * 2.54) + 
    (-driveTrain.backLeft.getSelectedSensorPosition()/4096.0) * 2.0 * Math.PI * (3.0 * 2.54))/2.0;
  }
  
  public static double getRightDistance(){
    return (( driveTrain.frontRight.getSelectedSensorPosition()/4096.0) * 2 * Math.PI * (3.0 * 2.54) + 
    (driveTrain.backRight.getSelectedSensorPosition()/4096.0) * 2.0 * Math.PI * (3.0 * 2.54))/2.0;
  }

  public static double getAverageDistance(){
    return (getLeftDistance() + getRightDistance()) / 2.0;
  }

  public static void driveWithJoystick(Joystick driverJoystick, double speed) {
    drive.arcadeDrive(driverJoystick.getRawAxis(Constants.XBOX_LEFT_Y_AXIS) * speed,
        driverJoystick.getRawAxis(Constants.XBOX_LEFT_X_AXIS) * speed);
  }
  public void zeroHeading(){
    gyro.zeroYaw();
  }

  public double getAngle(){
    return Math.IEEEremainder(gyro.getAngle(), 360) * (Constants.kGyroReversed ? 1.0: -1.0);
    //return Math.IEEEremainder(gyro.getAngle(), 360) * isGyroReversed();
    //39. satır = 40. satır
  }

  //drive function yazın (input: x,y,z) -> arda 
  public void verCoskuyu(double yspeed, double xspeed, double zspeed){
    m_robotDrive.driveCartesian(yspeed, xspeed, zspeed);
  }

  public void stop(){
    drive.stopMotor();
  }
}