package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;

public class DriveSubsystem extends SubsystemBase {

    private MecanumDrive mecanumDrive;
    private Motor frontLeft, frontRight, backLeft, backRight;
    private RevIMU revIMU;

    public DriveSubsystem(Motor fL, Motor fR, Motor bL, Motor bR, RevIMU imu) {
        frontLeft = fL;
        frontRight = fR;
        backLeft = bL;
        backRight = bR;

        revIMU = imu;

        mecanumDrive = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    public void drive(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        mecanumDrive.driveFieldCentric(-strafeSpeed, -forwardSpeed, -turnSpeed, revIMU.getHeading(), false);
    }



}
