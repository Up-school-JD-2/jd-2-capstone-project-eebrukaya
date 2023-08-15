package io.upschool.globalexception;

public class CustomExcepiton {

    public static class AirlineNotFoundException extends RuntimeException {
        public AirlineNotFoundException(String message) {
            super(message);
        }
    }

    public static class AirportNotFoundException extends RuntimeException {
        public AirportNotFoundException(String message) {
            super(message);
        }
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class TicketNotFoundException extends RuntimeException {
        public TicketNotFoundException(String message) {
            super(message);
        }
    }

    public static class IllegalArgumentException extends RuntimeException {
        public IllegalArgumentException(String message) {
            super(message);
        }
    }

    public static class PaymentFailedException extends RuntimeException {
        public PaymentFailedException(String message) {
            super(message);
        }
    }


    public static class CrewMemberNotFoundException extends RuntimeException {
        public CrewMemberNotFoundException(String message) {
            super(message);
        }
    }
    public static class FlightNotFoundException extends RuntimeException {
        public FlightNotFoundException(String message) {
            super(message);
        }
    }

    public static class RecordCreationException extends RuntimeException {
        public RecordCreationException(String message) {
            super(message);
        }
    }

    public static class AirportUnavailableException extends RuntimeException {
        public AirportUnavailableException(String message) {
            super(message);
        }
    }

    public static class AirlineUnavailableException extends RuntimeException {
        public AirlineUnavailableException(String message) {
            super(message);
        }
    }
}
