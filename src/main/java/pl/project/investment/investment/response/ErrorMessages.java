package pl.project.investment.investment.response;

public enum ErrorMessages {
        ZERO_VALUE("Found 0.00 in amount field"),
        NEGATIVE_VALUE("Value is negative"),
        NO_RECORD_FOUND("don't exist in database id "),
        NO_ALGORITHM_EXIST("Algorithm don't exist"),
        CONVERSION_TYPE_ERROR("Wrong type in request body"),
        ZERO_DAY("Investment is for 0 day error"),
        NEGATIVE_DAY("Investment is for negative number of day error");

        private String errorMessage;

        ErrorMessages(String errorMessage){
                this.errorMessage = errorMessage;
        }

        public String getErrorMessage(){
                return errorMessage;
        }

        public void setErrorMessage(String errorMessage){
                this.errorMessage = errorMessage;
        }
}
