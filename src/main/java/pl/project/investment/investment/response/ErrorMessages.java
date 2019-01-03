package pl.project.investment.investment.response;

public enum ErrorMessages {
        ZERO_VALUE("Found 0.00 in amount field"),
        NEGATIVE_VALUE("Value is negative"),
        NO_RECORD_FOUND("This id don't exist in database"),
        NO_ALGORITHM_EXIST("Algorithm don't exist");

        private String errorMessage;

        ErrorMessages(String errorMessage){
            this.errorMessage =errorMessage;
        }

        public String getErrorMessage(){
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage){
            this.errorMessage = errorMessage;
        }


}
