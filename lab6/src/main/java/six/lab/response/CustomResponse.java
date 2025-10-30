package six.lab.response;

import lombok.Getter;

@Getter
public class CustomResponse<T> {
    private final T data;
    private final String message;

    public CustomResponse(String message, T data){
        this.data = data;
        this.message = message;
    }

    
    public static <T> CustomResponse<T> success(String message, T data) {
        return new CustomResponse<T>(message, data);
    }
    
    public static <T> CustomResponse<T> fail(String message, T data) {
        return new CustomResponse<>(message, data);
    }}