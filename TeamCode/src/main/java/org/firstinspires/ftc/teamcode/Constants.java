package org.firstinspires.ftc.teamcode;

public class Constants {
    public static final class ElevatorConstants {
        public static final double MAX_MOTOR_SPEED = 0.8;

        public static final String LEFT_MOTOR_ID = "RightElevatorMotor";
        public static final String RIGHT_MOTOR_ID = "LeftElevatorMotor";

        public static final int kSetpointTolerance = 5;
        public static final int kCountsPerRotation = 537;
        public static final int kMaxExtensionCounts = 2400;
        public static final int kMinExtensionCounts = 0;

        public static final int[] ELEVATOR_LEVELS = {100, 1200, 2400};

    }
}