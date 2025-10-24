package org.firstinspires.ftc.teamcode.opmodes;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous (name = "Auto")
public class Auto extends LinearOpMode {
    double shootingx = -11.75;
    double shootingy = -12;
    double shootingangle = 225;
    double ballstack1 = -11.75;
    double ballstack2 = 11;
    double ballstack3 = 34.75;
    @Override
   public void runOpMode() throws InterruptedException {
        Pose2d beginPose = new Pose2d(new Vector2d(0, 63), Math.toRadians(270));
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(beginPose);

        waitForStart();
        if (isStopRequested()) return;
        /*
        drive.followTrajectorySequence(straight);

        // Path 1: Go to shooting position
        Trajectory traj1 = drive.trajectoryBuilder(beginPose)
                .lineToLinearHeading(new Pose2d(shootingx, shootingy, Math.toRadians(shootingangle)))
                .build();
        drive.followTrajectory(traj1);

        // Path 2: Go to first ball stack area
        Trajectory traj2 = drive.trajectoryBuilder(traj1.end())
                .lineToSplineHeading(new Pose2d(ballstack1, -20, Math.toRadians(270)))
                .build();
        drive.followTrajectory(traj2);

        // Path 3: Strafe to first ball stack
        Trajectory traj3 = drive.trajectoryBuilder(traj2.end())
                .strafeTo(new Vector2d(ballstack1, -52))
                .build();
        drive.followTrajectory(traj3);

        // Path 4: Return to shooting position
        Trajectory traj4 = drive.trajectoryBuilder(traj3.end())
                .lineToLinearHeading(new Pose2d(shootingx, shootingy, Math.toRadians(shootingangle)))
                .build();
        drive.followTrajectory(traj4);

        // Path 5: Go to second ball stack area
        Trajectory traj5 = drive.trajectoryBuilder(traj4.end())
                .lineToSplineHeading(new Pose2d(ballstack2, -20, Math.toRadians(270)))
                .build();
        drive.followTrajectory(traj5);

        // Path 6: Strafe maneuvers near second ball stack
        Trajectory traj6 = drive.trajectoryBuilder(traj5.end())
                .strafeTo(new Vector2d(ballstack2, -12))
                .strafeTo(new Vector2d(12, -60))
                .strafeTo(new Vector2d(ballstack2, -50))
                .build();
        drive.followTrajectory(traj6);

        // Path 7: Return to shooting position
        Trajectory traj7 = drive.trajectoryBuilder(traj6.end())
                .lineToLinearHeading(new Pose2d(shootingx, shootingy, Math.toRadians(shootingangle)))
                .build();
        drive.followTrajectory(traj7);

        // Path 8: Go to third ball stack area
        Trajectory traj8 = drive.trajectoryBuilder(traj7.end())
                .lineToSplineHeading(new Pose2d(ballstack3, -20, Math.toRadians(270)))
                .build();
        drive.followTrajectory(traj8);

        // Path 9: Strafe maneuvers near third ball stack
        Trajectory traj9 = drive.trajectoryBuilder(traj8.end())
                .strafeTo(new Vector2d(ballstack3, -12))
                .strafeTo(new Vector2d(ballstack3, -60))
                .build();
        drive.followTrajectory(traj9);

        // Path 10: Return to shooting position
        Trajectory traj10 = drive.trajectoryBuilder(traj9.end())
                .lineToLinearHeading(new Pose2d(shootingx, shootingy, Math.toRadians(shootingangle)))
                .build();

        TrajectorySequence straight = drive.trajectorySequenceBuilder(beginPose)
                .forward(24)
                .build();

         */
        TrajectorySequence ts = drive.trajectorySequenceBuilder(beginPose)
                .turn(Math.toRadians(45)) // Turns 45 degrees counter-clockwise
                .build();

        drive.followTrajectorySequence(ts);
    }
}
