package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class ShooterSubsystem {
    private Servo shooterServo, angleServo;
    private DcMotor shooterMotor;
    private double shooterPower = 0.5;
    private double shooterAngle = 0.5;
    public ShooterSubsystem(HardwareMap hmap) {
    shooterMotor = hmap.get(DcMotor.class, "shooterMotor");
    shooterServo = hmap.get(Servo.class, "shooterServo");
    angleServo = hmap.get(Servo.class, "angleServo");
    }

    public void setShooterPower(double power) {
        shooterPower = power;
    }

    public void setShooterAngle(double angle) {
        shooterAngle = angle;
        angleServo.setPosition(angle);
    }

    public void spin() {
        shooterMotor.setPower(shooterPower);
    }

    public void shoot() {
        shooterServo.setPosition(1);
    }

    public void stop() {
        shooterMotor.setPower(0);
        shooterServo.setPosition(0.5);
    }

    public double getShooterAngle(){
        return shooterAngle;
    }
    public double getShooterPower(){
        return shooterPower;
    }
}
