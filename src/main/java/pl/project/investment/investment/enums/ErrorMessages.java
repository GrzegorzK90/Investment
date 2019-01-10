package pl.project.investment.investment.enums;

public enum ErrorMessages {
        NO_RECORD_FOUND("don't exist in database id "),
        CONVERSION_TYPE_ERROR("Wrong type in request body"),
        NEGATIVE_DAY("Investment request have negative day error"),
        WRONG_REQUEST_BODY("Wrong request body please read documentation"),
        WRONG_VALUE("Wrong value in field "),
        WRONG_DATE("DateFrom is bigger Than DateTo");

        private final String errorMessage;

        ErrorMessages(String errorMessage){
                this.errorMessage = errorMessage;
        }

        public String getErrorMessage(){
                return errorMessage;
        }

}
