package server;

public class StatusResult {
    Status status;

    public StatusResult() {
        this.status = Status.NONE;

    }

    public synchronized  void setStatus(Status status){
        this.status = status;
    }
    public synchronized  Status getStatus(){
        return status;
    }
    public  synchronized String getMessage(){
        switch (status){
            case NONE:
                return "OK!";
            case MEMORY_LIMIT:
                return "Memory Limit!!!";
            case TIME_LIMIT:
                return "Time Limit!!!";
            case WRONG_ANSWER:
                return "Wrong Answer!!!";

            case RUNTIME_ERROR:
                return "Runtime Error!!!";
        }
        return "None";

    }
}
