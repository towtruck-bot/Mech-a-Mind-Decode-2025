package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class GrabberSubsystem {
    private Servo grabberServo;
    private DcMotorEx angleMotor;
    private CRServo extendServo;
    private boolean isGrabberClosed;
    private int targetPosition;

    public GrabberSubsystem(HardwareMap hmap) {
        angleMotor = (DcMotorEx) hmap.get(DcMotor.class, "AngleMotor");
        grabberServo = hmap.get(Servo.class, "GrabberServo");
        extendServo = hmap.get(CRServo.class, "ExtendServo");

        angleMotor.setPIDFCoefficients(
                DcMotor.RunMode.RUN_TO_POSITION,
                new PIDFCoefficients(
                        Constants.GrabberConstants.kPAngle,
                        Constants.GrabberConstants.kIAngle,
                        Constants.GrabberConstants.kDAngle,
                        Constants.GrabberConstants.kFFAngle
                )
        );
        angleMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        extendServo.getController().pwmEnable();

        isGrabberClosed = false;

        targetPosition = 0;
    }

    public void toggleGrabber() {
        if(isGrabberClosed) {
            grabberServo.setPosition(0);
            isGrabberClosed = false;
        } else {
            grabberServo.setPosition(1);
            isGrabberClosed = true;
        }
    }

    public void setGrabberPosition(double position) {
        if(position > 1.0) {
            position = 1.0;
        } else if(position < 0.0) {
            position = 0.0;
        }
        grabberServo.setPosition(position);
    }

    public double getGrabberPosition() {
        return grabberServo.getPosition();
    }

    public void setAngle(int position) {
        setAngle(position, Constants.GrabberConstants.ANGLE_SPEED);
    }

    public void setAngle(int position, double power) {
        if(position > Constants.GrabberConstants.MAX_ANGLE) {
            position = Constants.GrabberConstants.MAX_ANGLE;
        } else if(position < Constants.GrabberConstants.MIN_ANGLE) {
            position = Constants.GrabberConstants.MIN_ANGLE;
        }

        targetPosition = position;

        angleMotor.setTargetPosition(position);

        angleMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        angleMotor.setPower(power);
    }

    public void setAngle_raw(double power) {
        angleMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        angleMotor.setPower(power);
    }


    public void setExtendPower(double power) {
        extendServo.setPower(power);
    }

    public int getAngle() {
        return angleMotor.getCurrentPosition();
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public double calculateAnglePower(double power) {
        power = Constants.GrabberConstants.MAX_SPEED * (Constants.GrabberConstants.STRAIGHT_ANGLE - (double) getAngle() / Constants.GrabberConstants.MAX_ANGLE);
        return power;
    }

    private double calculateFeedforward(double power) {
        double ff =  (Constants.GrabberConstants.STRAIGHT_ANGLE - (double) getAngle() / Constants.GrabberConstants.STRAIGHT_ANGLE) * Constants.GrabberConstants.STRAIGHT_POWER;
        return Math.min(power + ff, 1);
    }
}
