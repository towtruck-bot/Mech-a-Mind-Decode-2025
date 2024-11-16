package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.MotorControlAlgorithm;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Constants;

public class GrabberSubsystem {
    private Servo grabberServo;
    private Servo angleServo;
    private CRServo extendServo;

    private boolean isGrabberClosed;

    public GrabberSubsystem(HardwareMap hmap) {
        angleServo = hmap.get(Servo.class, "AngleServo");
        grabberServo = hmap.get(Servo.class, "GrabberServo");
        extendServo = hmap.get(CRServo.class, "ExtendServo");

        extendServo.getController().pwmEnable();

        isGrabberClosed = false;
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

    public void setAngle(double position) {
        if(position > 1.0) {
            position = 1.0;
        } else if(position < 0.0) {
            position = 0.0;
        }
        angleServo.setPosition(position);
    }


    public void setExtendPower(double power) {
        extendServo.setPower(power);
    }

    public double getAngle() {
        return angleServo.getPosition();
    }
}
