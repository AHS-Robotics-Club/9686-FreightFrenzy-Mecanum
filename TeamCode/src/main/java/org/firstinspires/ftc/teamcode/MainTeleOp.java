package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.commands.DuckySpinnerCommand;
import org.firstinspires.ftc.teamcode.commands.drive.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.DuckySpinnerSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

@TeleOp(name = "MainTeleOp")
public class MainTeleOp extends CommandOpMode {

    // Motors
    private Motor frontLeft, frontRight, backLeft, backRight; // Drivetrain
    private Motor mDuckySpinner; // Other motors

    // Servos

    // Subsystems
    private DriveSubsystem driveS;
    private MecanumDriveSubsystem mecanumDriveS;
    private DuckySpinnerSubsystem duckySpinnerS;

    // Commands
    private DriveCommand drive_Com;
    private DuckySpinnerCommand duckySpinner_Com;

    // Extra Stuff
    private GamepadEx gPad1;
    private FtcDashboard dashboard;
    private RevIMU revIMU;

    // Commands Stuff

    // Constants
    private final double DRIVE_MULT = 1.0;
    private final double SLOW_MULT = 0.3;
    private final double BLUE_DS = 0.25;
    private final double RED_DS = -0.25;

    @Override
    public void initialize() {

        // Telemetry for Dashboard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        // Motors
        // drivetrain
        frontLeft = new Motor(hardwareMap, "frontLeft");
        frontRight = new Motor(hardwareMap, "frontRight");
        backLeft = new Motor(hardwareMap, "backLeft");
        backRight = new Motor(hardwareMap, "backRight");

        // other
        mDuckySpinner = new Motor(hardwareMap, "duckySpinner");

        // Servos

        // Extra Stuff Setup
        revIMU = new RevIMU(hardwareMap);
        revIMU.init();

        gPad1 = new GamepadEx(gamepad1);

        dashboard = FtcDashboard.getInstance();

        // Subsystems & Commands
        mecanumDriveS = new MecanumDriveSubsystem(new SampleMecanumDrive(hardwareMap), false);

        frontLeft.motor.setDirection(DcMotor.Direction.FORWARD);
        frontRight.motor.setDirection(DcMotor.Direction.FORWARD);

        frontLeft.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        driveS = new DriveSubsystem(frontLeft, frontRight, backLeft, backRight, revIMU);
        drive_Com = new DriveCommand(driveS, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT);

        /*
        TODO: When adding intake subsystem, use something like this, AKA look at Ethan's grabber code from last yr.

        grabberCommand = new InstantCommand(()-> {
            if(wobbleSystem.isGrabbing())
                wobbleSystem.openGrabber();
            else
                wobbleSystem.closeGrabber();
            }, wobbleSystem);
        */

        // Set Buttons

        // Ducky Spinner, no subsystem or command needed
        gPad1.getGamepadButton(GamepadKeys.Button.X).whenHeld(new StartEndCommand(() -> mDuckySpinner.set(BLUE_DS), () -> mDuckySpinner.stopMotor()));
        gPad1.getGamepadButton(GamepadKeys.Button.A).whenHeld(new StartEndCommand(() -> mDuckySpinner.set(RED_DS), () -> mDuckySpinner.stopMotor()));

        // Slow Drivetrain
        gPad1.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whenHeld(new StartEndCommand(
                () -> new DriveCommand(driveS, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, SLOW_MULT),
                () -> new DriveCommand(driveS, gPad1::getLeftX, gPad1::getLeftY, gPad1::getRightX, DRIVE_MULT)
        ));

        // Sets default command for drivetrain
        register(driveS);
        driveS.setDefaultCommand(drive_Com);

        // How to add telemetry, not that relevant rn
        schedule(new RunCommand(() -> {
            telemetry.addData("Ducky Spinner Speed", mDuckySpinner.get());
            telemetry.update();
        }));
    }
}
