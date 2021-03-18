package frc.robot.subsystems;

import java.util.concurrent.TimeUnit;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class driveTrain extends SubsystemBase {
  WPI_TalonSRX frontLeft;
  WPI_TalonSRX frontRight;
  WPI_TalonSRX backLeft;
  WPI_TalonSRX backRight;
  static DifferentialDrive drive;
  static MecanumDrive m_robotDrive;

  /**
   * Creates a new driveTrain.
   */
  public driveTrain() {
    frontLeft = new WPI_TalonSRX(Constants.LEFT_FRONT);
    frontLeft.setInverted(false);

    frontRight = new WPI_TalonSRX(Constants.RIGHT_FRONT);
    frontRight.setInverted(true);

    backLeft = new WPI_TalonSRX(Constants.LEFT_BACK);
    backLeft.setInverted(false);

    backRight = new WPI_TalonSRX(Constants.RIGHT_BACK);
    backRight.setInverted(true);

    m_robotDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeft, backLeft);
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRight, backRight);
    drive = new DifferentialDrive(leftMotors, rightMotors);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static void driveWithJoystick(Joystick driverJoystick, double speed) {
    drive.arcadeDrive(driverJoystick.getRawAxis(Constants.XBOX_LEFT_Y_AXIS) * speed,
        driverJoystick.getRawAxis(Constants.XBOX_LEFT_X_AXIS) * speed);
  }

  public static void verCoskuyu(double speed){
    m_robotDrive.driveCartesian(0, speed, 0);
  }

  public void stop(){
    
    drive.stopMotor();
  }
}
