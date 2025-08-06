package Commands;

import CustomException.AppException;

public interface Command {

    void execute() throws AppException;
    void undo();
}
