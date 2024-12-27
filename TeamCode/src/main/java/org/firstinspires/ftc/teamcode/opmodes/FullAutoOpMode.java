package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

@Autonomous(name="FullAutoOpMode", preselectTeleOp = "MainOpMode")
public class FullAutoOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap);
        ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        GrabberSubsystem grabberSubsystem = new GrabberSubsystem(hardwareMap);

        waitForStart();

        grabberSubsystem.setGrabberPosition(0.7);
        sleep(200);

        // elevator doesn't have autoextension method

//        grabberSubsystem.setAngle(0.35);
        sleep(200);

        driveSubsystem.driveRobotCentric(0, 0.25, 0);
    }
}
