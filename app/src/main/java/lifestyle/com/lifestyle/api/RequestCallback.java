package lifestyle.com.lifestyle.api;

public interface RequestCallback<T> {
    void success(T t);
    void failed(String msg);
}
