package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.commands.TrajectoryFollowerCommand;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.MecanumDriveSubsystem;

// NOTE: Face bot towards the plastic barrier, park bot between the tiles before the plastic barrier.
@Autonomous(name = "RedParkingAuton")
public class RedParkingAuton extends CommandOpMode {

    // Subsystems
    private MecanumDriveSubsystem mecanumDriveS;

    // Extra Stuff
    private ElapsedTime time;

    // Pathing
    // Start Pose
    private Pose2d startPose = new Pose2d(0.0, -62.0, Math.toRadians(0.0));

    // Trajectory
    private Trajectory traj0 = mecanumDriveS.trajectoryBuilder(startPose).
            forward(35.0)
            .build();

    @Override
    public void initialize() {
        mecanumDriveS = new MecanumDriveSubsystem(new SampleMecanumDrive(hardwareMap), false);

        TrajectoryFollowerCommand autonomous = new TrajectoryFollowerCommand(mecanumDriveS, traj0);

        if(isStopRequested()){
            return;
        }

        schedule(autonomous);
    }
}
