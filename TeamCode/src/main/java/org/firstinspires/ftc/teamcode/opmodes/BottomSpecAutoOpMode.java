package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;`
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GrabberSubsystem;

@Autonomous(name= "BottomSpecAutoOpMode"
        , preselectTeleOp = "MainOpMode")
public class BottomSpecAutoOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem(hardwareMap);
        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap);
        GrabberSubsystem grabberSubsystem = new GrabberSubsystem(hardwareMap);

        waitForStart();

        grabberSubsystem.setGrabberPosition(0.9);
        sleep(400);

        elevatorSubsystem.setAutoExtension(2000);
        sleep(200);

        grabberSubsystem.setAngle(0.5);
        sleep(500);

        driveSubsystem.driveRobotCentric(0, 0.25, 0);
        sleep(2000);

        driveSubsystem.stop();
        sleep(500);

        grabberSubsystem.setGrabberPosition(0.3);
        sleep(100);
    }

}
