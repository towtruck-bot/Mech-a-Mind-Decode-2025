package org.firstinspires.ftc.teamcode;

public class Constants {
    public static final class ElevatorConstants {
        public static final double MAX_MOTOR_SPEED = 0.8;

        public static final String LEFT_MOTOR_ID = "RightElevatorMotor";
        public static final String RIGHT_MOTOR_ID = "LeftElevatorMotor";

        public static final int kSetpointTolerance = 5;
        public static final int kCountsPerRotation = 537;
        public static final int kMaxExtensionCounts = 3000;
        public static final int kMinExtensionCounts = 0;

        public static final int[] ELEVATOR_LEVELS = {100, 1200, 2400};

        public static final String CENTER_MOTOR_ID = "CenterElevatorMotor";
    }
    public static final class GrabberConstants {
        public static final double MAX_EXTEND_SPEED = 0.8;
        public static final int MAX_ANGLE = 170;

        public static final int STRAIGHT_ANGLE = 85;
        public static final int MIN_ANGLE = 0;
        public static final double ANGLE_SPEED = 0.02;
        public static final double STRAIGHT_POWER = 0.01;

        public static final double kPAngle = 6;
        public static final double kIAngle = 0;
        public static final double kDAngle = 0;
        public static final double kFFAngle = 0;

        public static final double MAX_SPEED = 0;

        public static final double GRABBER_CLOSED_POS = 0.4;
        public static final double GRABBER_OPEN_POS = 0.0;
    }

    public static final class DriveConstants {
        public static final double DRIVE_SPEED = 0.5;
        public static final double DRIVE_SENSITIVITY = 2;
    }
}